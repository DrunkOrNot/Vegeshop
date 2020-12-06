package com.example.vegeshop;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ResultActivity extends AppCompatActivity {

    private Button btnForceAdd;
    private TextView txtID;
    private TextView txtName;
    private RecyclerView rvHistory;
    private RecyclerView.Adapter historyAdapter;
    private RecyclerView.LayoutManager historyLayoutManager;

    //TODO - remove dummy product
    Product dummyProduct = new Product();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        btnForceAdd=findViewById(R.id.btnForceAdd);
        txtID=findViewById(R.id.txtID);
        txtName = findViewById(R.id.txtProductName);
        InitializeRecyclerView();

        txtID.setText(getIntent().getStringExtra("ProductID"));
        txtName.setText(dummyProduct.Name);

       btnForceAdd.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, AddProductActivity.class);
                intent.putExtra("ProductID", getIntent().getStringExtra("ProductID"));
                startActivity(intent);
            }
        });
    }

    protected void InitializeRecyclerView() {
        // TODO - remove all dummies and replace with product actually received from database
        Ingredient dummyIngredient = new Ingredient();
        dummyIngredient.Name = "Karkowka";
        dummyIngredient.Traits.add("Fish");
        dummyIngredient.Traits.add("Honey");
        dummyIngredient.Traits.add("Meat");
        dummyIngredient.Traits.add("Diary");
        dummyIngredient.Traits.add("Seafood");
        dummyIngredient.Traits.add("Lactose");
        Ingredient dummyIngredient2 = new Ingredient();
        dummyIngredient2.Name = "Makaron";
        dummyIngredient2.Traits.add("Diary");
        dummyProduct.ID = "00";
        dummyProduct.Name = "Ziemniak";
        dummyProduct.Ingredients.add(dummyIngredient);
        dummyProduct.Ingredients.add(dummyIngredient2);
        // TODO - end remove

        rvHistory = findViewById(R.id.rvIngredients);
        rvHistory.setHasFixedSize(true);
        historyLayoutManager = new LinearLayoutManager(this);
        historyAdapter = new IngredientViewAdapter(dummyProduct);

        rvHistory.setLayoutManager(historyLayoutManager);
        rvHistory.setAdapter(historyAdapter);
    }
}