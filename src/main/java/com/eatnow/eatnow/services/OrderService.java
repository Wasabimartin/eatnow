package com.eatnow.eatnow.services;

import com.eatnow.eatnow.dtos.OrderDTO;
import com.eatnow.eatnow.dtos.OrderDTOMapper;
import com.eatnow.eatnow.model.Order;
import com.eatnow.eatnow.model.OrderModelMapper;
import com.eatnow.eatnow.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public OrderDTO getOrderById(Long id) {
        Order order = orderRepository.findById(id).orElse(null);
        return OrderDTOMapper.convertToDTO(order);
    }

    public void saveOrder(OrderDTO orderDTO) {
        Order order = OrderModelMapper.convertToEntity(orderDTO);
        orderRepository.save(order);
    }

    public List<OrderDTO> findAll() {
        List<Order> list = orderRepository.findAll();
        List<OrderDTO> dtoList = new ArrayList<>();
        for (Order order : list) {
            dtoList.add(OrderDTOMapper.convertToDTO(order));
        }
        return dtoList;
    }

    public Optional<OrderDTO> findById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.map(OrderDTOMapper::convertToDTO);
    }

    public Order save(OrderDTO dto) {
        Order order = OrderModelMapper.convertToEntity(dto);
        orderRepository.save(order);
        return order;

    }

    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }

}
