package com.eatnow.eatnow.model;

import jakarta.persistence.*;

@Entity
@Table(name = "drink")
public class Drink {

    public Drink() {
    }

    public Drink(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    // getter, setters, contructors

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