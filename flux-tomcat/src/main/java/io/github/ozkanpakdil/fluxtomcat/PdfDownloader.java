package io.github.ozkanpakdil.fluxtomcat;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

@AllArgsConstructor
@RestController
@Slf4j
public class PdfDownloader {
    final WebClient webClient;

    @GetMapping(path = "/download", produces = MediaType.APPLICATION_PDF_VALUE)
    public Flux<DataBuffer> downloadPdf() {
        log.info("returning flux...");
        return webClient.get().uri("https://files.testfile.org/PDF/10MB-TESTFILE.ORG.pdf")
                .retrieve()
                .bodyToFlux(DataBuffer.class);
    }

    @SneakyThrows
    @LogExecutionTime
    @GetMapping(path = "/download2", produces = MediaType.APPLICATION_PDF_VALUE)
    public void downloadPdf2(HttpServletResponse response) {
        Flux<DataBuffer> flux = webClient.get().uri("https://files.testfile.org/PDF/100MB-TESTFILE.ORG.pdf")
                .retrieve()
                .bodyToFlux(DataBuffer.class);
        DataBufferUtils
                .write(flux, response.getOutputStream())
                .map(DataBufferUtils::release)
                .blockLast();
    }

    @SneakyThrows
    @LogExecutionTime
    @GetMapping(path = "/download3", produces = MediaType.APPLICATION_PDF_VALUE)
    public void downloadPdf3(HttpServletResponse response) {
        URL url = new URL("https://files.testfile.org/PDF/100MB-TESTFILE.ORG.pdf");
        URLConnection connection = url.openConnection();
        InputStream is = connection.getInputStream();
        copyLarge(is, response.getOutputStream());
    }

    public static long copyLarge(final InputStream input, final OutputStream output)
            throws IOException {
        byte[] buffer = new byte[64 * 1024 * 1024];
        long count = 0;
        int n = 0;
        while (-1 != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
            if (count % 50 == 0) {
                output.flush();
                log.info("flushed");
            }
            count += n;
        }
        output.flush();
        return count;
    }
}
