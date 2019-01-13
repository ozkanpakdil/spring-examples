package com.mascix.controller;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Controller1Test {
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    public void Ahello() {
        ResponseEntity<String> response = restTemplate
                .withBasicAuth("u", "p")
                .getForEntity("http://localhost:" + port + "/hello", String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(response.getBody(), equalTo("huhuuu"));
    }

    @Test
    public void Benablemaintanince() {
        ResponseEntity<String> response = restTemplate
                .withBasicAuth("u", "p")
                .getForEntity("http://localhost:" + port + "/maintain/enable", String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

    @Test
    public void Chello() {
        ResponseEntity<String> response = restTemplate
                .withBasicAuth("u", "p")
                .getForEntity("http://localhost:" + port + "/hello", String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.SERVICE_UNAVAILABLE));
    }

    @Test
    public void disablemaintanince() {
        ResponseEntity<String> response = restTemplate
                .withBasicAuth("u", "p")
                .getForEntity("http://localhost:" + port + "/maintain/disable", String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

    @Test
    public void status() {
        ResponseEntity<String> response = restTemplate
                .withBasicAuth("u", "p")
                .getForEntity("http://localhost:" + port + "/maintain/status", String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }
}