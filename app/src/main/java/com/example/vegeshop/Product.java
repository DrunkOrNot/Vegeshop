package com.example.vegeshop;

import android.text.Editable;

public class Product {

    public String ID;
    public Editable Name;
    public IngredientList Ingredients;

    public Product(){
    Ingredients = new IngredientList();
    }
}
