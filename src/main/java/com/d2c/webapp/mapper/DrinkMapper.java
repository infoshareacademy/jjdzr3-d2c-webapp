package com.d2c.webapp.mapper;

import com.infoshareademy.data.DrinkDAO;
import com.infoshareademy.domain.Drink;
import org.springframework.stereotype.Component;

@Component
public class DrinkMapper {

    public static DrinkDAO toDao(Drink drink) {
        DrinkDAO dao = new DrinkDAO();
        dao.setIdDrink(drink.getDrinkId());
        dao.setStrDrink(drink.getDrinkName());
        dao.setStrCategory(drink.getDrinkCategory());
        return dao;
    }
}

