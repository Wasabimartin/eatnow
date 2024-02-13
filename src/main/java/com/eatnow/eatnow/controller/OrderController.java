package com.eatnow.eatnow.controller;

import com.eatnow.eatnow.dtos.OrderDTO;
import com.eatnow.eatnow.model.Order;
import com.eatnow.eatnow.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public List<OrderDTO> getOrders() {
        return orderService.findAll();
    }

    @GetMapping("/order/{id}")
    public OrderDTO getOrder(@PathVariable Long id) {
        Optional<OrderDTO> order = orderService.findById(id);
        return order.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND)).getBody();
    }

    @PostMapping("/order")
    public ResponseEntity createOrder(@RequestBody OrderDTO orderDTO) throws URISyntaxException {
        Order savedOrder = orderService.save(orderDTO);
        return ResponseEntity.created(new URI("/api/order/" + savedOrder.getId()))
                .body(savedOrder);
    }

    @PutMapping("/order/{id}")
    public ResponseEntity updateOrder(@PathVariable Long id, @RequestBody OrderDTO orderDTO) {
        Order result = orderService.save(orderDTO);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/order/{id}")
    public ResponseEntity deleteOrder(@PathVariable Long id) {
        orderService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}