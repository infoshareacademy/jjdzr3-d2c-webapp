package com.d2c.webapp.Controller;

import com.d2c.webapp.Service.DrinkService;
import com.infoshareademy.domain.Drink;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Random;

@Controller
public class SubController {

    @Autowired
    DrinkService drinkService;

    private static final Logger LOGGER = LogManager.getLogger(SubController.class);

    @GetMapping(value = "/login")
    public String getLogin() {
        LOGGER.info("Received login");
        return "Managements/login";
    }

    @GetMapping(value = "/Sign-Up")
    public String getSignUp() {
        LOGGER.info("Received request for Sign-Up");
        return "Managements/Sign-Up";
    }

    @GetMapping(value = "/Menagments")
    public String getMenagments() {
        LOGGER.info("Received request for Menagments");
        return "Managements/Menagments";
    }

    @GetMapping(value = "/AdvSearch")
    public String getAdvSearch() {
        LOGGER.info("Received request for Advanced search");
        return "subSites/AdvSearch";
    }

    @GetMapping(value = "/Favorites")
    public String getFavorites() {
        LOGGER.info("Received request for Favorites");
        return "subSites/Favorites";
    }

    @GetMapping(value = "/Search")
    public String getSearch() {
        LOGGER.info("Received request for Search");
        return "subSites/Search";
    }

    @GetMapping("/AddDrink")
    public String addSingleDrink(Model model) {
        model.addAttribute("drink", drinkService.getIngredientsList());
        LOGGER.info("Received request for AddDrink");
        return "Managements/AddDrink";
    }

    @PostMapping("/AddDrink")
    public String addDrink(@Valid @ModelAttribute Drink drink) {
        Random rand = new Random();
        int max = rand.nextInt(80000);
        drink.setDrinkId(max);
        LOGGER.info(drink);
        drinkService.addDrink(drink);
        return "Managements/AddDrink";
    }
}