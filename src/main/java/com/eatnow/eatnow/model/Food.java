package com.eatnow.eatnow.model;

import com.eatnow.eatnow.enums.FoodCategoryEnum;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "food")
public class Food {

    public Food() {
    }

    public Food(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price", scale = 2)
    private BigDecimal price;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private FoodCategoryEnum category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public FoodCategoryEnum getCategory() {
        return category;
    }

    public void setCategory(FoodCategoryEnum category) {
        this.category = category;
    }
}