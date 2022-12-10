package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ControllerApiTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    LoginSender loginSender;
    private String apiVersion;

    @BeforeEach
    void beforeEach() {
        RequestMapping requestMapping = ControllerApi.class.getAnnotation(RequestMapping.class);
        apiVersion = requestMapping.value()[0];
    }

    @Test
    void login() throws Exception {
        when(loginSender.login(any(), any(), any(), any(), any())).thenReturn(true);

        String mvcResult = mockMvc.perform(post(apiVersion + "/login")
                        .content(mapper.writeValueAsString(LoginParams.builder().build()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        Assertions.assertEquals("true", mvcResult);
    }
}