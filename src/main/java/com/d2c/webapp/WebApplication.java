package com.d2c.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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