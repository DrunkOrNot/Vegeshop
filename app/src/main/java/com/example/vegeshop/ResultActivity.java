package com.example.vegeshop;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ResultActivity extends AppCompatActivity implements IUserDataChangeListener{

    private Button btnForceAdd;
    private TextView txtID;
    private TextView txtName;
    private RecyclerView rvIngredients;
    private RecyclerView.Adapter ingredientsAdapter;
    private RecyclerView.LayoutManager ingredientsLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        btnForceAdd=findViewById(R.id.btnForceAdd);
        txtID=findViewById(R.id.txtID);
        txtName = findViewById(R.id.txtProductName);
        txtID.setText(getIntent().getStringExtra("ProductID"));

        Database.AddListener(this);
        Database.GetData(getIntent().getStringExtra("ProductID"));

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

    protected void InitializeRecyclerView(Product product) {
        rvIngredients = findViewById(R.id.rvIngredients);
        rvIngredients.setHasFixedSize(true);
        ingredientsLayoutManager = new LinearLayoutManager(this);
        ingredientsAdapter = new IngredientViewAdapter(product);
        rvIngredients.setLayoutManager(ingredientsLayoutManager);
        rvIngredients.setAdapter(ingredientsAdapter);
        txtName.setText(product.Name);
    }

    @Override
    public void onUserDataReceivedFromDatabase(Product product) {
        if(product == null){
            Toast.makeText(
                    ResultActivity.this,
                    "Product not found in the database",
                    Toast.LENGTH_LONG)
                    .show();
        }else{
            InitializeRecyclerView(product);
        }
    }
}