package com.d2c.webapp.Service;


import com.infoshareademy.data.DrinkParser;
import com.infoshareademy.domain.Drink;
import com.infoshareademy.domain.DrinkRepository;

import com.infoshareademy.domain.Ingredient;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DrinkService {

    private List<Drink> drinkList;

    private List<Ingredient> ingredientsList;

    public List<Drink> getDrinkList() {
        this.drinkList = new ArrayList<>();
        DrinkParser drinkParser = new DrinkParser();
        List<Drink> drinks = drinkParser.readFileIntoDrinkRepository().getDrinks();
        drinkList.addAll(drinks);
        return drinkList;
    }
    public Drink getIngredientsList(){
        Ingredient ingredient = new Ingredient();
        List<Ingredient> ingredients = new ArrayList<>();
        for (int i=0; i<=4; i++) {ingredients.add(ingredient);
                    }
        Drink drink = new Drink();
        drink.setIngredients(ingredients);
        return drink;
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
    public Page<Drink> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Drink>list;

        if (drinkList.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, drinkList.size());
            list = drinkList.subList(startItem, toIndex);
        }
        Page<Drink> drinkPage =
                new PageImpl<Drink>(list, PageRequest.of(currentPage, pageSize), drinkList.size());
        return drinkPage;
    }
    public void setDrinkList(List<Drink> drinkList) {
        this.drinkList = drinkList;
    }
}
