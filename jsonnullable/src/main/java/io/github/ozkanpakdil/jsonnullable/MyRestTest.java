package io.github.ozkanpakdil.jsonnullable;

import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyRestTest {
    @GetMapping(value = "/test2", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MyDto> test2() {
        MyDto dto = new MyDto();
        dto.setMyBooleanField(JsonNullable.of(true));
        return ResponseEntity.ok(dto);
    }
}
