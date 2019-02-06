package com.example.user.poornimapayment.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.poornimapayment.MainActivity;
import com.example.user.poornimapayment.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Sign_in extends AppCompatActivity {

    private Button btnSignUp, btnForgetPassword, signin;
    private EditText signEmail;
    private TextInputEditText signPassword;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        signEmail = (EditText) findViewById(R.id.sign_inEmail);
        signPassword = (TextInputEditText) findViewById(R.id.sign_inPassword);
        signin = (Button) findViewById(R.id.signinButton);
        btnSignUp = (Button) findViewById(R.id.btnNewHere);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Sign_in.this, Sign_upActivity.class);
                startActivity(i);
            }
        });

        btnForgetPassword = (Button) findViewById(R.id.btnForgot);
        btnForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Sign_in.this, Forgotpassword.class);
                startActivity(i);
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login();
            }

        });
    }

    protected boolean isLoginEmail(String rege_email) {
        String USER_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(USER_PATTERN);
        Matcher matcher = pattern.matcher((CharSequence) rege_email);
        return matcher.matches();
    }

    protected boolean isLoginPassword(String rege_password) {
        if (rege_password != null && rege_password.length() > 6) {
            return true;
        }
        return false;
    }

    private void Login() {
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();

//        if (user!= null) {
//            finish();
//            startActivity(new Intent(Sign_in.this,HomeActivity.class));
//        }

       // If edittext is empty in that condition ......
        String email=signEmail.getText().toString().trim();
        String password=signPassword.getText().toString().trim();

        if(!isLoginEmail(email))
        {
            signEmail.setError("Enter The Valid E-mail ID");
            signEmail.requestFocus();
            return;
        }

        else if(!isLoginPassword(password))
        {
            signPassword.setError("Enter The Password");
            signPassword.requestFocus();
            return;
        }

        else
            signin.setText("JUST WAIT");

        firebaseAuth.signInWithEmailAndPassword(signEmail.getText().toString().trim(), signPassword.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
             if (task.isSuccessful()) {
                 startActivity(new Intent(Sign_in.this, MainActivity.class));
                 finish();
             } else {
                 Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT).show();
                 signin.setText("LOGIN");
             }
            }
        });

    }
}
