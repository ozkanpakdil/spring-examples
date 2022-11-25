package com.mascix.badjson.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
//@AllArgsConstructor
//@NoArgsConstructor
public class BadJSon {
    @JsonProperty("11var1")
    String var1;
    @JsonProperty("11var1")
    String var2;
}
