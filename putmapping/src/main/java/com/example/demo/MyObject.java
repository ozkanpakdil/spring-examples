package com.example.demo;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.util.List;

@Builder
@Value
public class MyObject {
    List<ChildDTO> childs;
}
