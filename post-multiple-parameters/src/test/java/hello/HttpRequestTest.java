package hello;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.*;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void test1() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/", String.class))
                .contains("Hello World");
    }

    @Test
    public void test2() throws Exception {
        TestData t = new TestData();
        t.id = 123L;
        t.key = "key";
        t.name = "test";
        TestData2 t2 = new TestData2();
        t2.members = Arrays.asList("1", "2", "3");
        t2.t = t;

        String res = restTemplate.postForObject("http://localhost:" + port + "/t2", t2, String.class);

        assertThat(res).isEqualTo(t.name);
    }

    @Test
    public void test3() throws Exception {
        TestData t = new TestData();
        t.id = 123L;
        t.key = "key";
        t.name = "test";
        TestData2 t2 = new TestData2();
        t2.members = Arrays.asList("1", "2", "3");
        t2.t = t;


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Map req_payload = new HashMap();
        req_payload.put("t", t);
        req_payload.put("t2", t2);

        MultiValueMap<String, Object> map = new LinkedMultiValueMap();
        map.add("t", t);
        map.add("t2", t2);

        HttpEntity<?> request = new HttpEntity<>(map,headers);

        String res = restTemplate.postForObject("http://localhost:" + port + "/t3", req_payload, String.class);

        assertThat(res).isEqualTo(t.name);
    }
}
