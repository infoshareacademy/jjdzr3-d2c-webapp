package com.d2c.webapp.service;

import com.d2c.webapp.entities.DrinkEntity;
import com.d2c.webapp.reposotirySQL.RepositoryDrinkSQL;
import com.infoshareademy.data.DrinkParser;
import com.infoshareademy.domain.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DrinkService {

    @Autowired
    private RepositoryDrinkSQL repositoryDrinkSQL;

    private List<Drink> drinkList;

    private static final Logger LOGGER = LogManager.getLogger(DrinkService.class);

    public List<Drink> getDrinkList() {
        this.drinkList = new ArrayList<>();
        DrinkParser drinkParser = new DrinkParser();
        List<Drink> drinks = drinkParser.readFileIntoDrinkRepository().getDrinks();
        drinkList.addAll(drinks);
        LOGGER.debug("Received drink list" + drinks);
        return drinkList;
    }

    public List<Drink> getDrinkListfromDB() {
        drinkList.removeAll(drinkList);
        for (DrinkEntity drink : repositoryDrinkSQL.findAll()) {
            drinkList.add(changeDrinkEntityToDrink(drink));
        }
        return drinkList;
    }

    public Drink getIngredientsList() {
        Ingredient ingredient = new Ingredient();
        List<Ingredient> ingredients = new ArrayList<>();
        for (int i = 0; i <= 4; i++) {
            ingredients.add(ingredient);
        }
        Drink drink = new Drink();
        drink.setIngredients(ingredients);
        LOGGER.debug("Received ingredients list" + drink);
        return drink;
    }

    @Valid
    public void addDrink(Drink drink) {
        final DrinkEntity drinkEntity = changeDrinkToDrinkEntity(drink);
        LOGGER.debug("Recipe for a drink ready for saving to Data Base: " + drinkEntity);
        repositoryDrinkSQL.save(drinkEntity);
    }

    @Valid
    public Drink changeDrinkEntityToDrink(DrinkEntity drinkEntity) {
        Drink drink = new Drink();
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient(drinkEntity.getIngredient_name_1(), drinkEntity.getMeasure_1()));
        ingredients.add(new Ingredient(drinkEntity.getIngredient_name_2(), drinkEntity.getMeasure_2()));
        ingredients.add(new Ingredient(drinkEntity.getIngredient_name_3(), drinkEntity.getMeasure_3()));
        ingredients.add(new Ingredient(drinkEntity.getIngredient_name_4(), drinkEntity.getMeasure_4()));
        ingredients.add(new Ingredient(drinkEntity.getIngredient_name_5(), drinkEntity.getMeasure_5()));
        drink.setDrinkId(drinkEntity.getDrinkid().intValue());
        drink.setIngredients(ingredients);
        drink.setDrinkImg(drinkEntity.getDrinkImg());
        drink.setDrinkName(drinkEntity.getDrink_name());
        drink.setDrinkType(Type.valueOf(drinkEntity.getType()));
        drink.setDrinkCategory(Category.valueOf(drinkEntity.getDrink_category()));
        drink.setPreparationInstruction(drinkEntity.getPreparation_instruction());
        drink.setGlassType(GlassType.valueOf(drinkEntity.getGlass_type()));
        return drink;
    }

    @Valid
    public DrinkEntity changeDrinkToDrinkEntity(Drink drink) {
        DrinkEntity drinkEntity = new DrinkEntity();
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
        return drinkEntity;
    }

    public DrinkEntity update(DrinkEntity drinkEntity) {
        drinkEntity.setDrinkid(drinkEntity.getDrinkid());
        drinkEntity.setDrink_name(drinkEntity.getDrink_name());
        drinkEntity.setPreparation_instruction(drinkEntity.getPreparation_instruction());
        drinkEntity.setDrink_category(drinkEntity.getDrink_category());
        drinkEntity.setGlass_type(drinkEntity.getGlass_type());
        drinkEntity.setIngredient_name_1(drinkEntity.getIngredient_name_1());
        drinkEntity.setMeasure_1(drinkEntity.getMeasure_1());
        drinkEntity.setIngredient_name_2(drinkEntity.getIngredient_name_2());
        drinkEntity.setMeasure_2(drinkEntity.getMeasure_2());
        drinkEntity.setIngredient_name_3(drinkEntity.getIngredient_name_3());
        drinkEntity.setMeasure_3(drinkEntity.getMeasure_3());
        drinkEntity.setIngredient_name_4(drinkEntity.getIngredient_name_4());
        drinkEntity.setIngredient_name_5(drinkEntity.getIngredient_name_5());
        drinkEntity.setMeasure_4(drinkEntity.getMeasure_4());
        drinkEntity.setMeasure_5(drinkEntity.getMeasure_5());
        drinkEntity.setType(drinkEntity.getType());
        drinkEntity.setDrinkImg(drinkEntity.getDrinkImg());
        System.out.println(drinkEntity);
        repositoryDrinkSQL.update(drinkEntity);
        return drinkEntity;
    }

    public List<DrinkEntity> findAll() {
        Iterable<DrinkEntity> drinkEntities = repositoryDrinkSQL.findAll();
        return (List<DrinkEntity>) drinkEntities;
    }

    public List<DrinkEntity> findLast() {
        Iterable<DrinkEntity> drinkEntities = repositoryDrinkSQL.findLast();
        return (List<DrinkEntity>) drinkEntities;
    }

    public List<DrinkEntity> findByName(String name) {
        Iterable<DrinkEntity> drinkEntities = repositoryDrinkSQL.findByName(name);
        return (List<DrinkEntity>) drinkEntities;
    }

    public List<DrinkEntity> findByNameObjectToDelete(String name) {
        List<DrinkEntity> drinkEntities = repositoryDrinkSQL.findByNameObjectToDelete(name);
        if (drinkEntities.size() > 1) {
            deleteByName(name);
            LOGGER.info(drinkEntities.get(0));
        } else {
            System.out.println("Drink name was change. New drink was add to the drinks book");
        }
        return drinkEntities;
    }

    public void deleteByName(String name) {
        final DrinkEntity drinkEntity = repositoryDrinkSQL.findByName(name).get(0);
        repositoryDrinkSQL.delete(drinkEntity);
        LOGGER.info(name + " Removed");
    }

    public void addDrinksToBB() {
        if (repositoryDrinkSQL.findAll().size() < 20) {
            List<Drink> drinksList = getDrinkList();
            int i = 0;
            for (Drink drink : getDrinkList()) {
                addDrink(drink);
            }
        } else System.out.println("Archive exist");
    }

    public List<Drink> searchItemsForQuery(String input) {

        if (input.length() < 3) {
            return Collections.emptyList();
        } else {
            List<Drink> filteredDrinks = drinkList.stream().filter((a) -> {
                return a.getDrinkName().toLowerCase().contains(input.toLowerCase());
            }).collect(Collectors.toList());

            if (filteredDrinks.size() == 0) {
                LOGGER.info(" No items found for given search criteria");

                return Collections.emptyList();
            } else {
                return filteredDrinks;
            }
        }
    }

    public List<Drink> getDrinkByName(String name) {
        List<Drink> drinkList = getDrinkListfromDB();
        List<Drink> filteredDrinks = drinkList
                .stream()
                .filter(a -> a.getDrinkName().toLowerCase().equals(name.toLowerCase()))
                .collect(Collectors.toList());
        LOGGER.info("Filtered list of drinks: " + filteredDrinks);
        return filteredDrinks;
    }

    public Page<Drink> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Drink> list;

        if (drinkList.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, drinkList.size());
            list = drinkList.subList(startItem, toIndex);
        }
        Page<Drink> drinkPage =
                new PageImpl<Drink>(list, PageRequest.of(currentPage, pageSize), drinkList.size());
        LOGGER.debug("Actual drink page = " + drinkPage);
        return drinkPage;
    }

    public void setDrinkList(List<Drink> drinkList) {
        this.drinkList = drinkList;
    }
}
