package com.eatnow.eatnow.model;

import com.eatnow.eatnow.dtos.DrinkDTO;
import com.eatnow.eatnow.dtos.OrderDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderModelMapper {

    public static Order convertToEntity(OrderDTO orderDTO) {
        Order order = new Order();
        order.setId(orderDTO.getId());
        order.setTotalprice(orderDTO.getTotalprice());

        if (null != orderDTO.getDrinks()) {

            List<Drink> drinks = new ArrayList<>();
            for (DrinkDTO drinkDTO : orderDTO.getDrinks()) {
                drinks.add(DrinkModelMapper.convertToEntity(drinkDTO));
            }
            order.setDrinks(drinks);
        }

        return order;

    }
}
