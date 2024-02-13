package com.eatnow.eatnow.repo;


import com.eatnow.eatnow.model.Drink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface DrinkRepository extends JpaRepository<Drink, Long> {
}