package com.d2c.webapp;

import com.d2c.webapp.Service.DrinkService;
import com.infoshareademy.Search;
import com.infoshareademy.data.DrinkParser;
import com.infoshareademy.domain.Drink;
import com.infoshareademy.domain.DrinkRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
        System.out.println("It works");
/*        DrinkService drinkService = new DrinkService();
        List<Drink> drinks = drinkService.getDrinkList();
                DrinkRepository drinkRepository = new DrinkParser().readFileIntoDrinkRepository();
        drinks = Search.searchItemsForQuery(drinkRepository, "man");*/


    }
}