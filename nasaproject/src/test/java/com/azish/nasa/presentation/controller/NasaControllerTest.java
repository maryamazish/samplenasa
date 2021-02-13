package com.azish.nasa.presentation.controller;

import com.azish.nasa.presentation.model.NasaModel;
import com.azish.nasa.presentation.model.PhotoList;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NasaControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;
    private static final String HOST = "http://localhost:";
    private static final String peath = "/nasa/getPhoto";

    @Test
    public void callNasa() {

            try {
                //String uri = HOST + port + peath + "?sol=1000&page=2&api_key=DEMO_KEY";
                String uri = HOST + port + peath + "?sol=1000&page=2&api_key=DEMO_KEY";

                // build the request
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
                HttpEntity entity = new HttpEntity(headers);
                ResponseEntity<PhotoList> response = this.restTemplate.exchange(uri, HttpMethod.GET, entity, PhotoList.class);
                PhotoList photoListResponse = response.getBody();
                System.out.println("photoListResponse : " + photoListResponse);
                assertThat(response.getBody()).isNotEqualTo(null);
            } catch (Exception ex) {
                fail(ex.toString());
            }
    }

}
