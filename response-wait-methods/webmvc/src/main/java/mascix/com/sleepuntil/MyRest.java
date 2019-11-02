package mascix.com.sleepuntil;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MyRest {
    static int reqCounter = 0;

    @GetMapping(value = "/caract/opcoes/acoes/disponiveis*")
    public ResponseEntity<Object> getDatasCaractOpcoesAcoesDisponiveis() throws Exception {
        log.info("request number " + reqCounter++);
        Duration between = Duration.between(
                LocalTime.now(),
                LocalTime.parse("14:50:00")
        );
        log.info("will sleep "+between.toMillis());
        Thread.sleep(between.toMillis());
        return new ResponseEntity<Object>("hello world", HttpStatus.OK);
    }

}
