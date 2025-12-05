### mTLS Spring Boot client–server example with OpenFeign and X.509 authentication

This folders contains two independent Spring Boot applications demonstrating mutual TLS (mTLS) between a server and a client implemented with Spring Cloud OpenFeign. It also shows how to use Spring Security’s X.509 authentication to “recognize” the client at the application layer and authorize requests.

Repository layout:
- `server` Spring Boot app exposing `GET /api/hello`, configured to REQUIRE client certificates (mTLS) and authorize only a known client identity.
- `client` Spring Boot app using OpenFeign over HTTPS+mTLS to call the server at startup.
- `certs/gen-certs.sh` One‑command tool to create a demo Certificate Authority (CA), issue server/client certs, and build PKCS#12 keystores/truststores.
- `client/scripts/test-mtls.sh` End‑to‑end runner: regenerates certs, boots server, runs client, and performs a negative test with an unauthorized client cert. Time‑boxed to 30s at each step, with helpful log tails.

Tested with:
- Java 17+
- Spring Boot 3.3.x
- Spring Cloud 2023.0.x

---

### Quick start

1) Generate certificates and keystores

```
cd certs
chmod +x gen-certs.sh
./gen-certs.sh
```

Artifacts are written under `certs/output/`:
- `server-keystore.p12`, `server-truststore.p12`
- `client-keystore.p12`, `client-truststore.p12`
- All passwords: `changeit` (development only)

2) Start the server

```
cd server
mvn spring-boot:run
```

The server listens on `https://localhost:8443` and requires client authentication (`client-auth: need`).

3) Start the client (new terminal)

```
cd client
mvn spring-boot:run
```

The client calls the server via Feign and logs:

```
Received from server: Hello from secure server!
```

Alternatively, use the automated end‑to‑end script (regen certs, boot server, run client, then negative test):

```
chmod +x client/scripts/test-mtls.sh
client/scripts/test-mtls.sh --tail 200
```

On success you’ll see the success message, followed by a negative test that is declined with HTTP 403 as expected.

---

### What’s happening under the hood

Transport layer (TLS):
- The server is configured to require client authentication and trusts a local demo CA:
  - `server/src/main/resources/application.yml` → `server.ssl.client-auth: need`, keystore/truststore paths.
- The client Feign stack uses Apache HttpClient5 with a custom `SSLContext` that loads:
  - Client key material from `client-keystore.p12` (for presenting the certificate)
  - Trust material from `client-truststore.p12` (containing the CA that signed the server cert)
  - Code: `client/src/main/java/dev/demo/client/config/SslFeignConfig.java`

Application layer (Spring Security X.509):
- The server uses Spring Security’s X.509 support to map the client certificate to a principal.
- `SecurityConfig` extracts the common name (CN) from the cert subject and authorizes only a known user:
  - File: `server/src/main/java/dev/demo/server/SecurityConfig.java`
  - `.x509().subjectPrincipalRegex("CN=(.*?)(?:,|$)")` → extracts e.g. `demo-client`
  - `/api/hello` requires role `CLIENT`; an in‑memory `UserDetailsService` defines `demo-client` with that role.

Accessing the certificate in controllers (cleanly):
- A reusable `@ClientCert` annotation + argument resolver injects the client `X509Certificate` into any controller method parameter, without servlet plumbing.
- Files:
  - `server/src/main/java/dev/demo/server/security/ClientCert.java`
  - `server/src/main/java/dev/demo/server/security/ClientCertArgumentResolver.java`
  - `server/src/main/java/dev/demo/server/WebConfig.java`
- Example controller:
  - `HelloController.hello(Principal principal, @ClientCert X509Certificate cert)` logs the authenticated user and certificate subject.

Negative test (unauthorized client):
- The script creates a second cert `CN=intruder-client` signed by the same CA.
- Because Spring Security only authorizes `demo-client`, the server responds `HTTP 403`  proving app‑level authorization on top of TLS validation.

---

### mTLS in IAM: the big picture

Identity and Access Management (IAM) ensures that:
- Authentication: “Who or what is calling?”
- Authorization: “What is it allowed to do?”

