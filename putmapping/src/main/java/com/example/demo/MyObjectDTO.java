package com.example.demo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.List;

@Data
@NoArgsConstructor(force = true)
public class MyObjectDTO {
    List<ChildDTO> children;
}
