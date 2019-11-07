package com.emtowero.towero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ToweroApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToweroApplication.class, args);
    }

}
