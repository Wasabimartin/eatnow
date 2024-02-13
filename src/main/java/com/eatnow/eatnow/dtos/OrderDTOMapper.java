package com.eatnow.eatnow.dtos;


import com.eatnow.eatnow.model.Drink;
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
        return orderDTO;
    }
}