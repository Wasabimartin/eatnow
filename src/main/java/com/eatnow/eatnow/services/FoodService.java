package com.eatnow.eatnow.services;

import com.eatnow.eatnow.dtos.FoodDTO;
import com.eatnow.eatnow.dtos.FoodDTOMapper;
import com.eatnow.eatnow.model.Food;
import com.eatnow.eatnow.model.FoodModelMapper;
import com.eatnow.eatnow.repo.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FoodService {
    @Autowired
    private FoodRepository foodRepository;

    public FoodDTO getFoodById(Long foodID) {
        Food food = foodRepository.findById(foodID).orElse(null);
        return FoodDTOMapper.convertToDTO(food);
    }

    public void saveFood(FoodDTO foodDTO) {
        Food food = FoodModelMapper.convertToEntity(foodDTO);
        foodRepository.save(food);
    }

    public List<FoodDTO> findAll() {
        List<Food> list = foodRepository.findAll();
        List<FoodDTO> dtoList = new ArrayList<>();
        for (Food food : list) {
            dtoList.add(FoodDTOMapper.convertToDTO(food));
        }
        return dtoList;
    }

    public Optional<FoodDTO> findById(Long id) {
        Optional<Food> food = foodRepository.findById(id);
        return food.map(FoodDTOMapper::convertToDTO);
    }

    public Food save(FoodDTO foodDTO) {
        System.out.println(foodDTO.getFile());
        Food food = FoodModelMapper.convertToEntity(foodDTO);
        foodRepository.save(food);
        return food;

    }

    public void deleteById(Long id) {
        foodRepository.deleteById(id);
    }

}
