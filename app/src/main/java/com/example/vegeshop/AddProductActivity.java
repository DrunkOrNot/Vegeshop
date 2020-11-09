package com.example.vegeshop;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AddProductActivity extends AppCompatActivity {
    Spinner spTrait;
    Button btnAdd;
    EditText inProductName;
    EditText inIngredientName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        spTrait = findViewById(R.id.spTrait);
        btnAdd = (Button)findViewById(R.id.btnAdd);
        inIngredientName = findViewById(R.id.inIngredientName);
        inProductName = findViewById(R.id.inProductName);

        ArrayList<String> traits = new ArrayList<>();
        for (Trait trait : Trait.values()){
            traits.add(String.valueOf(trait));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, traits);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTrait.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                Product product = new Product();
                product.ID = getIntent().getStringExtra("ProductID");
                product.Name = inProductName.getText();
                Ingredient ingredient = new Ingredient();
                ingredient.ID = String.valueOf(1);
                ingredient.Name = inIngredientName.getText();
                product.Ingredients.Add(ingredient);

                Database.PostData(product);

            }
        });
    }
}






