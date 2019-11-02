package mascix.com.sleepuntil;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class MyRestTest {
    @Test
    public void testingTime(){
        Duration between = Duration.between(
                LocalTime.now(),
                LocalTime.parse("12:38:00")
        );
        System.out.println(between.getSeconds());
    }

}