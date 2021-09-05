package com.d2c.webapp.entities;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import javax.validation.executable.ValidateOnExecution;
@Transactional
@Entity(name = "DrinkEntity")
public class DrinkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long drinkid;

  //  @NotNull(message = "Field can't be empty")
    private String drink_name;

    @Type(type="text")
    private String preparation_instruction;

    private String drink_category;

    private String glass_type;
    private String ingredient_name_1;
    private String measure_1;
    private String ingredient_name_2;
    private String measure_2;
    private String ingredient_name_3;
    private String measure_3;
    private String ingredient_name_4;
    private String measure_4;
    private String ingredient_name_5;
    private String measure_5;
    private String type;

  //  @NotNull(message = "This field should have some picture")
    @Column(columnDefinition = "varchar(512)")
    private String drinkImg;

    @ValidateOnExecution
    public DrinkEntity() {
    }

    @Override
    public String toString() {
        return "DrinkEntity{" +
                "drinkid=" + drinkid +
                ", drink_name='" + drink_name + '\'' +
                ", preparation_instruction='" + preparation_instruction + '\'' +
                ", drink_category='" + drink_category + '\'' +
                ", glass_type='" + glass_type + '\'' +
                ", ingredient_name_1='" + ingredient_name_1 + '\'' +
                ", measure_1='" + measure_1 + '\'' +
                ", ingredient_name_2='" + ingredient_name_2 + '\'' +
                ", measure_2='" + measure_2 + '\'' +
                ", ingredient_name_3='" + ingredient_name_3 + '\'' +
                ", measure_3='" + measure_3 + '\'' +
                ", ingredient_name_4='" + ingredient_name_4 + '\'' +
                ", measure_4='" + measure_4 + '\'' +
                ", ingredient_name_5='" + ingredient_name_5 + '\'' +
                ", measure_5='" + measure_5 + '\'' +
                ", type='" + type + '\'' +
                ", drinkImg='" + drinkImg + '\'' +
                '}';
    }

    public Long getDrinkid() {
        return drinkid;
    }

    public void setDrinkid(Long drinkid) {
        this.drinkid = drinkid;
    }

    public String getDrink_name() {
        return drink_name;
    }

    public void setDrink_name(String drink_name) {
        this.drink_name = drink_name;
    }

    public String getPreparation_instruction() {
        return preparation_instruction;
    }

    public void setPreparation_instruction(String preparation_instruction) {
        this.preparation_instruction = preparation_instruction;
    }

    public String getDrink_category() {
        return drink_category;
    }

    public void setDrink_category(String drink_category) {
        this.drink_category = drink_category;
    }

    public String getGlass_type() {
        return glass_type;
    }

    public void setGlass_type(String glass_type) {
        this.glass_type = glass_type;
    }

    public String getIngredient_name_1() {
        return ingredient_name_1;
    }

    public void setIngredient_name_1(String ingredient_name_1) {
        this.ingredient_name_1 = ingredient_name_1;
    }

    public String getMeasure_1() {
        return measure_1;
    }

    public void setMeasure_1(String measure_1) {
        this.measure_1 = measure_1;
    }

    public String getIngredient_name_2() {
        return ingredient_name_2;
    }

    public void setIngredient_name_2(String ingredient_name_2) {
        this.ingredient_name_2 = ingredient_name_2;
    }

    public String getMeasure_2() {
        return measure_2;
    }

    public void setMeasure_2(String measure_2) {
        this.measure_2 = measure_2;
    }

    public String getIngredient_name_3() {
        return ingredient_name_3;
    }

    public void setIngredient_name_3(String ingredient_name_3) {
        this.ingredient_name_3 = ingredient_name_3;
    }

    public String getMeasure_3() {
        return measure_3;
    }

    public void setMeasure_3(String measure_3) {
        this.measure_3 = measure_3;
    }

    public String getIngredient_name_4() {
        return ingredient_name_4;
    }

    public void setIngredient_name_4(String ingredient_name_4) {
        this.ingredient_name_4 = ingredient_name_4;
    }

    public String getMeasure_4() {
        return measure_4;
    }

    public void setMeasure_4(String measure_4) {
        this.measure_4 = measure_4;
    }

    public String getIngredient_name_5() {
        return ingredient_name_5;
    }

    public void setIngredient_name_5(String ingredient_name_5) {
        this.ingredient_name_5 = ingredient_name_5;
    }

    public String getMeasure_5() {
        return measure_5;
    }

    public void setMeasure_5(String measure_5) {
        this.measure_5 = measure_5;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDrinkImg() {
        return drinkImg;
    }

    public void setDrinkImg(String drinkImg) {
        this.drinkImg = drinkImg;
    }
}