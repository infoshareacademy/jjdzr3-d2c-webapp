package com.d2c.webapp.service;


import com.d2c.webapp.entities.DrinkEntity;
import com.d2c.webapp.reposotirySQL.RepositoryDrinkSQL;
import com.infoshareademy.data.DrinkParser;
import com.infoshareademy.domain.Drink;
import com.infoshareademy.domain.DrinkRepository;

import com.infoshareademy.domain.Ingredient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DrinkService {

    @Autowired
    private RepositoryDrinkSQL repositoryDrinkSQL;

    private List<Drink> drinkList;

    public List<Drink> getDrinkList() {
        this.drinkList = new ArrayList<>();
        DrinkParser drinkParser = new DrinkParser();
        List<Drink> drinks = drinkParser.readFileIntoDrinkRepository().getDrinks();
        drinkList.addAll(drinks);
        return drinkList;
    }

    public void addAllDrinks(List<Drink> drinks){
        DrinkParser drinkParser = new DrinkParser();
        drinks = drinkParser.readFileIntoDrinkRepository().getDrinks();
        List <DrinkEntity> drinkEntities = new ArrayList<>();
        drinks.forEach(drink -> {repositoryDrinkSQL.save(new DrinkEntity());});

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

    @Valid
    public void addDrink(Drink drink){
        final DrinkEntity drinkEntity = new DrinkEntity();
        drinkEntity.setDrink_name(drink.getDrinkName());
        drinkEntity.setPreparation_instruction(drink.getPreparationInstruction());
        drinkEntity.setDrink_category(String.valueOf(drink.getDrinkCategory()));
        drinkEntity.setGlass_type(String.valueOf(drink.getGlassType()));
        drinkEntity.setIngredient_name_1(drink.getIngredients().get(0).getIngredientName());
        drinkEntity.setMeasure_1(drink.getIngredients().get(0).getMeasure());
        drinkEntity.setIngredient_name_2(drink.getIngredients().get(1).getIngredientName());
        drinkEntity.setMeasure_2(drink.getIngredients().get(1).getMeasure());
        drinkEntity.setIngredient_name_3(drink.getIngredients().get(2).getIngredientName());
        drinkEntity.setMeasure_3(drink.getIngredients().get(2).getMeasure());
        drinkEntity.setIngredient_name_4(drink.getIngredients().get(3).getIngredientName());
        drinkEntity.setMeasure_4(drink.getIngredients().get(3).getMeasure());
        drinkEntity.setIngredient_name_5(drink.getIngredients().get(4).getIngredientName());
        drinkEntity.setMeasure_5(drink.getIngredients().get(4).getMeasure());
        drinkEntity.setType(String.valueOf(drink.getDrinkType()));
        drinkEntity.setDrinkImg(drink.getDrinkImg());
        repositoryDrinkSQL.save(drinkEntity);
    }


    public void update(DrinkEntity drinkEntity){
        Drink drink = new Drink();
        drinkEntity.setDrink_name(drinkEntity.getDrink_name());
        drinkEntity.setPreparation_instruction(drinkEntity.getPreparation_instruction());
        drinkEntity.setDrink_category(drinkEntity.getDrink_category());
        drinkEntity.setGlass_type(drinkEntity.getGlass_type());
        drinkEntity.setIngredient_name_1(drinkEntity.getIngredient_name_1());
        drinkEntity.setMeasure_1(drinkEntity.getMeasure_1());
        drinkEntity.setIngredient_name_1(drinkEntity.getIngredient_name_2());
        drinkEntity.setMeasure_1(drinkEntity.getMeasure_2());
        drinkEntity.setIngredient_name_1(drinkEntity.getIngredient_name_3());
        drinkEntity.setMeasure_1(drinkEntity.getMeasure_3());
        drinkEntity.setIngredient_name_1(drinkEntity.getIngredient_name_4());
        drinkEntity.setIngredient_name_1(drinkEntity.getIngredient_name_5());
        drinkEntity.setMeasure_1(drinkEntity.getMeasure_4());
        drinkEntity.setMeasure_1(drinkEntity.getMeasure_5());
        drinkEntity.setType(drinkEntity.getType());
        drinkEntity.setDrinkImg(drinkEntity.getDrinkImg());
        System.out.println(drinkEntity);
        repositoryDrinkSQL.update(drinkEntity);
    }


    //TODO
    //

    public List<DrinkEntity> findAll(){
        Iterable<DrinkEntity> drinkEntities = repositoryDrinkSQL.findAll();
        return (List<DrinkEntity>) drinkEntities;
    }



    public List<DrinkEntity> findLast(){
        Iterable<DrinkEntity> drinkEntities = repositoryDrinkSQL.findLast();
        return (List<DrinkEntity>) drinkEntities;
    }

    public List<DrinkEntity> findByName(String name)    {
        Iterable<DrinkEntity> drinkEntities = repositoryDrinkSQL.findByName(name);
        return (List<DrinkEntity>) drinkEntities;
    }
    public void delete(DrinkEntity drinkEntity){

        repositoryDrinkSQL.delete(drinkEntity);

    }


    public void addDrinksToBB(){
        if(repositoryDrinkSQL.findAll().size()<20){
        List<Drink> drinksList = getDrinkList();
        int i = 0;
        for (Drink drink: getDrinkList())
        {
            addDrink(drink);
        }}else System.out.println("Archive exist");
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
    public List<Drink> getDrinkByName(String name) {
        List<Drink> drinkList = getDrinkList();
        List<Drink> filteredDrinks = drinkList
                .stream()
                .filter(a -> a.getDrinkName().toLowerCase().equals(name.toLowerCase()))
                .collect(Collectors.toList());
        System.out.println(filteredDrinks);
        return  filteredDrinks;
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
