package com.mascix;

import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class MaintenanceRequestMatcher implements RequestMatcher {
    public static Boolean maintainenceMode = false;

    @Override
    public boolean matches(HttpServletRequest request) {
        return maintainenceMode;
    }
}