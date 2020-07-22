package com.mascix.jsonbtest.controller;

import java.time.Duration;

import javax.json.bind.annotation.JsonbTypeAdapter;

import org.hibernate.validator.constraints.time.DurationMax;
import org.hibernate.validator.constraints.time.DurationMin;

public class MyRequest {
    @DurationMin(minutes = 1)
    @DurationMax(minutes = 10)
    @JsonbTypeAdapter(DurationAdapter.class)
    private Duration expiredTime;

    public MyRequest(@DurationMin(minutes = 1) @DurationMax(minutes = 10) Duration expiredTime) {
        this.expiredTime = expiredTime;
    }

    public Duration getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(Duration expiredTime) {
        this.expiredTime = expiredTime;
    }
}
