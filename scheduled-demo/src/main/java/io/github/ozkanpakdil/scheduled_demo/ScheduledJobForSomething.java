package io.github.ozkanpakdil.scheduled_demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledJobForSomething {
    private static final Logger log = LoggerFactory.getLogger(ScheduledJobForSomething.class);

    @Scheduled(cron = "0 * * * * *")
    public void runeveryminute() {
        log.info("1 minute");
    }
    @Scheduled(cron = "*/60 * * * * *")
    public void runevery60seconds() {
        log.info("60 secs");
    }

    @Scheduled(fixedRate = 60_000)
    public void consoleUserSqsListner() {
        log.info("fixed rate 60 seconds");
    }
}
