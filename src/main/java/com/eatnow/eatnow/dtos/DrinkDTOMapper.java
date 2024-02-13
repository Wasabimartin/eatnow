package com.eatnow.eatnow.dtos;

import com.eatnow.eatnow.model.Drink;
import com.eatnow.eatnow.utils.MapperUtils;
import org.springframework.stereotype.Component;

@Component
public class DrinkDTOMapper {

    public static DrinkDTO convertToDTO(Drink drink) {
        DrinkDTO drinkDTO = new DrinkDTO();
        drinkDTO.setAlcohol(MapperUtils.mapBoolean(drink.getAlcohol()));
        drinkDTO.setSize(drink.getSize());
        drinkDTO.setName(drink.getName());
        drinkDTO.setId(drink.getId());
        drinkDTO.setUnit(MapperUtils.mapUnit(drink.getUnit()));
        drinkDTO.setPrice(drink.getPrice());
        return drinkDTO;
    }
}
