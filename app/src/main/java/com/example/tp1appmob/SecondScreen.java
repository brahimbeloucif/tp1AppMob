package com.example.tp1appmob;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SecondScreen extends AppCompatActivity {
Button btnLogout;
    Button btnGoToCalculator;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_screen);
        btnGoToCalculator=findViewById(R.id.btnGoToCalculator);
        btnLogout = findViewById(R.id.btnLogOut);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondScreen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                 Toast.makeText(SecondScreen.this,"use btn",Toast.LENGTH_SHORT).show();
            }
        });
        btnGoToCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondScreen.this, SimpleCalculator.class);
                startActivity(intent);
                finish();
            }
        });
        }





}