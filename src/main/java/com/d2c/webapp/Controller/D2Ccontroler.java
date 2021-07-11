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

        return "/allDrinks.html";
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

    @GetMapping ("/AddDrink")
    public String addSingleDrink(Model model){

        model.addAttribute("drink", new Drink());
        model.addAttribute("ingredients", drinkService.getIngredientsList());

        return "Managements/AddDrink";
    }


    @PostMapping("/AddDrink")
    public String addDrink(@ModelAttribute Drink drink) {
        int id = 44;
        drink.setDrinkId(id);
        System.out.println(drink);
        return "redirect:/d2c/allDrinks";
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

/*    @RequestMapping("/search")
    public String getSearch(Model model,
                            @RequestParam(name = "input", required = false) String item,
                            @RequestParam(name = "type", required = false) Type type,
                            @RequestParam(name = "glassType", required = false) GlassType glassType,
                            @RequestParam(name = "category", required = false)  Category category
                            ) {
        DrinkParser drinkParser = new DrinkParser();
        DrinkRepository drinkRepository = drinkParser.readFileIntoDrinkRepository();
        List<Drink> drinks = drinkService.getDrinkList();
        if (item!= null) {
            drinks = Search.searchItemsForQuery(drinkRepository, item);
        }
        if (type != null) {
            drinks = Filter.filterByType(drinks, type);
        }

        if (glassType != null) {
            drinks = Filter.filterByGlassType(drinks, glassType);
        }

        if (category != null) {
            drinks = Filter.filterByCategory(drinks, category);
        }


        if (drinks.isEmpty()) {
            model.addAttribute("noDrinksFound", "No drinks found for given criteria: input too short or no drink available");
        }
        {
            model.addAttribute("listOfDrinks", drinks);
        }
        return "search.html";
    }*/


//    private static List<Drink> filterByType(List<Drink> drinks, Type type) {
//        return drinks.stream()
//                .filter(drink -> drink.getDrinkType() == type)
//                .collect(Collectors.toList());
//    }
//
//    private static List<Drink> filterByGlassType(List<Drink> drinks, GlassType glassType) {
//        return drinks.stream()
//                .filter(drink -> drink.getGlassType() == glassType)
//                .collect(Collectors.toList());
//    }
//
//    private static List<Drink> filterByCategory(List<Drink> drinks, Category category) {
//        return drinks.stream()
//                .filter(drink -> drink.getDrinkCategory() == category)
//                .collect(Collectors.toList());
//    }

}
