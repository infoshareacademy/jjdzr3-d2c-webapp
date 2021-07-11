package com.d2c.webapp.Controller;

import com.d2c.webapp.Service.DrinkService;
import com.infoshareademy.domain.Drink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Random;

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

    @GetMapping ("/AddDrink")
    public String addSingleDrink(Model model){
        model.addAttribute("drink", new Drink());
        return "Managements/AddDrink";
    }

    @PostMapping("/AddDrink")
    public String addDrink(@ModelAttribute Drink drink) {
        Random rand = new Random();
        int max = rand.nextInt(80000);
        drink.setDrinkId(max);
        System.out.println(drink);
        return "redirect:/showAllDrinks";
    }
}