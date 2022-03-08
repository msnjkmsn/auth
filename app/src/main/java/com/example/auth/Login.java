package com.example.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class Login extends AppCompatActivity {
    Button buttonLogin;
    TextView textViewSignUp;
    TextInputEditText textInputEditTextUsername, textInputEditTextPassword;
    ProgressBar progressBar;
    public static String USERID = "ID";
    public static String user = "user";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        textInputEditTextPassword = findViewById(R.id.passwordLogin);
        textInputEditTextUsername = findViewById(R.id.usernameLogin);
        buttonLogin = findViewById(R.id.buttonLogin);
        textViewSignUp = findViewById(R.id.signUpText);
        progressBar = findViewById(R.id.progress);

        textViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Signup.class);
                startActivity(intent);
                finish();
            }
        });
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String fullname, username, password, email;
                username = String.valueOf(textInputEditTextUsername.getText());
                password = String.valueOf(textInputEditTextPassword.getText());


                if (!username.equals("") && !password.equals("")) {

                    progressBar.setVisibility(View.VISIBLE);

                    //Start ProgressBar first (Set visibility VISIBLE)
                    Handler handler = new Handler();
                    handler.post(() -> {
                        //Starting Write and Read data with URL
                        //Creating array for parameters
                        String[] field = new String[2];

                        field[0] = "username";
                        field[1] = "password";

                        //Creating array for data
                        String[] data = new String[2];

                        data[0] = username;
                        data[1] = password;

//local machine ipv4 used to inform the emulator of the localhost address//
                        PutData putData = new PutData("http://192.168.1.7/LogIn-SignUp-master/login.php", "POST", field, data);
                        if (putData.startPut()) {
                            if (putData.onComplete()) {
                                System.out.println("Username is " +data[0] +"Pass is " + data[1]);
                                progressBar.setVisibility(View.GONE);
                                String result = putData.getResult();
                                if (result.equals("Login Success")) {
                                    user = username;
                                    PutData putData2 = new PutData("http://192.168.1.7/LogIn-SignUp-master/getID.php", "POST", field,  data );

                                    if (putData2.startPut()) {
                                        if (putData2.onComplete()) {
                                            USERID=putData2.getResult();
                                            Intent intent = new Intent(getApplicationContext(), AreaHub.class);
                                            startActivity(intent);
                                            finish();
                                       }}


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