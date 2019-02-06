package com.example.user.poornimapayment;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Forgotpassword extends AppCompatActivity {

    TextInputEditText txtUserName, txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

        txtUserName = (TextInputEditText) findViewById(R.id.idUserName);
        txtPassword = (TextInputEditText) findViewById(R.id.idPassword);
        Button btnLogin = (Button) findViewById(R.id.toastButton);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }
        private void login() {

            if (TextUtils.isEmpty(txtUserName.getText().toString().trim()) || TextUtils.isEmpty(txtPassword.getText().toString().trim())) {
                txtUserName.setError("Fields can't be Empty");
                txtPassword.setError("Fields can't be Empty");
            } else if (!emailValidator(txtUserName.getText().toString())) {
                txtUserName.setError("Please Enter Valid Email Address");
            } else {
                Toast.makeText(getApplicationContext(), "Password has changed.", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Forgotpassword.this, MainActivity.class);
                startActivity(intent);
            }
        }

        public boolean emailValidator(String email)
        {
            Pattern pattern;
            Matcher matcher;
            final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
            pattern = Pattern.compile(EMAIL_PATTERN);

            matcher = pattern.matcher(email);
            return  matcher.matches();

    }
}
