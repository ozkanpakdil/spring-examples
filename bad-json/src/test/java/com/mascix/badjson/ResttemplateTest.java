package com.mascix.badjson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mascix.badjson.model.BadJSon;
import com.mascix.badjson.service.BadJsonService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Map;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@SpringBootTest
public class ResttemplateTest {

    @Autowired
    BadJsonService badJsonService;
    @Autowired
    private RestTemplate restTemplate;

    private MockRestServiceServer mockServer;
    private ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void init() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void resttemplatecall() throws Exception {
        BadJSon emp = BadJSon.builder()
                .var1("vv")
                .var2("aa")
                .build();
        Mockito.when(badJsonService.gimmeBadMap()).thenReturn(Map.of("11var",""));

        mockServer.expect(ExpectedCount.once(),
                        requestTo(new URI("http://localhost:8080/")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(emp))
                );

        Map employee = badJsonService.gimmeBadMap();
        mockServer.verify();
        Assertions.assertEquals(emp, employee);
    }
}
