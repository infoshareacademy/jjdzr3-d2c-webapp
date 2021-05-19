package com.d2c.webapp.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping(value = "/welcome")
    public String getIndex() {
        return "index";
    }
}