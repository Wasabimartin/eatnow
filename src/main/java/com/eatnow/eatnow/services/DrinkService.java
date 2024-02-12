package com.eatnow.eatnow.services;

import com.eatnow.eatnow.dtos.DrinkDTO;
import com.eatnow.eatnow.model.Drink;
import com.eatnow.eatnow.repo.DrinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DrinkService {
    @Autowired
    private DrinkRepository drinkRepository;

    public DrinkDTO getUserById(Long drinkID) {
        Drink drink = drinkRepository.findById(drinkID).orElse(null);
        return convertToDTO(drink);
    }

    public void saveUser(DrinkDTO drinkDTO) {
        Drink drink = convertToEntity(drinkDTO);
        drinkRepository.save(drink);
    }

    private DrinkDTO convertToDTO(Drink drink) {
        DrinkDTO drinkDTO = new DrinkDTO();
        drinkDTO.setAlcohol(drink.getAlcohol());
        drinkDTO.setSize(drink.getSize());
        drinkDTO.setName(drink.getName());
        drinkDTO.setUnit(drink.getUnit());
        return null;
    }

    private Drink convertToEntity(DrinkDTO drinkDTO) {
        Drink drink = new Drink();
        drink.setAlcohol(drinkDTO.isAlcohol());
        drink.setSize(drinkDTO.getSize());
        drink.setUnit(drinkDTO.getUnit());
        drink.setName(drinkDTO.getName());
        return null;

    }
}
