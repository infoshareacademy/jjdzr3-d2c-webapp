package com.d2c.webapp.Controller;

import com.infoshareademy.Filter;
import com.infoshareademy.Menu;
import com.infoshareademy.data.DrinkParser;
import com.infoshareademy.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

import static com.infoshareademy.Search.*;

@Controller
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


    @RequestMapping(value = "/singleDrink")
    public String getDrink(Model model, @RequestParam("name") String name){

        DrinkParser drinkParser = new DrinkParser();
        DrinkRepository drinkRepository = drinkParser.readNewDataBase();
        List<Drink> filteredDrinks = drinkRepository.getDrinks()
                .stream()
                .filter(a -> a.getDrinkName().toLowerCase().contains(name))
                .collect(Collectors.toList());

        Drink drink = filteredDrinks.get(0);
        model.addAttribute("drink", drink);
        return "singleDrink";
    }

    private Optional<Drink> getDrink(String name) {
        DrinkParser drinkParser = new DrinkParser();
        DrinkRepository drinkRepository = drinkParser.readNewDataBase();
        List<Drink> filteredDrinks = drinkRepository.getDrinks()
                .stream()
                .filter(a -> a.getDrinkName().toLowerCase().contains(name))
                .collect(Collectors.toList());
        return  filteredDrinks.stream().findFirst();
    }
    @GetMapping ("/AddDrink")
    public String addSingleDrink(Model model){
        model.addAttribute("drink", new Drink());
        return "Managements/AddDrink";
    }
    @PostMapping("singleDrink")
    public String addDrink(@ModelAttribute Drink drink) {


        return "redirect:/allDrinks";
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

    @RequestMapping("/search")
    public String getSearch(Model model,
                            @RequestParam(name = "input", required = true) String item,
                            @RequestParam(name = "type", required = false) Type type,
                            @RequestParam(name = "glassType", required = false) GlassType glassType,
                            @RequestParam(name = "category", required = false)  Category category
                            ) {
        DrinkParser drinkParser = new DrinkParser();
        DrinkRepository drinkRepository = drinkParser.readFileIntoDrinkRepository();
        List<Drink> drinks = searchItemsForQuery(drinkRepository, item);

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
    }


    private static List<Drink> filterByType(List<Drink> drinks, Type type) {
        return drinks.stream()
                .filter(drink -> drink.getDrinkType() == type)
                .collect(Collectors.toList());
    }

    private static List<Drink> filterByGlassType(List<Drink> drinks, GlassType glassType) {
        return drinks.stream()
                .filter(drink -> drink.getGlassType() == glassType)
                .collect(Collectors.toList());
    }

    private static List<Drink> filterByCategory(List<Drink> drinks, Category category) {
        return drinks.stream()
                .filter(drink -> drink.getDrinkCategory() == category)
                .collect(Collectors.toList());
    }

}
