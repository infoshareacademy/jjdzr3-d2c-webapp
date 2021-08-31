package com.d2c.webapp.Controller;

import com.d2c.webapp.entities.DrinkEntity;
import com.d2c.webapp.service.DrinkService;
import com.infoshareademy.domain.Drink;
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


//////////////////////////////////////////////////////////////////
   @PostMapping(value ={"/EditDrink"})
    public String  getEditedDrink(@Valid @ModelAttribute Drink drink, Model model) {
        List<DrinkEntity> drinkEntityList = new ArrayList<>();
        drinkEntityList.add(drinkService.changeDrinkToDrinkEntity(drink));
        drinkService.update(drinkEntityList.get(0));



       model.addAttribute("drinkEntities", drinkEntityList.get(0) );

            return "Managements/singleDrinkFromDB";
        }































}