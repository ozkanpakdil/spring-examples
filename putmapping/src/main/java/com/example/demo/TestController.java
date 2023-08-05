package com.example.demo;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @PutMapping(value = "/updateMyObject",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<MyObject> updateMyObject(@RequestBody MyObjectDTO myObjectDTO) {
        return ResponseEntity.ok(MyObject.builder()
                .childs(myObjectDTO.getChildren())
                .build());
    }

    @PutMapping(value = "/updateMyObject1",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<MyObject> updateMyObject1(@ModelAttribute("param") MyObjectDTO myObjectDTO) {
        return ResponseEntity.ok(MyObject.builder()
                .childs(myObjectDTO.getChildren())
                .build());
    }
}
