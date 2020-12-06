package com.example.vegeshop;

import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Trait {
    @Retention(RetentionPolicy.SOURCE)
    @StringDef({
            IngredientTrait.MEAT,
            IngredientTrait.DIARY,
            IngredientTrait.GLUTEN,
            IngredientTrait.HONEY,
            IngredientTrait.FISH,
            IngredientTrait.SEAFOOD,
            IngredientTrait.LACTOSE,
            IngredientTrait.NONE,
    })

    @interface IngredientTrait{
        public static final String MEAT = "Meat";
        public static final String DIARY = "Diary";
        public static final String GLUTEN = "Gluten";
        public static final String HONEY = "Honey";
        public static final String FISH = "Fish";
        public static final String SEAFOOD = "Seafood";
        public static final String LACTOSE = "Lactose";
        public static final String NONE = "None";
    }

private String trait;

public void setTrait(@IngredientTrait String trait) {
this.trait = trait;
}

    @IngredientTrait
    public String getTrait() {
           return trait;
        }
}
