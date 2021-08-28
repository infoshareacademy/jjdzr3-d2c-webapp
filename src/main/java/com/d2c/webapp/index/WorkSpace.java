package com.d2c.webapp.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WorkSpace {

    @GetMapping(value = "/workSpace")
    public String getIndex() {
        return "subSites/workSpace/index";
    }

}
