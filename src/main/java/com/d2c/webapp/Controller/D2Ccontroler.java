package com.d2c.webapp.Controller;

import com.infoshareademy.Filter;
import com.infoshareademy.Menu;
import com.infoshareademy.Search;
import com.infoshareademy.data.DrinkParser;
import com.infoshareademy.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/d2c")
public class D2Ccontroler {

    private static Logger logger = LoggerFactory.getLogger(D2Ccontroler.class);

    @GetMapping("/allDrinks")
    public String getAllDrinks(Model model) {
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

    @RequestMapping("/search")
    public String getSearch(Model model, @RequestParam(name = "input", required = true) String item) {
        DrinkParser drinkParser = new DrinkParser();
        DrinkRepository drinkRepository = drinkParser.readFileIntoDrinkRepository();
        List<Drink> drinks = Search.searchItemsForQuery(drinkRepository, item);
        if (drinks.isEmpty()) {
            model.addAttribute("noDrinksFound", "No drinks found for given criteria: input too short or no drink available");
        }
        {
            model.addAttribute("listOfDrinks", drinks);
        }
        return "search.html";
    }


//    @RequestMapping("/filters")
//    @ResponseBody
//    public String index(
//            @RequestParam(value = "type", required = false) String type,
//            @RequestParam(value = "glassType", required = false) String glassType,
//            @RequestParam(value = "drinkType", required = false) String drinkType,
//            Model model
//    ) {
//        DrinkParser drinkParser = new DrinkParser();
//        DrinkRepository drinkRepository = drinkParser.readFileIntoDrinkRepository();
//        List<Drink> drinks = drinkRepository.getDrinks();
//
//        if (type != null) {
//            if (Type.ALKOHOL_FREE.toString().equalsIgnoreCase(type)) {
//                drinks = drinks.stream()
//                        .filter(drink -> drink.getDrinkType() == Type.ALKOHOL_FREE)
//                        .collect(Collectors.toList());
//            } else if (Type.ALKOHOL.toString().equalsIgnoreCase(type)) {
//                drinks = drinks.stream()
//                        .filter(drink -> drink.getDrinkType() == Type.ALKOHOL)
//                        .collect(Collectors.toList());
//            }
//        }
//
//        if (glassType != null) {
//            if (GlassType.COCKTAIL.getLabel().equalsIgnoreCase(glassType)) {
//                drinks = drinks.stream()
//                        .filter(drink -> drink.getGlassType() == GlassType.COCKTAIL)
//                        .collect(Collectors.toList());
//            } else if (GlassType.SHOT.getLabel().equalsIgnoreCase(glassType)) {
//                drinks = drinks.stream()
//                        .filter(drink -> drink.getGlassType() == GlassType.SHOT)
//                        .collect(Collectors.toList());
//            } else if (GlassType.OLD_FASHIONED.getLabel().equalsIgnoreCase(glassType)) {
//                drinks = drinks.stream()
//                        .filter(drink -> drink.getGlassType() == GlassType.OLD_FASHIONED)
//                        .collect(Collectors.toList());
//            } else if (GlassType.COLLINS.getLabel().equalsIgnoreCase(glassType)) {
//                drinks = drinks.stream()
//                        .filter(drink -> drink.getGlassType() == GlassType.COLLINS)
//                        .collect(Collectors.toList());
//            }
//        }
//
//        if (drinkType != null) {
//
//        }
//
//        model.addAttribute("listOfDrinks", drinks);
//
//        return "search.html";
//    }

//    public String getFilters(Model model, @RequestParam("input") String item, @Param("sort") String sort) {
//        DrinkParser drinkParser = new DrinkParser();
//        Filter drinkFilter = new Filter();
//        DrinkRepository drinkRepository = drinkParser.readFileIntoDrinkRepository();
//        List<Drink> drinks = Search.searchItemsForQuery(drinkRepository, item);
//        Drink drink = new Drink();


//        if (drink.getDrinkType().equals(Type.ALKOHOL))
//            model.addAttribute("listOfDrinks", drinks);
//              model.addAttribute("sort", sort);
//        model.getAttribute()
//
//
//        drinkFilter.filter(drinks, menuMap);


//        return drinks.toString();
//}
}
