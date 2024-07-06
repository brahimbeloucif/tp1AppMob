package com.example.tp1appmob;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SimpleCalculator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_calculator);

        EditText num1 = findViewById(R.id.editTextText);
        EditText num2 = findViewById(R.id.editTextText1);
        TextView ress=findViewById(R.id.Result);
        Button showRes = findViewById(R.id.buttonRes);
        Button mins = findViewById(R.id.buttonMins);
        Button div = findViewById(R.id.buttonDiv);
        Button mul = findViewById(R.id.buttonMul);
        Button gotoCalculator = findViewById(R.id.Calculator);
        Button backToLogin = findViewById(R.id.backToPrev);



        showRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num1S=num1.getText().toString();
                String num2S=num2.getText().toString();
                int num1Int=Integer.parseInt(num1S);
                int num2Int=Integer.parseInt(num2S);
                int res= num1Int+num2Int;


                ress.setText("Result = "+res);
            }
        });
        mins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num1S=num1.getText().toString();
                String num2S=num2.getText().toString();
                int num1Int=Integer.parseInt(num1S);
                int num2Int=Integer.parseInt(num2S);
                int res= num1Int-num2Int;


                ress.setText("Result = "+res);
            }
        });
        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String num1S=num1.getText().toString();
                String num2S=num2.getText().toString();
                double num1Int=Double.parseDouble(num1S);

                double num2Int=Double.parseDouble(num2S);

                double res= num1Int/num2Int;


                ress.setText("Result = "+res);
            }
        });
        mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num1S=num1.getText().toString();
                String num2S=num2.getText().toString();
                int num1Int=Integer.parseInt(num1S);
                int num2Int=Integer.parseInt(num2S);
                int res= num1Int*num2Int;


                ress.setText("Result = "+res);
            }
        });
        gotoCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SimpleCalculator.this, Calculator.class);
                startActivity(intent);
                finish();
            }
        });
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Toast.makeText(SimpleCalculator.this,"use btn",Toast.LENGTH_SHORT).show();
            }
        });
        backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SimpleCalculator.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}