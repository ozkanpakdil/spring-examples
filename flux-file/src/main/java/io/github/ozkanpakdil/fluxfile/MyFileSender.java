package io.github.ozkanpakdil.fluxfile;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;


@AllArgsConstructor
@Slf4j
@RestController
public class MyFileSender {
    final private WebClient webClient;

    @GetMapping(path = "/download-flux", produces = MediaType.APPLICATION_PDF_VALUE)
    public Flux<byte[]> downloadPdf() {
        log.info("returning flux...");
        return webClient.get().uri("https://files.testfile.org/PDF/10MB-TESTFILE.ORG.pdf")
                .retrieve()
                .bodyToFlux(byte[].class);
    }

}
