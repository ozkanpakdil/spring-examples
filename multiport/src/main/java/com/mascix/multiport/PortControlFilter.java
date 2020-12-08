package com.mascix.multiport;

import java.io.IOException;
import java.lang.reflect.Method;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Slf4j
@Component
@Order(1)
public class PortControlFilter implements Filter {

    @Autowired
    TestController1 testController1;

    @Autowired
    TestController2 testController2;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain fc) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        Object choosenController = null;
        if (req.getLocalPort() == 8882) {
            choosenController = testController1;
        }
        if (req.getLocalPort() == 8883) {
            choosenController = testController2;
        }
        chooseTheController(req, res, choosenController);
// NOTE no need now        fc.doFilter(request, response);
    }

    private void chooseTheController(HttpServletRequest req, HttpServletResponse res, Object c) {
        String funcName = req.getRequestURI()
                .replaceAll("/controller1", "")
                .replaceAll("/controller2", "")
                .replaceAll("/", "");
        try {
            Method method = c.getClass().getDeclaredMethod(funcName, HttpServletRequest.class);
            res.getWriter().write(String.valueOf(method.invoke(c, req)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
