package com.eatnow.eatnow.dtos;

import com.eatnow.eatnow.model.Food;
import com.eatnow.eatnow.utils.MapperUtils;
import org.springframework.stereotype.Component;


@Component
public class FoodDTOMapper {

    public static FoodDTO convertToDTO(Food food) {
        FoodDTO foodDTO = new FoodDTO();
        foodDTO.setName(food.getName());
        foodDTO.setId(food.getId());
        foodDTO.setPrice(food.getPrice());
        foodDTO.setCategory(MapperUtils.mapFoodCategory(food.getCategory()));
        return foodDTO;
    }
}
