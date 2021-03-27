package com.azish.nasa.presentation.controller;

import com.azish.nasa.presentation.model.PhotoList;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class NasaControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;
    private static final String HOST = "http://localhost:";


    @Test
    @Order(1)
    public void callData() {
        final String path = "/api/v1/nasa/data";

        try {
            //String uri = HOST + port + peath + "?sol=1000&page=2&api_key=DEMO_KEY";
            String uri = HOST + port + path + "?sol=1000&camera=&page=2&api_key=DEMO_KEY";

            // build the request
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.ALL));
            HttpEntity entity = new HttpEntity(headers);
            ResponseEntity<PhotoList> response = this.restTemplate.exchange(uri, HttpMethod.GET, entity, PhotoList.class);
            assertThat(response.getBody()).isNotEqualTo(null);
        } catch (Exception ex) {
            fail(ex.toString());
        }
    }

    @Test
    @Order(2)
    public void callPhoto() {
        final String path = "/api/v1/nasa/photo";
        try {
            String uri = HOST + port + path + "?sol=1000&camera=&page=2&api_key=DEMO_KEY";

            // build the request
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.ALL));
            HttpEntity entity = new HttpEntity(headers);
            ResponseEntity<List> response = this.restTemplate.exchange(uri, HttpMethod.GET, entity, List.class);
            assertThat(response.getBody()).isNotEqualTo(null);
        } catch (Exception ex) {
            fail(ex.toString());
        }
    }

}
