package com.eatnow.eatnow.enums;

import java.util.HashMap;
import java.util.Map;

public enum FoodCategoryEnum {
    DEFAULT("", ""),

    STARTER("Starter", "starter"),
    MAIN("Main", "main"),
    DESSERT("Dessert", "dessert");


    public static final Map<String, FoodCategoryEnum> BY_LABEL = new HashMap<>();
    public static final Map<String, FoodCategoryEnum> BY_VALUE = new HashMap<>();

    static {
        for (FoodCategoryEnum e : values()) {
            BY_LABEL.put(e.label, e);
            BY_VALUE.put(e.value, e);
        }
    }

    public final String label;
    public final String value;

    FoodCategoryEnum(String label, String value) {
        this.label = label;
        this.value = value;
    }

    public static FoodCategoryEnum valueOfLabel(String label) {
        return BY_LABEL.get(label);
    }

    public static FoodCategoryEnum valueOfValue(String value) {
        return BY_VALUE.get(value);
    }

    public String getLabel() {
        return label;
    }

    public String getValue() {
        return value;
    }
}
