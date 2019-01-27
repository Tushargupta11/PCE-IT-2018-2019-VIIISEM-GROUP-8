package com.example.user.poornimapayment.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.user.poornimapayment.Activity.Sign_in;
import com.example.user.poornimapayment.R;
import com.google.firebase.auth.FirebaseAuth;

public class Splash extends AppCompatActivity {
    Thread th;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        th = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(3000);
                    Intent it = new Intent(getApplicationContext(), Sign_in.class);
                    startActivity(it);
                    finish();
                } catch (Exception e) {

                }
            }
        });
        th.start();
    }
}
