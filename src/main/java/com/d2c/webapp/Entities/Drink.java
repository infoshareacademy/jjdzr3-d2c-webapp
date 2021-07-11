package com.d2c.webapp.Entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Drink {

    @Id
    private Long id;

    private String drinkName;

    private String preparationInstruction;

    private String drinkImg;


    public Drink() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        id = id;
    }

    public String getDrinkName() {
        return drinkName;
    }

    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
    }

    public String getPreparationInstruction() {
        return preparationInstruction;
    }

    public void setPreparationInstruction(String preparationInstruction) {
        this.preparationInstruction = preparationInstruction;
    }

    public String getDrinkImg() {
        return drinkImg;
    }

    public void setDrinkImg(String drinkImg) {
        this.drinkImg = drinkImg;
    }
}