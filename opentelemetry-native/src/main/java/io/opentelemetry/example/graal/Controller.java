package io.opentelemetry.example.graal;

import io.opentelemetry.api.trace.Span;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
  private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Controller.class);

  @GetMapping("/ping")
  public String ping() {
//    Span span = Span.current();
//    log.info("Current span's trace id: {}.", span.getSpanContext().getTraceId());
//    log.info("Current span: {}.", span);
    return "pong";
  }
}
