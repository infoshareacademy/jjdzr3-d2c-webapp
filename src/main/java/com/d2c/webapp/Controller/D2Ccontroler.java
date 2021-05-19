package com.d2c.webapp.Controller;


import com.infoshareademy.Filter;
import com.infoshareademy.Menu;
import com.infoshareademy.Search;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import java.awt.*;

import static java.awt.SystemColor.menu;

@RestController
@RequestMapping("/d2c")
public class D2Ccontroler {


    @GetMapping("/menu")
    @ResponseBody
    public String getMenu() {
        Menu menu;
        return "Menu";
    }

    @GetMapping("/search")
    @ResponseBody
    public String getSearch() {
        Search search;
        return "Search";
    }


    @GetMapping("/filters")
    @ResponseBody
    public String getFilters() {
        Filter filter;
        return "filters";
    }
}
