package com.eatnow.eatnow.model;

import com.eatnow.eatnow.dtos.FoodDTO;
import com.eatnow.eatnow.utils.MapperUtils;
import org.springframework.stereotype.Component;

@Component
public class FoodModelMapper {
    public static Food convertToEntity(FoodDTO foodDTO) {
        Food food = new Food();
        food.setName(foodDTO.getName());
        food.setId(foodDTO.getId());
        food.setPrice(foodDTO.getPrice());
        food.setDetails(foodDTO.getDetails());
        food.setCategory(MapperUtils.mapFoodCategoryEnum(foodDTO.getCategory()));
        food.setFile(foodDTO.getFile());
        return food;

    }
}
