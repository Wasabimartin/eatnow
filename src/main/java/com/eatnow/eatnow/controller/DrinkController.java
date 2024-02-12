package com.eatnow.eatnow.controller;

import com.eatnow.eatnow.model.Drink;
import com.eatnow.eatnow.repo.DrinkRepository;
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

    private final DrinkRepository drinkRepository;

    public DrinkController(DrinkRepository drinkRepository) {
        this.drinkRepository = drinkRepository;
    }

    @GetMapping("/drinks")
    public List<Drink> getDrinks() {
        return drinkRepository.findAll();
    }

    @GetMapping("/drink/{id}")
    public Drink getDrink(@PathVariable Long id) {
        Optional<Drink> drink = drinkRepository.findById(id);
        return drink.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND)).getBody();
//        return drinkRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @PostMapping("/drink")
    public ResponseEntity createDrink(@RequestBody Drink drink) throws URISyntaxException {
        System.out.println("Request to create group: {}");
        System.out.println( drink);

        Drink savedDrink = drinkRepository.save(drink);
        return ResponseEntity.created(new URI("/api/drink/" + savedDrink.getId()))
                .body(savedDrink);
    }

    @PutMapping("/drink/{id}")
    public ResponseEntity updateDrink(@PathVariable Long id, @RequestBody Drink drink) {

        System.out.println("Request to update group: {}");
        System.out.println(drink);
        Drink result = drinkRepository.save(drink);
        return ResponseEntity.ok().body(result);



//        Drink drinkDrink = drinkRepository.findById(id).orElseThrow(RuntimeException::new);
//        drinkDrink.setName(drink.getName());
//        drinkDrink = drinkRepository.save(drink);
//        return ResponseEntity.ok(drinkDrink);
    }

    @DeleteMapping("/drink/{id}")
    public ResponseEntity deleteDrink(@PathVariable Long id) {
        drinkRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}