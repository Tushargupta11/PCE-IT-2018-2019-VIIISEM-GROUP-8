package com.example.user.poornimapayment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash extends AppCompatActivity {

    Thread th;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        th= new Thread(new Runnable() {
            @Override
            public void run() {

                try{
                    Thread.sleep(2000);
                    Intent it=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(it);
                }
                catch (Exception e){

                }
            }
        });
        th.start();


    }
}
