package com.example.user.poornimapayment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText editTextLoginEmail, editTextLoginPassword;
    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    TextView textview;
    Button loginButton, loginButton1, loginButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        editTextLoginEmail = findViewById(R.id.useremail);
        editTextLoginPassword = findViewById(R.id.userpassword);

        progressDialog = new ProgressDialog(this);

        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser()!= null){
            Intent intent = new Intent(MainActivity.this, NavigationHome.class);
            startActivity(intent);

        }
        loginButton = (findViewById(R.id.login));

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userLogin();
            }
        });

        loginButton1 = (findViewById(R.id.forgot));

        loginButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Forgotpassword.class);
                startActivity(intent);
            }
        });

        loginButton2 = (findViewById(R.id.button1));

        loginButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Signup.class);
                startActivity(intent);
            }
        });



    }

    @Override
    public void onBackPressed() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);

        startMain.addCategory(Intent.CATEGORY_HOME);

        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }





    private  void userLogin(){
        String email = editTextLoginEmail.getText().toString().trim();
        String password = editTextLoginPassword.getText().toString().trim();


        if (TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please enter Email", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please enter Password", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Logging In");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if(task.isSuccessful()){
                    Intent intent = new Intent(MainActivity.this, NavigationHome.class);
                    startActivity(intent);

                }
            }
        });
    }
}
