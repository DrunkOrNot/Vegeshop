package com.example.vegeshop;

import android.text.Editable;

import java.util.ArrayList;
import java.util.List;

public class Product {

    public String ID;
    public String Name;
    public List<Ingredient> Ingredients = new ArrayList<>();

    public Product(){
    }
}
