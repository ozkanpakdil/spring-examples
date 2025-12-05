package dev.demo.client.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "mtls")
public class MtlsProperties {
    private String keyStore;
    private String keyStorePassword;
    private String keyStoreType = "PKCS12";
    private String trustStore;
    private String trustStorePassword;
    private String trustStoreType = "PKCS12";

    public String getKeyStore() { return keyStore; }
    public void setKeyStore(String keyStore) { this.keyStore = keyStore; }

    public String getKeyStorePassword() { return keyStorePassword; }
    public void setKeyStorePassword(String keyStorePassword) { this.keyStorePassword = keyStorePassword; }

    public String getKeyStoreType() { return keyStoreType; }
    public void setKeyStoreType(String keyStoreType) { this.keyStoreType = keyStoreType; }

    public String getTrustStore() { return trustStore; }
    public void setTrustStore(String trustStore) { this.trustStore = trustStore; }

    public String getTrustStorePassword() { return trustStorePassword; }
    public void setTrustStorePassword(String trustStorePassword) { this.trustStorePassword = trustStorePassword; }

    public String getTrustStoreType() { return trustStoreType; }
    public void setTrustStoreType(String trustStoreType) { this.trustStoreType = trustStoreType; }
}
