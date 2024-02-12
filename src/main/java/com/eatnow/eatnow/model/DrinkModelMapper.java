package com.eatnow.eatnow.model;

import com.eatnow.eatnow.dtos.DrinkDTO;

public class DrinkModelMapper {

    public static Drink convertToEntity(DrinkDTO drinkDTO) {
        Drink drink = new Drink();
        drink.setAlcohol(drinkDTO.isAlcohol());
        drink.setSize(drinkDTO.getSize());
        drink.setUnit(drinkDTO.getUnit());
        drink.setName(drinkDTO.getName());
        return drink;

    }
}