Where does mTLS fit?
- mTLS is a strong, mutual authentication mechanism at the transport layer. Both sides present X.509 certificates issued by a trusted CA.
- In machine‑to‑machine (service‑to‑service) scenarios, mTLS authenticates services without passwords or tokens.
- TLS alone answers “is this certificate issued by a CA I trust?”

Why add Spring Security on top?
- Application‑level IAM policies often need to recognize specific clients, not just “any cert from this CA”.
- With Spring Security X.509, you can map certificate attributes (Subject CN or SANs) to application principals and roles, then enforce fine‑grained authorization per endpoint.

Common IAM patterns with mTLS in Spring Boot:
- CN/SAN mapping → principal: Extract a stable identifier from Subject DN or SAN (DNS/URI) to become the username.
- Role assignment: Map principals to roles/authorities (in‑memory for demos; DB/LDAP in production).
- Allow‑listing by fingerprint: Compute SHA‑256 of the client cert and allow only known fingerprints (pinning).
- Revocation: Integrate CRL/OCSP checks (via proxy/ingress or custom validator) for cert lifecycle control.
- Rotation: Issue short‑lived certs; automate renewal and rollout via your PKI/secret manager.

---

### Apply this in your own Spring Boot apps

Server (require mTLS + X.509 auth):
1) Configure SSL and client auth in `application.yml`:
   ```yaml
   server:
     ssl:
       enabled: true
       key-store: file:/path/to/server-keystore.p12
       key-store-password: changeit
       key-store-type: PKCS12
       trust-store: file:/path/to/server-truststore.p12
       trust-store-password: changeit
       trust-store-type: PKCS12
       client-auth: need
   ```
2) Add Spring Security and X.509 mapping, then authorize endpoints:
   ```java
   http
     .x509(x -> x.subjectPrincipalRegex("CN=(.*?)(?:,|$)"))
     .authorizeHttpRequests(auth -> auth
       .requestMatchers("/api/hello").hasRole("CLIENT")
       .anyRequest().denyAll());
   ```
3) Replace the demo `InMemoryUserDetailsManager` with your identity source:
   - DB/LDAP lookup keyed by the extracted principal
   - Custom `AuthenticationUserDetailsService`/`UserDetailsService`
   - Or fingerprint allow‑list if you prefer pinning

Client (OpenFeign over mTLS):
1) Build an `SSLContext` with client key material + trusted CA, and plug it into a Feign `Client` backed by Apache HttpClient5.
2) Set the server URL to `https://host:port` and ensure hostname verification matches your cert’s SANs.

Operational tips:
- Terminating mTLS at the edge: If a reverse proxy/ingress terminates mTLS, forward the verified client identity to the app (e.g., via `X-SSL-Client-Cert`), or use mutual auth all the way through to the app.
- Secrets management: Store keystores/truststores and passwords securely; use your vault/secret store.
- Revocation and rotation: Plan for CRL/OCSP or short‑lived certs; automate renewals.

---

### Troubleshooting PKIX/SSL issues

- PKIX path building failed: The client’s truststore must contain the CA that issued the server certificate.
- Unexpected 403: Spring Security X.509 mapping didn’t match a user with the required role; verify CN/SAN and your user mapping.
- Hostname verification failure: Ensure the server certificate has SANs (`DNS:localhost`, `IP:127.0.0.1`) when calling `https://localhost`.
- Wrong store type or password: Both sides use PKCS#12 and password `changeit` in this demo; align types/passwords in config.
- Is Feign using the right SSLContext?: Ensure you provide a `Client` bean (as in `SslFeignConfig`) so Feign uses your custom HttpClient5 instance.

---

### Useful commands

Regenerate demo certificates
```
cd certs && ./gen-certs.sh
```

Run the automated test (positive + negative)
```
chmod +x client/scripts/test-mtls.sh
client/scripts/test-mtls.sh --tail 200
```

Run server and client manually
```
cd server && mvn spring-boot:run
# new terminal
cd client && mvn spring-boot:run
```

---

### Disclaimer
This is a development‑grade example. Do not reuse demo passwords or keys in production. Integrate with your organization’s PKI, secret manager, and IAM policies.
