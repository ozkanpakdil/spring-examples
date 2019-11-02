package mascix.com.sleepuntil;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalTime;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
public class MyRest {
    static int reqCounter = 0;

    @GetMapping(value = "/anothertest")
    public Mono<String> rest() {
        log.info("request number " + reqCounter++);
        CompletableFuture<String> stringCompletableFuture = sendRequestWithJavaHttpClient().thenApply(x -> "test: " + x);
        Duration between = Duration.between(
                LocalTime.now(),
                LocalTime.parse("14:30:00")
        );
        return Mono.first(Mono.delay(between)).then(Mono.fromFuture(stringCompletableFuture));
    }

    private CompletableFuture<String> sendRequestWithJavaHttpClient() {
        return CompletableFuture.supplyAsync(() -> {
            // do some logic here
            return "hello world.";
        });
    }

}
