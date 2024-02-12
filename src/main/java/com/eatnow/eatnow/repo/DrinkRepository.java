package com.eatnow.eatnow.repo;

import com.eatnow.eatnow.dtos.DrinkDTO;
import com.eatnow.eatnow.model.Drink;
import com.eatnow.eatnow.services.DrinkService;
import org.springframework.data.jpa.repository.JpaRepository;
public interface DrinkRepository extends JpaRepository<Drink, Long> {
}