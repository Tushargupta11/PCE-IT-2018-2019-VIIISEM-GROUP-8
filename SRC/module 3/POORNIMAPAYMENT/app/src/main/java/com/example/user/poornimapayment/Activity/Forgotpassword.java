package com.example.user.poornimapayment.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.poornimapayment.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Forgotpassword extends AppCompatActivity {

    private EditText extEmail;
    private Button btnReset;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

        extEmail = findViewById(R.id.forgetEmail);
        btnReset = findViewById(R.id.btnPasswordReset);
        firebaseAuth = FirebaseAuth.getInstance();

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail = extEmail.getText().toString().trim();

                if (!isForgetEmail(userEmail)) {
                    extEmail.setError("Enter the valid e-mail!");
                    extEmail.requestFocus();
                    return;
                } else
                    btnReset.setText("SENDING E-MAIL...");
                firebaseAuth.sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Password Reset Email Sent!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Forgotpassword.this, Sign_in.class));
                            finish();
                        } else {
                            btnReset.setText("Reset Password");
                            Toast.makeText(getApplicationContext(), "Failed to Send Password Reset Email!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

        });
    }

    protected boolean isForgetEmail(String rege_email) {
        String USER_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(USER_PATTERN);
        Matcher matcher = pattern.matcher((CharSequence) rege_email);
        return matcher.matches();
    }
}
