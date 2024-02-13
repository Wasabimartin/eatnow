package com.eatnow.eatnow.model;

import com.eatnow.eatnow.dtos.DrinkDTO;
import com.eatnow.eatnow.utils.MapperUtils;
import org.springframework.stereotype.Component;

@Component
public class DrinkModelMapper {

    public static Drink convertToEntity(DrinkDTO drinkDTO) {
        Drink drink = new Drink();
        drink.setAlcohol(MapperUtils.mapBoolean(drinkDTO.isAlcohol()));
        drink.setSize(drinkDTO.getSize());
        drink.setName(drinkDTO.getName());
        drink.setId(drinkDTO.getId());
        drink.setUnit(MapperUtils.mapUnitEnum(drinkDTO.getUnit()));
        drink.setPrice(drinkDTO.getPrice());
        return drink;

    }
}
