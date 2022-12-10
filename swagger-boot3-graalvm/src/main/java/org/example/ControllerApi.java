package org.example;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ControllerApi {

    final LoginSender loginSender;

    public ControllerApi(LoginSender loginSender) {
        this.loginSender = loginSender;
    }

    @PostMapping("/login")
    boolean login(@RequestBody LoginParams params) {
        return loginSender.login(params.server(), params.login(), params.password(), params.build(), params.webManager());
    }

    @GetMapping("/get1")
    LoginParams get1(@RequestParam String login, @RequestParam(required = false) String webMan,
                     @RequestParam(required = false) String server, @RequestParam(required = false) String pass,
                     @RequestParam(required = false) String build) {
        return LoginParams.builder()
                .webManager(webMan)
                .server(server)
                .password(pass)
                .login(login)
                .build(build)
                .build();
    }
}
