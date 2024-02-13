package com.eatnow.eatnow.dtos;

import com.eatnow.eatnow.model.Food;
import org.springframework.stereotype.Component;


@Component
public class FoodDTOMapper {

    public static FoodDTO convertToDTO(Food food) {
        FoodDTO foodDTO = new FoodDTO();
        foodDTO.setName(food.getName());
        foodDTO.setId(food.getId());
        foodDTO.setPrice(food.getPrice());
        return foodDTO;
    }
}
