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
    private RecyclerView rvHistory;
    private RecyclerView.Adapter historyAdapter;
    private RecyclerView.LayoutManager historyLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        btnForceAdd=findViewById(R.id.btnForceAdd);
        txtID=findViewById(R.id.txtID);
        txtID.setText(getIntent().getStringExtra("ProductID"));

        InitializeRecyclerView();


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
        // TODO - delete all dummies and replace with product actually received from database
        Product dummyProduct = new Product();
        Ingredient dummyIngredient = new Ingredient();
        dummyIngredient.Name = "Karkowka";
        dummyIngredient.Traits.add("Fish");
        dummyIngredient.Traits.add("Honey");
        Ingredient dummyIngredient2 = new Ingredient();
        dummyIngredient2.Name = "Makaron";
        dummyIngredient2.Traits.add("Diary");
        dummyProduct.ID = "00";
        dummyProduct.Name = "Ziemniak";
        dummyProduct.Ingredients.add(dummyIngredient);
        dummyProduct.Ingredients.add(dummyIngredient2);

        rvHistory = findViewById(R.id.rvIngredients);
        rvHistory.setHasFixedSize(true);
        historyLayoutManager = new LinearLayoutManager(this);
        historyAdapter = new IngredientViewAdapter(dummyProduct);

        rvHistory.setLayoutManager(historyLayoutManager);
        rvHistory.setAdapter(historyAdapter);
    }
}