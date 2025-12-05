#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "$0")/../.." && pwd)"
CERTS_DIR="$ROOT_DIR/certs"
SERVER_DIR="$ROOT_DIR/server"
CLIENT_DIR="$ROOT_DIR/client"

CLIENT_LOG="$ROOT_DIR/.client.log"
SERVER_LOG="$ROOT_DIR/.server.log"

VERBOSE=0
TLS_DEBUG=0
CURL_CHECK=0
SHOW_TAIL=80

usage() {
  cat <<EOF
Usage: $(basename "$0") [options]

Options:
  --verbose        Enable detailed DEBUG logging for client and server (Feign FULL, HttpClient5 wire, Spring Web)
  --tls-debug      Enable JVM TLS handshake debug (javax.net.debug=ssl,handshake)
  --curl-check     After client run, perform a raw mTLS curl call to show request/response details
  --tail N         Tail N lines of logs on success (default: $SHOW_TAIL)
  -h, --help       Show this help

Examples:
  $0 --verbose
  $0 --verbose --tls-debug --curl-check --tail 200
EOF
}

log() { echo "[test] $*"; }
log_ok() { echo "[test] $*"; }
log_err() { echo "[test] $*"; }

cleanup() {
  if [[ -n "${SERVER_PID:-}" ]] && ps -p "$SERVER_PID" >/dev/null 2>&1; then
    log "Stopping server (PID $SERVER_PID)"
    kill "$SERVER_PID" || true
    sleep 2 || true
  fi
}

trap cleanup EXIT

# Parse args
while [[ $# -gt 0 ]]; do
  case "$1" in
    --verbose) VERBOSE=1; shift ;;
    --tls-debug) TLS_DEBUG=1; shift ;;
    --curl-check) CURL_CHECK=1; shift ;;
    --tail) SHOW_TAIL=${2:-80}; shift 2 ;;
    -h|--help) usage; exit 0 ;;
    *) echo "Unknown option: $1"; usage; exit 2 ;;
  esac
done

# If a previous instance is using 8443, try to stop it to avoid port conflicts
if command -v lsof >/dev/null 2>&1; then
  PID_ON_PORT=$(lsof -ti:8443 || true)
  if [[ -n "${PID_ON_PORT:-}" ]]; then
    log "Port 8443 in use by PID(s): $PID_ON_PORT — attempting to terminate"
    kill ${PID_ON_PORT} || true
    sleep 2 || true
  fi
fi

log "Generating certificates"
chmod +x "$CERTS_DIR/gen-certs.sh"
"$CERTS_DIR/gen-certs.sh"

# Build logging and JVM args
SERVER_ARGS=""
SERVER_JVM_ARGS=""
CLIENT_ARGS=""
CLIENT_JVM_ARGS=""

if [[ $VERBOSE -eq 1 ]]; then
  SERVER_ARGS+=" --logging.level.org.springframework.web=DEBUG"
  SERVER_ARGS+=" --logging.level.org.apache.coyote.http11=DEBUG"

  CLIENT_ARGS+=" --logging.level.dev.demo.client=DEBUG"
  CLIENT_ARGS+=" --logging.level.feign=DEBUG"
  CLIENT_ARGS+=" --logging.level.org.springframework.cloud.openfeign=DEBUG"
  CLIENT_ARGS+=" --logging.level.org.apache.hc.client5.http=DEBUG"
  CLIENT_ARGS+=" --logging.level.org.apache.hc.client5.http.wire=DEBUG"
  CLIENT_ARGS+=" --feign.client.config.default.loggerLevel=FULL"
fi

if [[ $TLS_DEBUG -eq 1 ]]; then
  SERVER_JVM_ARGS+=" -Djavax.net.debug=ssl,handshake"
  CLIENT_JVM_ARGS+=" -Djavax.net.debug=ssl,handshake"
fi

log "Starting server"
(
  cd "$SERVER_DIR"
  mvn -q -DskipTests \
    -Dspring-boot.run.jvmArguments="${SERVER_JVM_ARGS}" \
    -Dspring-boot.run.arguments="${SERVER_ARGS}" \
    spring-boot:run
) >"$SERVER_LOG" 2>&1 &
SERVER_PID=$!
log "Server started with PID $SERVER_PID (logs: $(basename "$SERVER_LOG"))"

