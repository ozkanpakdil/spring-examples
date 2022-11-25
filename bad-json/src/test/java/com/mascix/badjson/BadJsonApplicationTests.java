package com.mascix.badjson;

import com.mascix.badjson.controller.MyRestController;
import com.mascix.badjson.service.BadJsonService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MyRestController.class)
class BadJsonApplicationTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BadJsonService badJsonService;

    @Test
    void callBadJson() throws Exception {
        Mockito.when(badJsonService.gimmeBadMap()).thenReturn(Map.of("11var",""));
        mockMvc.perform(MockMvcRequestBuilders
                .get("/")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("11var").exists());
    }

}
