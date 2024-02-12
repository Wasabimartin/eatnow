package com.eatnow.eatnow.services;

import com.eatnow.eatnow.dtos.DrinkDTO;
import com.eatnow.eatnow.dtos.DrinkDTOMapper;
import com.eatnow.eatnow.model.Drink;
import com.eatnow.eatnow.model.DrinkModelMapper;
import com.eatnow.eatnow.repo.DrinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DrinkService {
    @Autowired
    private DrinkRepository drinkRepository;

    public DrinkDTO getDrinkById(Long drinkID) {
        Drink drink = drinkRepository.findById(drinkID).orElse(null);
        return DrinkDTOMapper.convertToDTO(drink);
    }

    public void saveDrink(DrinkDTO drinkDTO) {
        Drink drink = DrinkModelMapper.convertToEntity(drinkDTO);
        drinkRepository.save(drink);
    }

    public List<DrinkDTO> findAll(){
            List<Drink> list = drinkRepository.findAll();
            List<DrinkDTO> dtoList = new ArrayList<>();
            for(Drink drink : list){
                dtoList.add(DrinkDTOMapper.convertToDTO(drink));
            }
            return dtoList;
    }

}
