package com.mascix.badjson.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BadJsonServiceImpl implements BadJsonService {
    @Override
    public Map gimmeBadMap(){
        return Map.of(
                "11var","cs",
                "11var1","cs",
                "11var2","cs",
                "11var3","cs",
                "11var4","cs",
                "11var5","cs",
                "11var6","cs",
                "11var7","cs",
                "11var8","cs"
        );
    }
}
