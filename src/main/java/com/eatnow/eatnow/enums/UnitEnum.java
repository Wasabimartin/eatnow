package com.eatnow.eatnow.enums;

import java.util.HashMap;
import java.util.Map;

public enum UnitEnum {

    MILLILITRES("Milliliters (ml)", "ml"),
    LITRES("Liters (l)", "l");

    public static final Map<String, UnitEnum> BY_LABEL = new HashMap<>();
    public static final Map<String, UnitEnum> BY_VALUE = new HashMap<>();

    static {
        for (UnitEnum e : values()) {
            BY_LABEL.put(e.label, e);
            BY_VALUE.put(e.value, e);
        }
    }

    public final String label;
    public final String value;

    private UnitEnum(String label, String value) {
        this.label = label;
        this.value = value;
    }

    public static UnitEnum valueOfLabel(String label) {
        return BY_LABEL.get(label);
    }

    public static UnitEnum valueOfValue(String value) {
        return BY_VALUE.get(value);
    }

    public String getLabel() {
        return label;
    }

    public String getValue() {
        return value;
    }
}
