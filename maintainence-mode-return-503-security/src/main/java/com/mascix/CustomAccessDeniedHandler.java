package com.mascix;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Autowired
    MaintenanceRequestMatcher maintenanceRequestMatcher;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException e) throws IOException, ServletException {
        if (maintenanceRequestMatcher.maintainenceMode == true) {
            response.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
            Map<String, Object> data = new HashMap<>();
            data.put("timestamp", Calendar.getInstance().getTime());
            data.put("exception", "no-access");
            response.getOutputStream().println(objectMapper.writeValueAsString(data));
        }
        // You can create your own repsonse here to handle method level access denied reponses..
        // Follow similar method to the bad credentials handler above.
    }

}