package com.eatnow.eatnow.model;

import com.eatnow.eatnow.dtos.OrderDTO;
import org.springframework.stereotype.Component;

@Component
public class OrderModelMapper {

    public static Order convertToEntity(OrderDTO orderDTO) {
        Order order = new Order();
        order.setId(orderDTO.getId());
        order.setQuantity(orderDTO.getQuantity());
        order.setTotalprice(orderDTO.getTotalprice());
        return order;

    }
}
