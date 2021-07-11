package com.d2c.webapp.Controller;

import com.d2c.webapp.Service.DrinkService;
import com.infoshareademy.Filter;
import com.infoshareademy.Menu;
import com.infoshareademy.Search;
import com.infoshareademy.data.DrinkParser;
import com.infoshareademy.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/d2c")
public class D2Ccontroler {

    @Autowired
    DrinkService drinkService;

    private static Logger logger = LoggerFactory.getLogger(D2Ccontroler.class);

    @GetMapping("/allDrinks")
    public String getAllDrinks(Model model) {
        Map<Integer, String> menuMap = new HashMap<>();
        DrinkParser drinkParser = new DrinkParser();
        List<Drink> drinks = drinkParser.readFileIntoDrinkRepository().getDrinks();
        model.addAttribute("listOfDrinks", drinks);

        return "/allDrinks";
    }

    @GetMapping(value = "/singleDrink")
    public String  getSinDrink(Model model, @RequestParam ("name") String name) {
        List <Drink> drinks = getDrink(name);
        if (drinks == null) {
            return ResponseEntity.notFound().build().toString();
        } else {
            model.addAttribute("name", name);
            model.addAttribute("listOfDrinks", drinks);
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
    @GetMapping("/menu")
    public String getMenu(Model model) {
        Map<Integer, String> menuMap = new HashMap<>();
        DrinkParser drinkParser = new DrinkParser();
        DrinkRepository drinkRepository = drinkParser.readFileIntoDrinkRepository();
        List<Drink> drinks = drinkParser.readFileIntoDrinkRepository().getDrinks();
        Menu menu = new Menu();
        menu.menu(drinkParser, drinkRepository, drinks);
        return "Menu";
    }
}
