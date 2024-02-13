package com.eatnow.eatnow.utils;

import com.eatnow.eatnow.enums.FoodCategoryEnum;
import com.eatnow.eatnow.enums.UnitEnum;

public class MapperUtils {

    public static Boolean mapBoolean(Boolean bool) {
        return null == bool ? Boolean.FALSE : bool;
    }

    public static UnitEnum mapUnitEnum(String unit) {
        return switch (unit) {
            case "ml" -> UnitEnum.MILLILITRES;
            case "l" -> UnitEnum.LITRES;
            case "cl" -> UnitEnum.CENTILITRES;
            case null, default -> UnitEnum.DEFAULT;
        };
    }

    public static String mapUnit(UnitEnum unitEnum) {
        return switch (unitEnum) {
            case UnitEnum.MILLILITRES -> "ml";
            case UnitEnum.LITRES -> "l";
            case UnitEnum.CENTILITRES -> "cl";
            case null, default -> "";
        };
    }

    public static FoodCategoryEnum mapFoodCategoryEnum(String unit) {
        return switch (unit) {
            case "main" -> FoodCategoryEnum.MAIN;
            case "starter" -> FoodCategoryEnum.STARTER;
            case "dessert" -> FoodCategoryEnum.DESSERT;
            case null, default -> FoodCategoryEnum.DEFAULT;
        };
    }

    public static String mapFoodCategory(FoodCategoryEnum unitEnum) {
        return switch (unitEnum) {
            case FoodCategoryEnum.MAIN -> "main";
            case FoodCategoryEnum.STARTER -> "starter";
            case FoodCategoryEnum.DESSERT -> "dessert";
            case null, default -> "";
        };
    }
}
