package com.example.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView question, questionNumber;
    private Button optionBtn1, optionBtn2, optionBtn3, optionBtn4;
    private ArrayList<QuizModal> quizModalArrayList;
    Random random;
    int currentScore = 0;
    int questionAttempted = 1;
    int currentPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        question = findViewById(R.id.questionAttempted);
        questionNumber = findViewById(R.id.question);
        optionBtn1 = findViewById(R.id.option1);
        optionBtn2 = findViewById(R.id.option2);
        optionBtn3 = findViewById(R.id.option3);
        optionBtn4 = findViewById(R.id.option4);
        quizModalArrayList = new ArrayList<>();
        random = new Random();
        getQuizQuestion(quizModalArrayList);
        currentPos = random.nextInt(quizModalArrayList.size());
        setDataToViews(currentPos);
        optionBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quizModalArrayList.get(currentPos).getAnswer().trim().toLowerCase(Locale.ROOT).equals(optionBtn1.getText().toString().trim().toLowerCase())) {
                    currentScore++;
                }
                questionAttempted++;
                currentPos = random.nextInt(quizModalArrayList.size());
                setDataToViews(currentPos);
            }
        });

    }

    private void setDataToViews(int currentPos) {

        questionNumber.setText("Questions attempted : " + questionAttempted + " /10");
        question.setText(quizModalArrayList.get(currentPos).getQuestion());
        optionBtn1.setText(quizModalArrayList.get(currentPos).getOption1());
        optionBtn2.setText(quizModalArrayList.get(currentPos).getOption2());
        optionBtn3.setText(quizModalArrayList.get(currentPos).getOption3());
        optionBtn4.setText(quizModalArrayList.get(currentPos).getOption4());
    }

    private void getQuizQuestion(ArrayList<QuizModal> quizModalArrayList) {

        quizModalArrayList.add(new QuizModal("1. Which of the following keyword is not associated with IF statement?", "ELSE", "THEN", "ELSE IF", "WHEN", "WHEN"));
        quizModalArrayList.add(new QuizModal("2. An ELSE statement must be preceded by ___ statement in Java.", "IF", "ELSE IF", "IF or ELSE IF", "NONE", "IF or ELSE IF"));
        quizModalArrayList.add(new QuizModal("3. An IF or ELSE IF statement accepts ___ as input before branching.", "boolean", "int", "char", "float", "boolean"));
        quizModalArrayList.add(new QuizModal("1. An IF statement in Java is also a ___ statement.", "boolean", "conditional", "iterative", "optional", "conditional"));
    }
}