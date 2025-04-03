package com.example.demo.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.model.Role;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Component
public class UserAuthProviderImpl implements UserAuthProvider {
    @Value("${security.jwt.token.secret-key:secret-value}")
    private String secretKey;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String createToken(String login, Role role) {
        final Date now = new Date();
        final Date validity = new Date(now.getTime() + 144_000_000);

        return JWT.create()
                .withIssuer(login)
                .withSubject(role.toString())
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .sign(Algorithm.HMAC256(secretKey));
    }

    public String getLoginFromJwt(String token) {
        return JWT.decode(token).getIssuer();
    }

    public Authentication validateToken(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secretKey))
                .build();
        DecodedJWT decoded = verifier.verify(token);

        final String login = decoded.getIssuer();
        final String role = decoded.getSubject();

        return new UsernamePasswordAuthenticationToken(login, null, List.of(new SimpleGrantedAuthority("ROLE_" + role)));
    }
}