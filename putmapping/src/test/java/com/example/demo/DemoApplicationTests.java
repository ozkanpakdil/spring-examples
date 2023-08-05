package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DemoApplicationTests {
    @Autowired
    MockMvc mvc;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void normalJson() throws Exception {
        ChildDTO childDTO = new ChildDTO(1L, "name");
        MyObjectDTO myObjectDTO = new MyObjectDTO();
        myObjectDTO.setChildren(List.of(childDTO));

        mvc.perform(put("/updateMyObject")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(myObjectDTO)))
                .andDo(print())
                .andExpect(jsonPath("$.childs[0].id").value(childDTO.getId()))
                .andExpect(jsonPath("$.childs[0].name").value(childDTO.getName()))
                .andExpect(status().isOk());
    }

    @Test
    void modelAttribute() throws Exception {
        ChildDTO childDTO = new ChildDTO(1L, "name");
        MyObjectDTO myObjectDTO = new MyObjectDTO();
        myObjectDTO.setChildren(List.of(childDTO));

        mvc.perform(put("/updateMyObject1")
                        .flashAttr("param", myObjectDTO)
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(jsonPath("$.childs[0].id").value(childDTO.getId()))
                .andExpect(jsonPath("$.childs[0].name").value(childDTO.getName()))
                .andExpect(status().isOk())
                .andDo(print());
    }

}
