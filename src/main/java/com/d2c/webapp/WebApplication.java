package com.d2c.webapp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebApplication {

    private static final Logger LOGGER = LogManager.getLogger(WebApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
        LOGGER.info("The application is running");
    }
}