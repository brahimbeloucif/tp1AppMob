package com.example.tp1appmob;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText email,password;
    int numberTty=5;

String emailFixe="a";
TextView wrong;
String passFixe="a";
Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        wrong=findViewById(R.id.wrongPassOrEmail);

        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberTty--;

                login();
            }
        });

    }
    void login() {
        String em = email.getText().toString();
        String ps = password.getText().toString();

        if (em.equals(emailFixe) && ps.equals(passFixe)) {
            Intent intent = new Intent(MainActivity.this, SecondScreen.class);
            startActivity(intent);
            finish();
        } else {
            wrong.setText("Wrong password or email");
            if (numberTty >= 1) {
                Toast.makeText(MainActivity.this, "Wrong password or email, " + numberTty + " attempts left", Toast.LENGTH_SHORT).show();
            } else {
                btnLogin.setEnabled(false);
                startCountDown();
            }
        }
    }

    void startCountDown() {
        new CountDownTimer(30000, 1000) {
            public void onTick(long time) {
                wrong.setText("Try again in " + time / 1000 + " seconds...");
            }

            public void onFinish() {
                btnLogin.setEnabled(true);
                btnLogin.setBackgroundColor(Color.parseColor("#4CAF50"));
                btnLogin.setTextColor(Color.WHITE);
                numberTty = 5;
                wrong.setText("");
            }
        }.start();
    }




}
