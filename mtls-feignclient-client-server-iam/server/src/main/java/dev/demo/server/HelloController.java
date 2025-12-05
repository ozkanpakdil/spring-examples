package dev.demo.server;

import dev.demo.server.security.ClientCert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.security.cert.X509Certificate;

@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/hello")
    public String hello(Principal principal, @ClientCert X509Certificate clientCert) {
        String username = (principal != null ? principal.getName() : "<unknown>");
        if (clientCert != null) {
            String subject = clientCert.getSubjectX500Principal().getName();
            System.out.println("[mTLS] Authenticated principal=" + username + ", Client certificate subject=" + subject);
        } else {
            System.out.println("[mTLS] No client certificate resolved; principal=" + username);
        }

        return "Hello from secure server!";
    }
}
