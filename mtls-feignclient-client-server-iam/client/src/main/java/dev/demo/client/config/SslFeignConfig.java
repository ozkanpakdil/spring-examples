package dev.demo.client.config;

import feign.Client;
import feign.hc5.ApacheHttp5Client;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.socket.PlainConnectionSocketFactory;
import org.apache.hc.client5.http.ssl.DefaultHostnameVerifier;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.core5.http.config.Registry;
import org.apache.hc.core5.http.config.RegistryBuilder;
import org.apache.hc.core5.ssl.SSLContexts;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLContext;
import java.io.FileInputStream;
import java.security.KeyStore;

@Configuration
public class SslFeignConfig {

    @Bean
    public Client feignClient(MtlsProperties props) throws Exception {
        SSLContext sslContext = buildSslContext(props);

        SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext, new DefaultHostnameVerifier());

        Registry<org.apache.hc.client5.http.socket.ConnectionSocketFactory> registry = RegistryBuilder
                .<org.apache.hc.client5.http.socket.ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", sslSocketFactory)
                .build();

        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();

        return new ApacheHttp5Client(httpClient);
    }

    private SSLContext buildSslContext(MtlsProperties props) throws Exception {
        KeyStore keyStore = KeyStore.getInstance(props.getKeyStoreType());
        try (FileInputStream fis = new FileInputStream(stripFilePrefix(props.getKeyStore()))) {
            keyStore.load(fis, props.getKeyStorePassword().toCharArray());
        }

        KeyStore trustStore = KeyStore.getInstance(props.getTrustStoreType());
        try (FileInputStream fis = new FileInputStream(stripFilePrefix(props.getTrustStore()))) {
            trustStore.load(fis, props.getTrustStorePassword().toCharArray());
        }

        return SSLContexts.custom()
                .loadKeyMaterial(keyStore, props.getKeyStorePassword().toCharArray())
                .loadTrustMaterial(trustStore, null)
                .build();
    }

    private String stripFilePrefix(String path) {
        if (path == null) return null;
        if (path.startsWith("file:")) {
            return path.substring("file:".length());
        }
        return path;
    }
}
