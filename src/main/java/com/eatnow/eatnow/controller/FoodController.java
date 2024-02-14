package com.eatnow.eatnow.controller;

import com.eatnow.eatnow.dtos.FileDTO;
import com.eatnow.eatnow.dtos.FoodDTO;
import com.eatnow.eatnow.model.File;
import com.eatnow.eatnow.model.FileModelMapper;
import com.eatnow.eatnow.model.Food;
import com.eatnow.eatnow.services.FileService;
import com.eatnow.eatnow.services.FoodService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class FoodController {

    @Autowired
    private final FoodService foodService;
    @Autowired
    private final FileService fileService;

    public FoodController(FoodService foodService, FileService fileService) {
        this.foodService = foodService;
        this.fileService = fileService;
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

//    @PutMapping("/food/{id}")
//    public ResponseEntity updateFood(@PathVariable Long id, @RequestBody FoodDTO foodDTO) {
//        Food result = foodService.save(foodDTO);
//        return ResponseEntity.ok().body(result);
//    }

    @PutMapping("/food/{id}")
    public ResponseEntity updateFood(
            @PathVariable Long id,
            @RequestParam("foodDTO") String foodDTOJson,
            @RequestPart(value = "file", required = false) MultipartFile file)
            throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        FoodDTO foodDTO = objectMapper.readValue(foodDTOJson, FoodDTO.class);
        if (file != null) {
//            File fileEntity = FileModelMapper.convertToEntity(file, null);
            File fileEntity = fileService.uploadFile(file);
            foodDTO.setFile(fileEntity);
        }
        Food result = foodService.save(foodDTO);
        return ResponseEntity.ok().body(result);
    }


    @DeleteMapping("/food/{id}")
    public ResponseEntity deleteFood(@PathVariable Long id) {
        foodService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}