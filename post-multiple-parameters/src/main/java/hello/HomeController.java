package hello;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@Slf4j
public class HomeController {

    @RequestMapping("/")
    public @ResponseBody
    String greeting() {
        return "Hello World";
    }

    @PostMapping("/t2")
    public @ResponseBody
    String greeting(@RequestBody TestData2 t) {
        log.info("t:{}", t);
        return t.t.name;
    }

    @PostMapping("/t3")
    public @ResponseBody
    String greeting2(@RequestBody HashMap args) {
        ObjectMapper mapper = new ObjectMapper();

        TestData2 t2 = mapper.convertValue(args.get("t2"), TestData2.class);
        TestData t = mapper.convertValue(args.get("t"), TestData.class);
        log.info("t:{}", t);
        log.info("t2:{}", t2);
        return t.name;
    }

}
