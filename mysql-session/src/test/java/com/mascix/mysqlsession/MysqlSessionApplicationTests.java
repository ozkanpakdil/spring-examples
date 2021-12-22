package com.mascix.mysqlsession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class MysqlSessionApplicationTests {

  @Test
  void contextLoads() {}

  @Autowired
  private MockMvc mockMvc;

  @BeforeEach
  void testNativeSessionSet() throws Exception {
    mockMvc
      .perform(get("/demo/user/native/setsession/sesval"))
      .andDo(print())
      .andExpect(status().isOk());
  }

  @Test
  void testNativeSessionGet() throws Exception {
    mockMvc
      .perform(get("/demo/user/native/getsession/"))
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(content().string(containsString("sesval")));
  }
}
