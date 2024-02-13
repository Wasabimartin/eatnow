package com.eatnow.eatnow.dtos;

import java.math.BigDecimal;

public class FoodDTO {
    private Long id;
    private String name;

    private BigDecimal price;

    public FoodDTO() {
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

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

}
