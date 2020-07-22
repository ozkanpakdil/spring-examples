package com.mascix.jsonbtest.controller;

import java.time.Duration;

import javax.json.Json;
import javax.json.JsonNumber;
import javax.json.JsonValue;
import javax.json.bind.adapter.JsonbAdapter;

public class DurationAdapter implements JsonbAdapter<Duration, JsonValue> {

    @Override
    public JsonValue adaptToJson(Duration duration) throws Exception {
        final JsonNumber value = Json.createValue(duration.toMillis());
        System.out.println("adaptToJson" + value);
        return value;
    }

    @Override
    public Duration adaptFromJson(JsonValue jsonValue) throws Exception {
        final long timeInMillis = ((JsonNumber) jsonValue).longValue();
        System.out.println("adaptFromJson" + timeInMillis);
        return Duration.ofMillis(timeInMillis);
    }
}