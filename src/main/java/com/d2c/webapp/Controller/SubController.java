package com.d2c.webapp.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class SubController {

    @GetMapping(value = "/login")
    public String getLogin() {
        return "Managements/login";
    }

    @GetMapping(value = "/Sign-Up")
    public String getSignUp() {
        return "Managements/Sign-Up";
    }

    @GetMapping(value = "/Menagments")
    public String getMenagments() {
        return "Managements/Menagments";
    }

    @GetMapping(value = "/AddDrink")
    public String getAddDrink() {
        return "Managements/AddDrink";
    }

    @GetMapping(value = "/AdvSearch")
    public String getAdvSearch() {
        return "subSites/AdvSearch";
    }

    @GetMapping(value = "/Favorites")
    public String getFavorites() {
        return "subSites/Favorites";
    }

    @GetMapping(value = "/Search")
    public String getSearch() {
        return "subSites/Search";
    }







}