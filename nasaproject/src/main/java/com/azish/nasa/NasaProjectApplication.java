package com.azish.nasa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class NasaProjectApplication {

//    @Bean
//    public HttpTraceRepository htttpTraceRepository()
//    {
//        return new InMemoryHttpTraceRepository();
//    }

    public static void main(String[] args) {
        SpringApplication.run(NasaProjectApplication.class, args);

    }

}
