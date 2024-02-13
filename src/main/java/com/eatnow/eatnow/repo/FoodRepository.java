package com.eatnow.eatnow.repo;


import com.eatnow.eatnow.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface FoodRepository extends JpaRepository<Food, Long> {
}