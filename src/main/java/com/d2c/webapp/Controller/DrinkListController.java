package com.d2c.webapp.Controller;

import com.d2c.webapp.Service.DrinkService;
import com.infoshareademy.domain.Drink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DrinkListController {


     @Autowired
    DrinkService drinkService;

    @GetMapping(value = "/showAllDrinks")
    public String getShowAllDrinks (Model model) {
        List<Drink> drinkList = drinkService.getDrinkList();
        model.addAttribute("Drinks", drinkList);

        return "subSites/showAllDrinks";
    }


    @GetMapping(value = "/showAllDrinks2")
    public String getShowAllDrinks2() {
        return "subSites/showAllDrinks2";
    }

    @GetMapping(value = "/worklist")
    public String getWorkList(Model model) {
        List<Drink> drinkList = drinkService.getDrinkList();
        model.addAttribute("Drinks", drinkList);
        return "subSites/WorkList";
    }
}
