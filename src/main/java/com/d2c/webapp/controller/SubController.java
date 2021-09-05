package com.d2c.webapp.controller;

import com.d2c.webapp.entities.DrinkEntity;
import com.d2c.webapp.service.DrinkService;
import com.infoshareademy.domain.Drink;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
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

    @GetMapping(value = "/EditDrink")
    public String  EditDrink(Model model, @RequestParam ("name") String name) {
        List<DrinkEntity> drinkss = drinkService.findByName(name);
        List<Drink> drinks = new ArrayList<>();
        drinks.add(drinkService.changeDrinkEntityToDrink(drinkss.get(0)));

        if (drinks == null) {
            return ResponseEntity.notFound().build().toString();
        } else {
            model.addAttribute("name", name);
            model.addAttribute("listOfDrinks", drinks);
            model.addAttribute("drink", drinks.get(0));
            return "Managements/EditDrink";
        }
    }
    @PostMapping(value ={ "/AddDrink", })
    public String  AddDrink(@Valid @ModelAttribute Drink drink, Model model) {
        List<Drink> drinks = new ArrayList<>();
        drinks.add(drink);
        Random rand = new Random();
        int max = rand.nextInt(80000);
        drink.setDrinkId(max);
        if (drinks == null) {
            return ResponseEntity.notFound().build().toString();
        } else {
            model.addAttribute("name", drink.getDrinkName());
            model.addAttribute("listOfDrinks", drinks);
            LOGGER.info(drink);
            drinkService.addDrink(drink);
            System.out.println(drink); // To delate, testing line
        }
        return "singleDrink";
    }
    @GetMapping ("/singleDrinkFromDB")
    public String showAllEntities(Model model){
        List<DrinkEntity> drinkEntities = drinkService.findLast();
        model.addAttribute("drinkEntities", drinkEntities);
      return "Managements/singleDrinkFromDB";
    }

   @PostMapping(value ={"/EditDrink"})
    public String  getEditedDrink(@Valid @ModelAttribute Drink drink, Model model) {
        List<DrinkEntity> drinkEntityList = new ArrayList<>();
        drinkEntityList.add(drinkService.changeDrinkToDrinkEntity(drink));
        drinkService.update(drinkEntityList.get(0));
        model.addAttribute("drinkEntities", drinkEntityList.get(0) );

            return "Managements/singleDrinkFromDB";
        }
}