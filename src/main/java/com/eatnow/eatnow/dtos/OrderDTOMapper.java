package com.eatnow.eatnow.dtos;


import com.eatnow.eatnow.model.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderDTOMapper {

    public static OrderDTO convertToDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setQuantity(order.getQuantity());
        orderDTO.setTotalprice(order.getTotalprice());
        return orderDTO;
    }
}