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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class SubController {

    @Autowired
    DrinkService drinkService;

    private static final Logger LOGGER = LogManager.getLogger(SubController.class);

    @GetMapping("/")
    public String getIndex() {
        LOGGER.info("Received request for main page");
        drinkService.addDrinksToBB();
        return "index";
    }


    @GetMapping(value = "/login")
    public String getLogin() {
        LOGGER.info("Received login");
        return "managements/Login";
    }

    @GetMapping(value = "/DeleteSuccesfull")
    public String getDeleteIfSuccesfull() {
        LOGGER.info("Received login");
        return "managements/DeleteSuccesfull";
    }


    @GetMapping(value = "/Sign-Up")
    public String getSignUp() {
        LOGGER.info("Received request for Sign-Up");
        return "managements/Sign-Up";
    }

    @GetMapping(value = "/managements")
    public String getManagement() {
        LOGGER.info("Received request for Menagments");
        return "managements/managements";
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
    public String addSingleDrink(Drink drink, Model model) {
        model.addAttribute("drink", drinkService.getIngredientsList());
        LOGGER.info("Received request for AddDrink");
        return "managements/AddDrink";
    }


    @PostMapping(value ={ "/AddDrink"})
    public String  AddDrink(@ModelAttribute("Managements/AddDrink")  @Valid  Drink drink , BindingResult result , Model model) {
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
            model.addAttribute("drink", drinkService.findAll());
            drinkService.addDrink(drink);
            // LOGGER.info(drink);

        }
        if (result.hasErrors()) {
            drinkService.addDrink(drink);
            return "managements/AddDrink";
        }
       return "singleDrink";
    }



    @GetMapping ("/singleDrinkFromDB")
    public String showAllEntities(Model model){
        List<DrinkEntity> drinkEntities = drinkService.findLast();
        model.addAttribute("drinkEntities", drinkEntities);
      return "managements/singleDrinkFromDB";
    }
    @GetMapping(value ={ "/EditDrink"})
    public String   EditDrink(Model model, @RequestParam ("name") String name) {
        //  drinkService.addDrinksToBB();
        List<DrinkEntity> drinkss = drinkService.findByName(name);
        List<Drink> drinks = new ArrayList<>();
        drinks.add(drinkService.changeDrinkEntityToDrink(drinkss.get(0)));

        if (drinks == null) {
            return ResponseEntity.notFound().build().toString();
        } else {
            model.addAttribute("name", name);
            model.addAttribute("listOfDrinks", drinks);
            model.addAttribute("drink", drinks.get(0));
            return "managements/EditDrink";
        }
    }
   @PostMapping(value ={"/EditDrink"})
    public String  getEditedDrink(@Valid @ModelAttribute Drink drink, Model model) {
        List<DrinkEntity> drinkEntityList = new ArrayList<>();
        drinkEntityList.add(drinkService.changeDrinkToDrinkEntity(drink));
        drinkService.update(drinkEntityList.get(0));
        model.addAttribute("drinkEntities", drinkEntityList.get(0) );
            return "managements/singleDrinkFromDB";
        }

    @GetMapping(value ={ "/DeleteDrink"})
    public String deleteDrink(Model model, @RequestParam ("name") String name) {

        List<DrinkEntity> drinkss = drinkService.findByName(name);
        List<Drink> drinks = new ArrayList<>();
        drinks.add(drinkService.changeDrinkEntityToDrink(drinkss.get(0)));

        if (drinks == null) {
            return ResponseEntity.notFound().build().toString();
        } else {
            drinkService.deleteByName(name);
            return "redirect:DeleteSuccesfull";
        }
    }
}
