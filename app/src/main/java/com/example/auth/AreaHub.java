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

import java.sql.SQLOutput;

public class AreaHub extends AppCompatActivity {


    public static String classCode = "classCode";
    Button Area1;
    Button Area2;
    Button Area3;
    Button Area4;
    TextView textViewSignUp;
    TextInputEditText textInputEditTextUsername, textInputEditTextPassword;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area);

        Area1 = findViewById(R.id.Area1);
        Area2 = findViewById(R.id.Area2);
        Area3 = findViewById(R.id.Area3);
        Area4 = findViewById(R.id.Area4);

        Handler handler = new Handler();
        handler.post(() -> {
            //Starting Write and Read data with URL
            //Creating array for parameters
            String[] field = new String[1];

            field[0] = "username";


            //Creating array for data
            String[] data = new String[1];

            data[0] = Login.user;

            PutData putData = new PutData("http://192.168.1.5/LogIn-SignUp-master/getClassCode.php", "POST", field,  data );
            System.out.println(data[0]);
            if (putData.startPut()) {
                if (putData.onComplete()) {
                    classCode=putData.getResult();
                    System.out.println(classCode);
                }

            }});



        Area1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();


            }});
        Area2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), Quiz2.class);
                startActivity(intent);
                finish();


            }});
        Area3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), Quiz3.class);
                startActivity(intent);
                finish();


            }});
        Area4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), Quiz4.class);
                startActivity(intent);
                finish();


            }});
    }}

