package com.d2c.webapp.Controller;

import com.infoshareademy.Filter;
import com.infoshareademy.Menu;
import com.infoshareademy.Search;
import com.infoshareademy.data.DrinkParser;
import com.infoshareademy.domain.Drink;
import com.infoshareademy.domain.DrinkRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/d2c")
public class D2Ccontroler {

    private static Logger logger = LoggerFactory.getLogger(D2Ccontroler.class);

    @GetMapping("/allDrinks")
    @ResponseBody
    public String getAllDrinks () {
        Map<Integer, String> menuMap = new HashMap<>();
        DrinkParser drinkParser = new DrinkParser();
        List<Drink> drinks = drinkParser.readFileIntoDrinkRepository().getDrinks();
        return drinks.toString();
    }

    @GetMapping("/menu")
    @ResponseBody
    public String getMenu() {
        Map<Integer, String> menuMap = new HashMap<>();
        DrinkParser drinkParser = new DrinkParser();
        DrinkRepository drinkRepository = drinkParser.readFileIntoDrinkRepository();
        List<Drink> drinks = drinkParser.readFileIntoDrinkRepository().getDrinks();
        Menu menu = new Menu();
        menu.menu(drinkParser, drinkRepository, drinks);
        return "Menu";
    }

    @GetMapping("/search")
    @ResponseBody
    public String getSearch() {
        Search search;
        return "Search";
    }

    @GetMapping("/filters")
    @ResponseBody
    public String getFilters() {
        Filter filter;
        return "filters";
    }

/*    @GetMapping("/index")
    @ResponseBody
    public String index(Model model) {
        return "index.html";
    }*/

}
