package com.example.user.poornimapayment.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.user.poornimapayment.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Sign_upActivity extends AppCompatActivity {

    private EditText etUsername, etContact, etEmail ,etPassword, etConfirmPassword ;
    /*private TextInputEditText etPassword, etConfirmPassword;*/
    private RadioButton rbMale, rbFemale;
    private Button btnSignup;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);


        etUsername = findViewById(R.id.userName);
        etContact = findViewById(R.id.userContact);
        etEmail = findViewById(R.id.userEmail);
        etPassword = findViewById(R.id.userPassword);
        etConfirmPassword =  findViewById(R.id.userConfirmPassword);
        rbMale = (RadioButton) findViewById(R.id.male);
        rbFemale = (RadioButton) findViewById(R.id.female);

        btnSignup = (Button) findViewById(R.id.signupButton);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignUp();
            }
        });
    }

    protected boolean isValidUsername(String Username) {
        String USER_PATTERN = "[A-z-Z ]+";
        Pattern pattern = Pattern.compile(USER_PATTERN);
        Matcher matcher = pattern.matcher((CharSequence) Username);
        return matcher.matches();
    }

    protected boolean isValidNumber(String rege_mobile_no) {
        if (rege_mobile_no != null && rege_mobile_no.length() == 10) {
            return true;
        }
        return false;
    }

    protected boolean isValidEmail(String mEmailid) {
        String USER_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(USER_PATTERN);
        Matcher matcher = pattern.matcher((CharSequence) mEmailid);
        return matcher.matches();
    }

    protected boolean isValidPassword(String mPassword) {
        if (mPassword != null && mPassword.length() > 6) {
            return true;
        }
        return false;
    }

    protected boolean isValidConfirmPassword(String mConfirmPassword) {
        if (mConfirmPassword != null && mConfirmPassword.length() > 6) {
            return true;
        }
        return false;
    }

    private void SignUp() {
        firebaseAuth = FirebaseAuth.getInstance();
        final String username = etUsername.getText().toString().trim();
        final String contact_number = etContact.getText().toString().trim();
        final String email = etEmail.getText().toString().trim();
        final String password = etPassword.getText().toString().trim();
        final String confirmpassword = etConfirmPassword.getText().toString().trim();

        if (!isValidUsername(username)) //Condition so that no edit-text will remain empty
        {
            etUsername.setError("Enter The Valid Username");
            etUsername.requestFocus();
            return;
        } else if (!isValidNumber(contact_number)) {
            etContact.setError("Enter The Valid Mobile Number");
            etContact.requestFocus();
            return;
        } else if (!isValidEmail(email)) {
            etEmail.setError("Enter The Valid E-mail ID");
            etEmail.requestFocus();
            return;
        } else if (!isValidPassword(password)) {
            etPassword.setError("Enter The Correct Password");
            etPassword.requestFocus();
        } else if (!isValidConfirmPassword(confirmpassword)) {
            etConfirmPassword.setError("Enter The Correct Confirm Password");
            etConfirmPassword.requestFocus();
        } else if (!password.equals(confirmpassword)) {
            etConfirmPassword.setError("Password Does't Match");
            etConfirmPassword.requestFocus();
        } else btnSignup.setText("PROCESSING");

        firebaseAuth.createUserWithEmailAndPassword(etEmail.getText().toString().trim(),
                etPassword.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Registration Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Sign_upActivity.this, Sign_in.class);
                    startActivity(intent);
                } else {
//                    Toast.makeText(getApplicationContext(), "Registration Failed", Toast.LENGTH_SHORT).show();
                    btnSignup.setText("SIGN UP");
                }

            }

        });
    }
}
