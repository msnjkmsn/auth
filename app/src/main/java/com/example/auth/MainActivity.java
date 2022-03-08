package com.example.auth;

import static com.example.auth.Login.USERID;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public static String Total = "user";
    private TextView question, questionNumber;
    private Button optionBtn1, optionBtn2, optionBtn3, optionBtn4,Username,TotalScore,Area,Leaderboard;
    private ArrayList<QuizModal> quizModalArrayList;
    Random random;
    int currentScore = 0;
    int questionAttempted = 1;
    int currentPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Username = findViewById(R.id.Username);
        TotalScore = findViewById(R.id.TotalScore);
        Area = findViewById(R.id.levelselect);
        question = findViewById(R.id.question);
        questionNumber = findViewById(R.id.questionAttempted);
        optionBtn1 = findViewById(R.id.option1);
        optionBtn2 = findViewById(R.id.option2);
        optionBtn3 = findViewById(R.id.option3);
        optionBtn4 = findViewById(R.id.option4);
        quizModalArrayList = new ArrayList<>();
        random = new Random();

        Area.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AreaHub.class);
                startActivity(intent);
                finish();
            }
        });

        TotalScore.setText("EXP: "+MainActivity.Total);
        Username.setText(Login.user);
        getQuizQuestion(quizModalArrayList);
        currentPos = random.nextInt(quizModalArrayList.size());
        setDataToViews(currentPos);
        optionBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quizModalArrayList.get(currentPos).getAnswer().trim().toLowerCase().equals(optionBtn1.getText().toString().trim().toLowerCase())) {
                    currentScore++;
                }
                questionAttempted++;
                currentPos = random.nextInt(quizModalArrayList.size());
                setDataToViews(currentPos);
            }
        });
        optionBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quizModalArrayList.get(currentPos).getAnswer().trim().toLowerCase().equals(optionBtn2.getText().toString().trim().toLowerCase())) {
                    currentScore++;
                }
                questionAttempted++;
                currentPos = random.nextInt(quizModalArrayList.size());
                setDataToViews(currentPos);
            }
        });
        optionBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quizModalArrayList.get(currentPos).getAnswer().trim().toLowerCase().equals(optionBtn3.getText().toString().trim().toLowerCase())) {
                    currentScore++;
                }
                questionAttempted++;
                currentPos = random.nextInt(quizModalArrayList.size());
                setDataToViews(currentPos);
            }
        });
        optionBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quizModalArrayList.get(currentPos).getAnswer().trim().toLowerCase().equals(optionBtn4.getText().toString().trim().toLowerCase())) {
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
        if(questionAttempted==10){
            String[] field = new String[3];

            field[0] = "userID";
            field[1] = "score";
            field[2] = "quizID";


            String[] data = new String[3];
            int quizID= 1;
            String userID= USERID;
            data[0] = String.valueOf(userID) ;
            data[1] = String.valueOf(currentScore);
            data[2] = String.valueOf(quizID);
            System.out.println("aaaaaa");
            PutData putData = new PutData("http://192.168.1.7/LogIn-SignUp-master/getScore.php", "POST", field,  data );
            if (putData.startPut()) {
                if (putData.onComplete()) {
                    PutData putData2 = new PutData("http://192.168.1.7/LogIn-SignUp-master/getTotalScore.php", "POST", field,  data );

                    if (putData2.startPut()) {
                        if (putData2.onComplete()) {
                            MainActivity.Total=putData2.getResult();
                            TotalScore.setText("EXP: "+MainActivity.Total);
                        }}

                }}
            showSheet();
        }
        question.setText(quizModalArrayList.get(currentPos).getQuestion());
        optionBtn1.setText(quizModalArrayList.get(currentPos).getOption1());
        optionBtn2.setText(quizModalArrayList.get(currentPos).getOption2());
        optionBtn3.setText(quizModalArrayList.get(currentPos).getOption3());
        optionBtn4.setText(quizModalArrayList.get(currentPos).getOption4());
    }

    private void getQuizQuestion(ArrayList<QuizModal> quizModalArrayList) {

        quizModalArrayList.add(new QuizModal("Which of the following keyword is not associated with IF statement?", "ELSE", "THEN", "ELSE IF", "WHEN", "WHEN"));
        quizModalArrayList.add(new QuizModal("An ELSE statement must be preceded by ___ statement in Java.", "IF", "ELSE IF", "IF or ELSE IF", "NONE", "IF or ELSE IF"));
        quizModalArrayList.add(new QuizModal("An IF or ELSE IF statement accepts ___ as input before branching.", "boolean", "int", "char", "float", "boolean"));
        quizModalArrayList.add(new QuizModal("An IF statement in Java is also a ___ statement.", "boolean", "conditional", "iterative", "optional", "conditional"));
    }

    private void showSheet(){
        BottomSheetDialog bottomSheetDialog  = new BottomSheetDialog(MainActivity.this);
        View bottomSheetView  = getLayoutInflater().from(getApplicationContext()).inflate(R.layout.score,(LinearLayout)findViewById(R.id.LLScore));
        TextView scoreTV  =  bottomSheetView.findViewById(R.id.score);
        Button restartQuiz = bottomSheetView.findViewById(R.id.restartQuiz);
        scoreTV.setText("Your Score is "+currentScore+ "/10");
        restartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPos = random.nextInt(quizModalArrayList.size());
                setDataToViews(currentPos);
                questionAttempted = 1 ;
                currentScore = 0;
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
    }
}