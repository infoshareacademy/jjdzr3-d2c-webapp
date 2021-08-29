package com.d2c.webapp.Controller;

import com.d2c.webapp.service.DrinkService;
import com.infoshareademy.domain.Drink;
import com.infoshareademy.domain.Ingredient;
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
        model.addAttribute("drink", drinkService.getIngredientsList());

        return "Managements/AddDrink";
    }

    @GetMapping(value = "/EditDrink")
    public String  EditDrink(Model model, @RequestParam ("name") String name) {
        List <Drink> drinks = drinkService.getDrinkByName(name);
        if (drinks == null) {
            return ResponseEntity.notFound().build().toString();
        } else {
            model.addAttribute("name", name);
            model.addAttribute("listOfDrinks", drinks);
            model.addAttribute("drink", drinks.get(0));
            return "Managements/EditDrink";
        }
    }
    @PostMapping(value ={"/EditDrink", "/AddDrink"})
    public String  getEditedDrink(@Valid @ModelAttribute Drink drink, Model model) {
        List<Drink> drinks = new ArrayList<>();
        drinks.add(drink);
        if (drinks == null) {
            return ResponseEntity.notFound().build().toString();
        } else {
            model.addAttribute("name", drink.getDrinkName());
            model.addAttribute("listOfDrinks", drinks);
            System.out.println(drink); // To delate, testing line
        }
        return "singleDrink";

    }

    @GetMapping ("/singleDrinkFromDB")
    public String showAllEntities(Model model){
        List<DrinkEntity> drinkEntities = drinkService.findAll();
        model.addAttribute("drinkEntities", drinkEntities);
      return "Managements/singleDrinkFromDB";
    }

//    @PostMapping("/test")
//    public String addAllDrinks(@ModelAttribute List<Drink> drinks){
//        drinkService.addAllDrinks(drinks);
//        return "Managements/test";
//    }
//
//    @GetMapping ("/test")
//    public String adAllDrinks(Model model){
//        model.addAttribute("drinks", drinkService.getDrinkList());
//        return "Managements/test";
//    }


}