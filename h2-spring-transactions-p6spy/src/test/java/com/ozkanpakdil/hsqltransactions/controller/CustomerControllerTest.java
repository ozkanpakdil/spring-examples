package com.ozkanpakdil.hsqltransactions.controller;

import com.ozkanpakdil.hsqltransactions.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CustomerControllerTest {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(CustomerControllerTest.class);
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CustomerService customerService;

    @BeforeEach
    void setup() {
        customerService.setup();
    }

    @Test
    void getCustomerByName() throws Exception {
        restCall("/byname/name1", jsonPath("$[0].name").value("name1"), status().isOk());
    }

    @Test
    void rollBackExample() throws Exception {
        restCall("/rollBackExample/name1", jsonPath("$[0].orders.length()").value(2), status().isOk());
        restCall("/rollBackExample/name1", jsonPath("$[0].orders.length()").value(4), status().isOk());
        throwingExceptionRequest("/rollBackExample/name1",
                result -> assertEquals("bad day", result.getResolvedException().getMessage()),
                status().isInternalServerError());
        // proof check orders are not saved, see orders length is 4
        restCall("/byname/name1", jsonPath("$[0].orders.length()").value(4), status().isOk());
    }

    @Test
    void noRollBackExample() throws Exception {
        restCall("/norollBackExample/name1", jsonPath("$[0].orders.length()").value(2), status().isOk());
        restCall("/norollBackExample/name1", jsonPath("$[0].orders.length()").value(4), status().isOk());
        throwingExceptionRequest("/norollBackExample/name1",
                result -> assertEquals("bad day", result.getResolvedException().getMessage()),
                status().isInternalServerError());
        // proof check orders are saved and length 6 now because no rollback
        restCall("/byname/name1", jsonPath("$[0].orders.length()").value(6), status().isOk());
    }

    private void restCall(String path, ResultMatcher expectedValue, ResultMatcher resultStatus) throws Exception {
        mockMvc.perform(get(path))
                .andDo(print())
                .andExpect(resultStatus)
                .andExpect(content().string(containsString("name1")))
                .andExpect(expectedValue);
    }

    private void throwingExceptionRequest(String path, ResultMatcher expectedValue, ResultMatcher resultStatus) throws Exception {
        mockMvc.perform(get(path))
                .andDo(print())
                .andExpect(resultStatus)
                .andExpect(expectedValue);
    }
}