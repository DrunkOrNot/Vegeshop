package com.example.vegeshop;

import java.util.ArrayList;

public class IngredientList {

    public ArrayList<Ingredient> Ingredients = new ArrayList<>();

    public IngredientList(){

    }

    public void Add(Ingredient ingredient){
    Ingredients.add(ingredient);
    }
}
