package com.eatnow.eatnow.controller;

import com.eatnow.eatnow.dtos.DrinkDTO;
import com.eatnow.eatnow.model.Drink;
import com.eatnow.eatnow.repo.DrinkRepository;
import com.eatnow.eatnow.services.DrinkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class DrinkController {

    private final DrinkService drinkService;
    private final DrinkRepository drinkRepository;


    public DrinkController(DrinkService drinkService, DrinkRepository drinkRepository) {
        this.drinkService = drinkService;
        this.drinkRepository = drinkRepository;
    }

    @GetMapping("/drinks")
    public List<DrinkDTO> getDrinks() {
        return drinkService.findAll();
    }

    @GetMapping("/drink/{id}")
    public DrinkDTO getDrink(@PathVariable Long id) {
        Optional<DrinkDTO> drink = drinkService.findById(id);
        System.out.println(drink);
        return drink.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND)).getBody();
    }

    @PostMapping("/drink")
    public ResponseEntity createDrink(@RequestBody DrinkDTO drinkDTO) throws URISyntaxException {
        System.out.println("Request to create group: {}");
        System.out.println( drinkDTO);

        Drink savedDrink = drinkService.save(drinkDTO);
        System.out.println(savedDrink);
        return ResponseEntity.created(new URI("/api/drink/" + savedDrink.getId()))
                .body(savedDrink);
    }

    @PutMapping("/drink/{id}")
    public ResponseEntity updateDrink(@PathVariable Long id, @RequestBody DrinkDTO drinkDTO) {
        System.out.println("Request to update group: {}");

        Drink result = drinkService.save(drinkDTO);
        System.out.println(result);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/drink/{id}")
    public ResponseEntity deleteDrink(@PathVariable Long id) {
        drinkService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}