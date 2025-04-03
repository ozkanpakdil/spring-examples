package com.example.demo.jwt;

import com.example.demo.model.Role;
import org.springframework.security.core.Authentication;

public interface UserAuthProvider {
    String createToken(String login, Role role);

    String getLoginFromJwt(String token);

    Authentication validateToken(String token);
}
