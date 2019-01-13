package com.mascix;

import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class MaintenanceRequestMatcher implements RequestMatcher {
    public Boolean maintainenceMode = false;

    public Boolean getMaintainenceMode() {
        return maintainenceMode;
    }

    public void setMaintainenceMode(Boolean maintainenceMode) {
        this.maintainenceMode = maintainenceMode;
    }

    @Override
    public boolean matches(HttpServletRequest request) {
        return maintainenceMode;
    }
}