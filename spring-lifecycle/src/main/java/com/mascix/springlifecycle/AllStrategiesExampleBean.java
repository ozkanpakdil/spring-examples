package com.mascix.springlifecycle;

import lombok.extern.slf4j.Slf4j;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class AllStrategiesExampleBean implements InitializingBean {
    public AllStrategiesExampleBean() {
        log.info("Constructor");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("InitializingBean");
    }

    @PostConstruct
    public void postConstruct() {
        log.info("PostConstruct");
    }

    public void init() {
        log.info("init-method");
    }
}