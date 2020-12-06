package com.example.vegeshop;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Field;
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
        for (Field trait : Trait.IngredientTrait.class.getFields()){
            String traitName = new String();
            try {
                traits.add((String.valueOf(trait.get(traitName))));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
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
                product.Name = inProductName.getText().toString();
                Ingredient ingredient = new Ingredient();
                ingredient.Name = inIngredientName.getText().toString();
                ingredient.Traits.add(spTrait.getSelectedItem().toString());
                product.Ingredients.add(ingredient);

                Database.PostData(product);
                Toast.makeText(
                        getApplicationContext(),
                        "Product added to database",
                        Toast.LENGTH_LONG)
                        .show();
                Thread timer = new Thread(){

                    @Override
                    public void run() {
                        try {
                            sleep(1000);
                            Intent intentCamera = new Intent(getApplicationContext(), CameraActivity.class);
                            startActivity(intentCamera);
                            finish();
                            super.run();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };
                timer.start();
            }
        });
    }
}






