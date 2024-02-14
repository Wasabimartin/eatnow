package com.eatnow.eatnow.controller;

import com.eatnow.eatnow.dtos.FoodDTO;
import com.eatnow.eatnow.model.Food;
import com.eatnow.eatnow.services.FoodService;
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
public class FoodController {

    @Autowired
    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping("/foods")
    public List<FoodDTO> getFoods() {
        return foodService.findAll();
    }

    @GetMapping("/food/{id}")
    public FoodDTO getFood(@PathVariable Long id) {
        Optional<FoodDTO> food = foodService.findById(id);
        return food.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND)).getBody();
    }

    @PostMapping("/food")
    public ResponseEntity createFood(@RequestBody FoodDTO foodDTO) throws URISyntaxException {
        Food savedFood = foodService.save(foodDTO);
        return ResponseEntity.created(new URI("/api/food/" + savedFood.getId()))
                .body(savedFood);
    }

    @PutMapping("/food/{id}")
    public ResponseEntity updateFood(@PathVariable Long id, @RequestBody FoodDTO foodDTO) {
//        System.out.println("Request to update group: {}");
        Food result = foodService.save(foodDTO);
//        System.out.println(result);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/food/{id}")
    public ResponseEntity deleteFood(@PathVariable Long id) {
        foodService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}