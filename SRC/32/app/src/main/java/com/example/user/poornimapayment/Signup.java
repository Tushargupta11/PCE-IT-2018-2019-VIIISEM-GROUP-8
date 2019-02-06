package com.example.user.poornimapayment;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {

    Button loginButton2;
    RadioButton radioButton, radioButton1;
    EditText editTextName, editTextMobile, editTextEmail, editTextPassword, editTextConfirmPassword;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        editTextName = findViewById(R.id.name);
        editTextMobile = findViewById(R.id.mobile);

        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        editTextConfirmPassword = findViewById(R.id.confirmpassword);

        firebaseAuth = FirebaseAuth.getInstance();

        databaseReference = FirebaseDatabase.getInstance().getReference();

        editTextName = (EditText) findViewById(R.id.name);
        editTextMobile = (EditText) findViewById(R.id.mobile);
        loginButton2 = (Button) findViewById(R.id.signbutton);


        radioButton = (RadioButton) findViewById(R.id.radio1);
        radioButton1 = (RadioButton) findViewById(R.id.radio2);
        radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioButton.isChecked()) {
                    radioButton.getText();
                    radioButton1.setChecked(false);
                }
            }
        });
        radioButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioButton1.isChecked()) {
                    radioButton1.getText();
                    radioButton.setChecked(false);
                }
            }
        });

        loginButton2 = (findViewById(R.id.signbutton));

        loginButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               saveUserInformation();

                if (validate()) {


                    String user_email = editTextEmail.getText().toString().trim();
                    String user_password = editTextPassword.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(Signup.this, "Registration Successful", Toast.LENGTH_SHORT).show();

                                Intent intent= new Intent(Signup.this, NavigationHome.class);
                                startActivity(intent);
                            }

                            else {
                                Toast.makeText(Signup.this, "Registration Failed", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }




            }
        });

    }
    private void saveUserInformation(){
        String name = editTextName.getText().toString().trim();
        String mob = editTextMobile.getText().toString().trim();

       FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
       databaseReference= firebaseDatabase.getReference(firebaseAuth.getUid());
        UserInformation userInformation = new UserInformation(name, mob);
        databaseReference.setValue(userInformation);


    }


    public boolean validate() {
        Boolean result = false;

        String name = editTextName.getText().toString();
        String mobile = editTextMobile.getText().toString();
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();
        String confirmpassword = editTextConfirmPassword.getText().toString();

        if (name.isEmpty() && email.isEmpty() && mobile.isEmpty() && password.isEmpty() && confirmpassword.isEmpty()) {
            Toast.makeText(this, "Enter all the entries", Toast.LENGTH_SHORT).show();
        } else {
            result = true;
        }

        return result;


    }
}
