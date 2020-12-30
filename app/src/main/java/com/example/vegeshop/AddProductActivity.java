package com.example.vegeshop;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AddProductActivity extends AppCompatActivity {
    Spinner spTrait;
    Button btnAdd;
    Button btnAddIngredient;
    Button btnRmvIngredient;
    Button btnAddTrait;
    Button btnRmvTrait;
    EditText inProductName;
    EditText inIngredientName;
    ListView lstAddedIngredients;

    ArrayList<Ingredient> addedIngredients = new ArrayList<>();

    int currentIngredientIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        spTrait = findViewById(R.id.spTrait);
        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnAddIngredient = findViewById(R.id.btnAddIngredient);
        btnRmvIngredient = findViewById(R.id.btnRmvIngredient);
        btnAddTrait = findViewById(R.id.btnAddTrait);
        btnRmvTrait = findViewById(R.id.btnRmvTrait);
        inIngredientName = findViewById(R.id.inIngredientName);
        inProductName = findViewById(R.id.inProductName);
        lstAddedIngredients = findViewById(R.id.lstAddedIngredients);

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

        RefreshIngredientsListView();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                String productName = inProductName.getText().toString();
                if (productName.isEmpty() || productName.equals(R.string.add_product)) {
                    Toast.makeText(
                            AddProductActivity.this,
                            "Invalid product name",
                            Toast.LENGTH_LONG)
                            .show();
                    return;
                }
                Product product = new Product();
                product.ID = getIntent().getStringExtra("ProductID");
                product.Name = productName;
                product.Ingredients.addAll(addedIngredients);

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

        btnAddIngredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ingredientName = inIngredientName.getText().toString();
                if (ingredientName.isEmpty() || ingredientName.equals(R.string.add_ingredient)) {
                    Toast.makeText(
                            AddProductActivity.this,
                            "Invalid ingredient name",
                            Toast.LENGTH_LONG)
                            .show();
                    return;
                }
                Ingredient ingredient = new Ingredient();
                ingredient.Name = ingredientName;
                addedIngredients.add(ingredient);
                currentIngredientIndex = addedIngredients.indexOf(ingredient);
                RefreshIngredientsListView();
            }
        });

        btnRmvIngredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    addedIngredients.remove(currentIngredientIndex);
                }
                catch (IndexOutOfBoundsException e) {
                    Toast.makeText(
                            AddProductActivity.this,
                            "Something went wrong. Cannot delete item.",
                            Toast.LENGTH_LONG)
                            .show();
                }

                currentIngredientIndex = addedIngredients.size() - 1;
                RefreshIngredientsListView();
            }
        });

        btnAddTrait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    addedIngredients.get(currentIngredientIndex).Traits.add(spTrait.getSelectedItem().toString());
                }
                catch (IndexOutOfBoundsException e) {
                    Toast.makeText(
                            AddProductActivity.this,
                            "Something went wrong. Cannot add trait to this ingredient.",
                            Toast.LENGTH_LONG)
                            .show();
                }
                RefreshIngredientsListView();
            }
        });

        btnRmvTrait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Ingredient ingredient = addedIngredients.get(currentIngredientIndex);
                    ingredient.Traits.remove(ingredient.Traits.size() - 1);
                }
                catch (IndexOutOfBoundsException e) {
                    Toast.makeText(
                            AddProductActivity.this,
                            "Something went wrong. Cannot remove trait from this ingredient.",
                            Toast.LENGTH_LONG)
                            .show();
                }
                RefreshIngredientsListView();
            }
        });
    }

    List<HashMap<String, String>> SerializeIngredientsList() {
        List<HashMap<String, String>> serializedAddedIngredients = new ArrayList<>();
        for(Ingredient ingredient : addedIngredients) {
            HashMap<String, String> resultMap = new HashMap<>();

            StringBuilder traitsListBuilder = new StringBuilder();
            for (String trait : ingredient.Traits) {
                traitsListBuilder.append(trait);
                traitsListBuilder.append("\n");
            }

            resultMap.put("First", ingredient.Name);
            resultMap.put("Second", traitsListBuilder.toString());
            serializedAddedIngredients.add(resultMap);
        }

        return serializedAddedIngredients;
    }

    void RefreshIngredientsListView() {
        SimpleAdapter addedIngredientsAdapter = new SimpleAdapter(this, SerializeIngredientsList(),
                R.layout.ingredient_listitem, new String[]{"First", "Second"},
                new int[]{R.id.txtIngredientName, R.id.txtIngredientTrait});

        lstAddedIngredients.setAdapter(addedIngredientsAdapter);
    }
}






