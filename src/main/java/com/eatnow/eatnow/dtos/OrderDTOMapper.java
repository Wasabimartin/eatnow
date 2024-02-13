package com.eatnow.eatnow.dtos;


import com.eatnow.eatnow.model.Drink;
import com.eatnow.eatnow.model.Food;
import com.eatnow.eatnow.model.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderDTOMapper {

    public static OrderDTO convertToDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setTotalprice(order.getTotalprice());
        if (null != order.getDrinks()) {
            List<DrinkDTO> drinkDTOs = new ArrayList<>();
            for (Drink drink : order.getDrinks()) {
                drinkDTOs.add(DrinkDTOMapper.convertToDTO(drink));
            }
            orderDTO.setDrinks(drinkDTOs);
        }
        if (null != order.getFoods()) {
            List<FoodDTO> foodDTOS = new ArrayList<>();
            for (Food food : order.getFoods()) {
                foodDTOS.add(FoodDTOMapper.convertToDTO(food));
            }
            orderDTO.setFoods(foodDTOS);
        }
        return orderDTO;
    }
}