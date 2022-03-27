package com.example.auth;


import android.content.Intent;
import android.icu.text.SymbolTable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;


public class Signup extends AppCompatActivity {
    Button buttonSignup;
    TextView textViewLogin;
    TextInputEditText textInputEditTextFullname, textInputEditTextUsername, textInputEditTextPassword, textInputEditTextEmail, textInputEditTextClassCode, textInputEditTextUserType;
    //ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        textInputEditTextFullname = findViewById(R.id.fullname);
        textInputEditTextUsername = findViewById(R.id.username);
        textInputEditTextEmail = findViewById(R.id.email);
        textInputEditTextPassword = findViewById(R.id.password);
        textInputEditTextClassCode = findViewById(R.id.ClassCode);
        textInputEditTextUserType = findViewById(R.id.AccountType);
        buttonSignup = findViewById(R.id.buttonSignUp);
        textViewLogin = findViewById(R.id.loginText);
        //progressBar = findViewById(R.id.progress);

        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
                finish();
            }
        });
        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String fullname, username, password, email,accType, classCode;
                username = String.valueOf(textInputEditTextUsername.getText());
                password = String.valueOf(textInputEditTextPassword.getText());
                fullname = String.valueOf(textInputEditTextFullname.getText());
                email = String.valueOf(textInputEditTextEmail.getText());
                accType = String.valueOf(textInputEditTextUserType.getText());
                classCode = String.valueOf(textInputEditTextClassCode.getText());

                if (!fullname.equals("") && !username.equals("") && !password.equals("")) {

                    //progressBar.setVisibility(View.VISIBLE);

                    //Start ProgressBar first (Set visibility VISIBLE)
                    Handler handler = new Handler();
                    handler.post(() -> {
                        //Starting Write and Read data with URL
                        //Creating array for parameters
                        String[] field = new String[6];
                        field[0] = "fullname";
                        field[1] = "username";
                        field[2] = "password";
                        field[3] = "email";
                        field[4] = "accType";
                        field[5] = "classCode";
                        //Creating array for data
                        String[] data = new String[6];
                        data[0] = fullname;
                        data[1] = username;
                        data[2] = password;
                        data[3] = email;
                        data[4] = accType;
                        data[5] = classCode;

                        System.out.println (data[0] + data[4]);
                        PutData putData = new PutData("http://192.168.1.5/LogIn-SignUp-master/signup.php", "POST", field, data);
                        if (putData.startPut()) {
                            if (putData.onComplete()) {
                               // progressBar.setVisibility(View.GONE);
                                String result = putData.getResult();
                                if (result.equals("Sign up Success")) {
                                    Intent intent = new Intent(getApplicationContext(), Login.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                }

                                Log.i("PutData", result);
                            }
                        }
                        //End Write and Read data with URL
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "All fields required", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }


}