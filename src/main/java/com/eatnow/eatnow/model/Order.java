package com.eatnow.eatnow.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    public Order() {
    }

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "totalprice", scale = 2)
    private BigDecimal totalprice;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "drink_order",
            joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "drink_id",
                    referencedColumnName = "id"))
    private List<Drink> drinks;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "food_order",
            joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "food_id",
                    referencedColumnName = "id"))
    private List<Food> foods;

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

    public List<Drink> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<Drink> drinks) {
        this.drinks = drinks;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }
}