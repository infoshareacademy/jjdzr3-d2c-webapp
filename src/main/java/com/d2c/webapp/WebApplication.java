package com.d2c.webapp;

import com.d2c.webapp.service.DrinkService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
        System.out.println("It works");



    }
}