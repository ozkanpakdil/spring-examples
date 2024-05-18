package com.mascix.variables;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MyBean {

    @Autowired
    private ProviderConfig providerConfig;

    @PostConstruct
    void ready(){
        System.out.println("val:"+providerConfig.getProvider());
    }

}
