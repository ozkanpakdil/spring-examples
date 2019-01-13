package com.mascix;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.annotation.PostConstruct;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomFilter extends GenericFilterBean {
    MaintenanceRequestMatcher maintenanceRequestMatcher;

    @PostConstruct
    void post() {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        maintenanceRequestMatcher = webApplicationContext.getBean(MaintenanceRequestMatcher.class);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String url = "";
        if (request instanceof HttpServletRequest) {
            url = ((HttpServletRequest) request).getRequestURL().toString();
        }

        if (maintenanceRequestMatcher.getMaintainenceMode() == false)
            chain.doFilter(request, response);
        else {
            if (url.contains("maintain")) {
                chain.doFilter(request, response);
            } else {
                HttpServletResponse hsr = (HttpServletResponse) response;
                hsr.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
            }
        }
    }

}