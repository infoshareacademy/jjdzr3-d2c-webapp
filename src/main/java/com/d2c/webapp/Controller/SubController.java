package com.d2c.webapp.Controller;

import com.d2c.webapp.Service.DrinkService;
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
import java.util.stream.Collectors;

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

    @PostMapping(value = "/AddDrink")
    public String  getSinDrink(@Valid @ModelAttribute Drink drink, Model model) {
        List<Drink> drinks = new ArrayList<>();
        drinks.add(drink);
        if (drinks == null) {
            return ResponseEntity.notFound().build().toString();
        } else {
            model.addAttribute("name", drink.getDrinkName());
            model.addAttribute("listOfDrinks", drinks);
            return "singleDrink";
        }
    }

// Start of Editing Drink


    @GetMapping(value = "/EditDrink")
    public String  EditDrink(Model model, @RequestParam ("name") String name) {
        List <Drink> drinks = getDrink(name);
        if (drinks == null) {
            return ResponseEntity.notFound().build().toString();
        } else {
            model.addAttribute("name", name);
            model.addAttribute("listOfDrinks", drinks);
            model.addAttribute("drink", drinks.get(0));
            return "Managements/EditDrink";
        }
    }
    @PostMapping(value = "/EditDrink")
    public String  getEditedDrink(@Valid @ModelAttribute Drink drink, Model model) {
        List<Drink> drinks = new ArrayList<>();
        drinks.add(drink);
        if (drinks == null) {
            return ResponseEntity.notFound().build().toString();
        } else {
            model.addAttribute("name", drink.getDrinkName());
            model.addAttribute("listOfDrinks", drinks);
            System.out.println(drink); // To delate, testing line
            return "singleDrink";
        }
    }
    private List<Drink> getDrink(String name) {
        List<Drink> drinkList = drinkService.getDrinkList();
        List<Drink> filteredDrinks = drinkList
                .stream()
                .filter(a -> a.getDrinkName().toLowerCase().equals(name.toLowerCase()))
                .collect(Collectors.toList());
        System.out.println(filteredDrinks);
        return  filteredDrinks;
    }


    // End of Editing Drink
}