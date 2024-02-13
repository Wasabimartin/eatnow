package com.eatnow.eatnow.dtos;

import java.math.BigDecimal;
import java.util.List;

public class OrderDTO {
    private Long id;

    private BigDecimal totalprice;


    private List<DrinkDTO> drinks;

    public OrderDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(BigDecimal totalprice) {
        this.totalprice = totalprice;
    }



    public List<DrinkDTO> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<DrinkDTO> drinks) {
        this.drinks = drinks;
    }
}
