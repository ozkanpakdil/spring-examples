package com.mascix.wasmdemo;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URL;

@RestController
public class MyWasmController {

    @GetMapping("/addTwo")
    public String addTwo() {
        try (Context context = Context.create()) {
            URL wasmFile = WasmdemoApplication.class.getResource("/test.wasm");
            String moduleName = "main";
            context.eval(Source.newBuilder("wasm", wasmFile).name(moduleName).build());
            Value addTwo = context.getBindings("wasm").getMember(moduleName).getMember("addTwo");
            return "addTwo(40, 2) = " + addTwo.execute(40, 2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