# Wait for server to be ready: prefer a real HTTPS mTLS probe, fallback to log markers
log "Waiting for server to become ready..."
READY_TIMEOUT=30
ready=0
for i in $(seq 1 $READY_TIMEOUT); do
  # Try a real request using the generated client certificate
  HTTP_STATUS=$(curl -sk \
    --key "$CERTS_DIR/output/client.key" \
    --cert "$CERTS_DIR/output/client.crt" \
    --cacert "$CERTS_DIR/output/ca.crt" \
    -o /dev/null -w '%{http_code}' \
    https://localhost:8443/api/hello || true)
  if [[ "$HTTP_STATUS" == "200" ]]; then
    ready=1; break
  fi

  # Also accept typical Spring Boot startup markers as readiness signals
  if grep -qE "Tomcat started on port.*8443|Started ServerApplication" "$SERVER_LOG" 2>/dev/null; then
    ready=1; break
  fi

  sleep 1
done
if [[ $ready -ne 1 ]]; then
  log "Server did not become ready within ${READY_TIMEOUT}s. Showing last 120 lines of server log."
  tail -n 120 "$SERVER_LOG" || true
  exit 1
fi

log "Running client (max 30s)"
(
  cd "$CLIENT_DIR"
  mvn -q -DskipTests \
    -Dspring-boot.run.jvmArguments="${CLIENT_JVM_ARGS}" \
    -Dspring-boot.run.arguments="${CLIENT_ARGS}" \
    spring-boot:run
) >"$CLIENT_LOG" 2>&1 &
CLIENT_PID=$!

# Wait up to 30s for client to log success or exit
CLIENT_TIMEOUT=30
client_done=0
for i in $(seq 1 $CLIENT_TIMEOUT); do
  if ! ps -p "$CLIENT_PID" >/dev/null 2>&1; then
    client_done=1; break
  fi
  if grep -q "Received from server: Hello from secure server!" "$CLIENT_LOG" 2>/dev/null; then
    client_done=1; break
  fi
  sleep 1
done

if [[ $client_done -ne 1 ]]; then
  log "Client did not finish within ${CLIENT_TIMEOUT}s. Terminating (PID $CLIENT_PID)."
  kill "$CLIENT_PID" || true
  sleep 1 || true
fi

if grep -q "Received from server: Hello from secure server!" "$CLIENT_LOG" 2>/dev/null; then
  log_ok "SUCCESS: Client received expected response"
  echo
  log "=== Client log (tail $SHOW_TAIL) ==="
  tail -n "$SHOW_TAIL" "$CLIENT_LOG" || true
  echo
  log "=== Server log (tail $SHOW_TAIL) ==="
  tail -n "$SHOW_TAIL" "$SERVER_LOG" || true

  if [[ $CURL_CHECK -eq 1 ]]; then
    echo
    log "Performing raw mTLS curl check (showing request/response details)"
    curl -vk \
      --key "$CERTS_DIR/output/client.key" \
      --cert "$CERTS_DIR/output/client.crt" \
      --cacert "$CERTS_DIR/output/ca.crt" \
      https://localhost:8443/api/hello || true
    echo
  fi

  # Negative test: use a different client certificate (signed by same CA but CN not authorized)
  echo
  log "Preparing unauthorized client certificate (CN=intruder-client) for negative test"
  BAD_DIR="$CERTS_DIR/output"
  BAD_KEY="$BAD_DIR/bad-client.key"
  BAD_CSR="$BAD_DIR/bad-client.csr"
  BAD_CRT="$BAD_DIR/bad-client.crt"
  # Create a new key/cert if missing or always regenerate to be deterministic
  openssl genrsa -out "$BAD_KEY" 2048 >/dev/null 2>&1
  openssl req -new -key "$BAD_KEY" -out "$BAD_CSR" -subj "/CN=intruder-client" >/dev/null 2>&1
  openssl x509 -req -in "$BAD_CSR" -CA "$CERTS_DIR/output/ca.crt" -CAkey "$CERTS_DIR/output/ca.key" -CAcreateserial \
    -out "$BAD_CRT" -days 365 -sha256 >/dev/null 2>&1

  log "Testing call with unauthorized cert (expect HTTP 403)"
  BAD_STATUS=$(curl -sk \
    --key "$BAD_KEY" \
    --cert "$BAD_CRT" \
    --cacert "$CERTS_DIR/output/ca.crt" \
    -o /dev/null -w '%{http_code}' \
    https://localhost:8443/api/hello || true)
  if [[ "$BAD_STATUS" == "403" ]]; then
    log_err "DECLINED as expected (HTTP $BAD_STATUS) for CN=intruder-client"
    log "=== Server log around denial (tail 60) ==="
    tail -n 60 "$SERVER_LOG" || true
  else
    log_err "Unexpected status for unauthorized cert. Got HTTP $BAD_STATUS (expected 403)."
    log_err "Showing recent server logs (tail 120)"
    tail -n 120 "$SERVER_LOG" || true
    exit 1
  fi
  exit 0
else
  log "FAIL: Expected success log not found. Showing client and server log tails."
  echo "--- Client log (tail) ---"
  tail -n 200 "$CLIENT_LOG" || true
  echo "--- Server log (tail) ---"
  tail -n 200 "$SERVER_LOG" || true
  exit 1
fi
