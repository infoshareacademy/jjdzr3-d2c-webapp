package com.d2c.webapp.Controller;

import com.infoshareademy.Filter;
import com.infoshareademy.Menu;
import com.infoshareademy.Search;
import com.infoshareademy.data.DrinkParser;
import com.infoshareademy.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("/d2c")
public class D2Ccontroler {

    private static Logger logger = LoggerFactory.getLogger(D2Ccontroler.class);

    @GetMapping("/allDrinks")
    public String getAllDrinks (Model model) {
        Map<Integer, String> menuMap = new HashMap<>();
        DrinkParser drinkParser = new DrinkParser();
        List<Drink> drinks = drinkParser.readFileIntoDrinkRepository().getDrinks();
        model.addAttribute("listOfDrinks", drinks);

        return "/allDrinks.html";
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

    @RequestMapping("search")
    public String getSearch(Model model, @RequestParam(name="input") String item) {
        DrinkParser drinkParser = new DrinkParser();
        DrinkRepository drinkRepository = drinkParser.readFileIntoDrinkRepository();
        List<Drink> drinks = Search.searchItemsForQuery(drinkRepository, item);
        if (drinks.isEmpty()) {
            model.addAttribute("noDrinksFound", "No drinks found for given criteria: input too short or no drink available");
        } {
            model.addAttribute("listOfDrinks", drinks);
        }
        return "/search.html";
    }


    @GetMapping("/filters")
    @ResponseBody
    public String getFilters(Model model, @RequestParam(name="input") String item) {
//        Filter filter;
        Filter drinkFilter = new Filter();
//        drinkFilter.filter();





        return "filters";
    }
}
