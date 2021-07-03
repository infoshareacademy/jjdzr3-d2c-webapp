package com.d2c.webapp.Service;


import com.infoshareademy.data.DrinkParser;
import com.infoshareademy.domain.Drink;
import com.infoshareademy.domain.DrinkRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DrinkService {

    private List<Drink> drinkList;

    public List<Drink> getDrinkList() {
        this.drinkList = new ArrayList<>();
        DrinkParser drinkParser = new DrinkParser();
        List<Drink> drinks = drinkParser.readFileIntoDrinkRepository().getDrinks();
        drinkList.addAll(drinks);
        return drinkList;
    }
}
