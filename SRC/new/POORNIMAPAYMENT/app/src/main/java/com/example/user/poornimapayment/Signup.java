package com.example.user.poornimapayment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class Signup extends AppCompatActivity {

    Button loginButton2;
    RadioButton radioButton, radioButton1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        radioButton= (RadioButton) findViewById(R.id.radio1);
        radioButton1= (RadioButton) findViewById(R.id.radio2);
        radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioButton.isChecked()){
                    radioButton.getText();
                    radioButton1.setChecked(false);
                }
            }
        });
        radioButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioButton1.isChecked()){
                    radioButton1.getText();
                    radioButton.setChecked(false);
                }
            }
        });
        loginButton2 = (findViewById(R.id.signbutton));

        loginButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Signup.this, NavigationHome.class);
                startActivity(intent);
            }
        });

    }
}
