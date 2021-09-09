package com.d2c.webapp.index;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private static final Logger LOGGER = LogManager.getLogger(IndexController.class);

    @GetMapping(value = "/welcome")
    public String getIndex() {
        LOGGER.info("Received request for main page");
        return "index";
    }
}