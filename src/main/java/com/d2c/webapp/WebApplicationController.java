package com.d2c.webapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebApplicationController {

    @GetMapping("/home")
    @ResponseBody
    public String getDemo() {
        return "Hello World";
    }

}
