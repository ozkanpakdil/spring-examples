package io.opentelemetry.example.graal;

import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.trace.SpanKind;
import io.opentelemetry.contrib.sampler.RuleBasedRoutingSampler;
import io.opentelemetry.exporter.logging.LoggingSpanExporter;
import io.opentelemetry.exporter.otlp.http.trace.OtlpHttpSpanExporter;
import io.opentelemetry.sdk.autoconfigure.spi.AutoConfigurationCustomizerProvider;
import io.opentelemetry.sdk.autoconfigure.spi.ConfigProperties;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.sdk.trace.export.SimpleSpanProcessor;
import io.opentelemetry.sdk.trace.export.SpanExporter;
import io.opentelemetry.sdk.trace.samplers.Sampler;
import io.opentelemetry.semconv.UrlAttributes;
import java.util.Collections;
import java.util.Map;

import jakarta.annotation.PostConstruct;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenTelemetryConfig {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(OpenTelemetryConfig.class);

  @Bean
  public AutoConfigurationCustomizerProvider otelCustomizer() {
    return p ->
        p.addSamplerCustomizer(this::configureSampler)
            .addSpanExporterCustomizer(this::configureSpanExporter);
  }

  /** suppress spans for actuator endpoints */
  private RuleBasedRoutingSampler configureSampler(Sampler fallback, ConfigProperties config) {
    return RuleBasedRoutingSampler.builder(SpanKind.SERVER, fallback)
        .drop(UrlAttributes.URL_PATH, "^/actuator")
        .build();
  }

  /**
   * Configuration for the OTLP exporter. This configuration will replace the default OTLP exporter,
   * and will add a custom header to the requests.
   */
  private SpanExporter configureSpanExporter(SpanExporter exporter, ConfigProperties config) {
    if (exporter instanceof OtlpHttpSpanExporter) {
      logger.info("Using OtlpHttpSpanExporter");
      logger.info(exporter.toString());
      System.out.println("Using OtlpHttpSpanExporter");
      return ((OtlpHttpSpanExporter) exporter).toBuilder().setHeaders(this::headers).build();
    }
    return exporter;
  }

  private Map<String, String> headers() {
    return Collections.singletonMap("Authorization", "Bearer " + refreshToken());
  }

  private String refreshToken() {
    // e.g. read the token from a kubernetes secret
    return "token";
  }
}
