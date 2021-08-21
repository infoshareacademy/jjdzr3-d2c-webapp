package com.d2c.webapp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebApplicationController {

    private static final Logger LOGGER = LogManager.getLogger(WebApplicationController.class);

    @GetMapping("/")
    public String getDemo() {
        LOGGER.info("Received request for main page");
        return "index";
    }
}