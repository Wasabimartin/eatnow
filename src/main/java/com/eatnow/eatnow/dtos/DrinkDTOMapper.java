package com.eatnow.eatnow.dtos;

import com.eatnow.eatnow.model.Drink;

public class DrinkDTOMapper {

    public static DrinkDTO convertToDTO(Drink drink) {
        DrinkDTO drinkDTO = new DrinkDTO();
        drinkDTO.setAlcohol(drink.getAlcohol());
        drinkDTO.setSize(drink.getSize());
        drinkDTO.setName(drink.getName());
        drinkDTO.setUnit(drink.getUnit());
        return drinkDTO;
    }
}
