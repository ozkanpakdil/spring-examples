#!/usr/bin/env bash
set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"
OUT_DIR="$SCRIPT_DIR/output"
mkdir -p "$OUT_DIR"

PASSWORD="changeit"
DAYS=825

echo "[1/8] Generate CA key and cert"
openssl genrsa -out "$OUT_DIR/ca.key" 4096 2>/dev/null
openssl req -x509 -new -nodes -key "$OUT_DIR/ca.key" -sha256 -days $DAYS \
  -subj "/CN=Demo Local CA" -out "$OUT_DIR/ca.crt"

cat > "$OUT_DIR/openssl-san.cnf" <<EOF
[ req ]
distinguished_name = req_distinguished_name
req_extensions = v3_req
[ req_distinguished_name ]
[ v3_req ]
subjectAltName = @alt_names
[ alt_names ]
DNS.1 = localhost
IP.1 = 127.0.0.1
EOF

echo "[2/8] Create server key and CSR"
openssl genrsa -out "$OUT_DIR/server.key" 2048 2>/dev/null
openssl req -new -key "$OUT_DIR/server.key" -out "$OUT_DIR/server.csr" -subj "/CN=localhost" -config "$OUT_DIR/openssl-san.cnf"

echo "[3/8] Sign server cert with CA"
openssl x509 -req -in "$OUT_DIR/server.csr" -CA "$OUT_DIR/ca.crt" -CAkey "$OUT_DIR/ca.key" -CAcreateserial \
  -out "$OUT_DIR/server.crt" -days $DAYS -sha256 -extensions v3_req -extfile "$OUT_DIR/openssl-san.cnf"

echo "[4/8] Create client key and CSR"
openssl genrsa -out "$OUT_DIR/client.key" 2048 2>/dev/null
openssl req -new -key "$OUT_DIR/client.key" -out "$OUT_DIR/client.csr" -subj "/CN=demo-client"

echo "[5/8] Sign client cert with CA"
openssl x509 -req -in "$OUT_DIR/client.csr" -CA "$OUT_DIR/ca.crt" -CAkey "$OUT_DIR/ca.key" -CAcreateserial \
  -out "$OUT_DIR/client.crt" -days $DAYS -sha256

echo "[6/8] Build PKCS#12 keystores (with private key + cert chain)"
# Server keystore with alias 'server'
openssl pkcs12 -export -inkey "$OUT_DIR/server.key" -in "$OUT_DIR/server.crt" -certfile "$OUT_DIR/ca.crt" \
  -name server -out "$OUT_DIR/server-keystore.p12" -passout pass:$PASSWORD >/dev/null 2>&1

# Client keystore with alias 'client'
openssl pkcs12 -export -inkey "$OUT_DIR/client.key" -in "$OUT_DIR/client.crt" -certfile "$OUT_DIR/ca.crt" \
  -name client -out "$OUT_DIR/client-keystore.p12" -passout pass:$PASSWORD >/dev/null 2>&1

echo "[7/8] Build PKCS#12 truststores (containing the CA cert)"
# Ensure we start fresh to avoid alias-exists errors on repeated runs
rm -f "$OUT_DIR/server-truststore.p12" "$OUT_DIR/client-truststore.p12"
keytool -importcert -noprompt -alias demo-ca -file "$OUT_DIR/ca.crt" \
  -keystore "$OUT_DIR/server-truststore.p12" -storetype PKCS12 -storepass $PASSWORD >/dev/null 2>&1

keytool -importcert -noprompt -alias demo-ca -file "$OUT_DIR/ca.crt" \
  -keystore "$OUT_DIR/client-truststore.p12" -storetype PKCS12 -storepass $PASSWORD >/dev/null 2>&1

echo "[8/8] Done. Files in $OUT_DIR"
ls -1 "$OUT_DIR"

echo "\nPasswords used (dev only): $PASSWORD"
