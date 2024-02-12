package com.eatnow.eatnow.dtos;

public class DrinkDTO {
        private String name;
        private boolean alcohol;
        private Double size;
        private String unit;

    public DrinkDTO() {
    }

    public DrinkDTO(String name, boolean alcohol, Double size, String unit) {
        this.name = name;
        this.alcohol = alcohol;
        this.size = size;
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAlcohol() {
        return alcohol;
    }

    public void setAlcohol(boolean alcohol) {
        this.alcohol = alcohol;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
