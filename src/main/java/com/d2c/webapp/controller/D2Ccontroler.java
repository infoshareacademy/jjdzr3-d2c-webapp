package com.d2c.webapp.controller;

import com.d2c.webapp.service.DrinkService;
import com.infoshareademy.Menu;
import com.infoshareademy.data.DrinkParser;
import com.infoshareademy.domain.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@Controller
@RequestMapping("/d2c")
public class D2Ccontroler {

    @Autowired
    DrinkService drinkService;

    private static final Logger LOGGER = LogManager.getLogger(D2Ccontroler.class);

    @GetMapping("/allDrinks")
    public String getAllDrinks(Model model) {
        LOGGER.info("Read list of all drinks");

        DrinkParser drinkParser = new DrinkParser();
        List<Drink> drinks = drinkParser.readFileIntoDrinkRepository().getDrinks();
        model.addAttribute("listOfDrinks", drinks);
        LOGGER.debug("Number of read drinks: " + drinks.size());
        return "/allDrinks";
    }

    @GetMapping(value = "/singleDrink")
    public String getSinDrink(Model model, @RequestParam("name") String name) {
        LOGGER.info("Read single drink of name: " + name);

        List<Drink> drinks = drinkService.getDrinkByName(name);
        if (drinks == null) {
            LOGGER.info("No drink of name " + name + " found");
            return ResponseEntity.notFound().build().toString();
        } else {
            model.addAttribute("name", name);
            model.addAttribute("listOfDrinks", drinks);
            LOGGER.debug("Added drink of name " + name + " to display of drinks");
            return "singleDrink";
        }
    }

    @GetMapping("/menu")
    public String getMenu(Model model) {
        Map<Integer, String> menuMap = new HashMap<>();
        DrinkParser drinkParser = new DrinkParser();
        DrinkRepository drinkRepository = drinkParser.readFileIntoDrinkRepository();
        List<Drink> drinks = drinkParser.readFileIntoDrinkRepository().getDrinks();
        Menu menu = new Menu();
        menu.menu(drinkParser, drinkRepository, drinks);
        LOGGER.info("Received request for menu");
        return "Menu";
    }
}
