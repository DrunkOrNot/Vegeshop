package com.example.vegeshop;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    Button btnForceAdd;
    TextView txtID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        btnForceAdd=findViewById(R.id.btnForceAdd);
        txtID=findViewById(R.id.txtID);

        txtID.setText(getIntent().getStringExtra("ProductID"));

       btnForceAdd.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                ResultActivity.this.startActivity(new Intent(ResultActivity.this, AddProductActivity.class));
            }
        });
    }
}