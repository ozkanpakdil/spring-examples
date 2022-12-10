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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
    private String loginUrl;
    private String get1Url;

    @BeforeEach
    void beforeEach() throws NoSuchMethodException {
        RequestMapping requestMapping = ControllerApi.class.getAnnotation(RequestMapping.class);
        Method[] methods = ControllerApi.class.getDeclaredMethods();
        loginUrl = methods[0].getAnnotation(PostMapping.class).value()[0];
        get1Url = methods[1].getAnnotation(GetMapping.class).value()[0];
        apiVersion = requestMapping.value()[0];
    }

    @Test
    void login() throws Exception {
        when(loginSender.login(any(), any(), any(), any(), any())).thenReturn(true);

        String mvcResult = mockMvc.perform(post(apiVersion + loginUrl)
                        .content(mapper.writeValueAsString(LoginParams.builder().build()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        Assertions.assertEquals("true", mvcResult);
    }

    @Test
    void get1() throws Exception {
        mockMvc.perform(get(apiVersion + get1Url)
                        .param("login","1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.login").value("1"));
    }
}