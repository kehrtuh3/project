package com.example.projectapp;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Level1 extends AppCompatActivity implements View.OnClickListener {
    TextView txt_question;
    Button ans1, ans2, ans3, ans4;
    Button btn_confirm;
    int score = 0;
    int max = 6;
    int questionIndex = 0;
    String answer = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);


    TextView txt_lvl1 = findViewById(R.id.lvl_txt);
        txt_lvl1.setText(R.string.lvl1);

    Button btn_back = (Button) findViewById(R.id.button2_back);
        btn_back.setOnClickListener(view -> {
        try {
            Intent intent = new Intent(Level1.this, ChooseSubject.class);
            startActivity(intent);
            finish();
        } catch (Exception a) {
        }
    });
        txt_question = findViewById(R.id.txt_question);
        ans1 = findViewById(R.id.ans1);
        ans2 = findViewById(R.id.ans2);
        ans3 = findViewById(R.id.ans3);
        ans4 = findViewById(R.id.ans4);
        btn_confirm = findViewById(R.id.btn_confirm);
        ans1.setOnClickListener(this);
        ans2.setOnClickListener(this);
        ans3.setOnClickListener(this);
        ans4.setOnClickListener(this);
        btn_confirm.setOnClickListener(this);

        NewQuestion();
    }


    @Override
    public void onClick(View view) {
        ans1.setBackgroundResource(R.color.green_2);
        ans2.setBackgroundResource(R.color.green_2);
        ans3.setBackgroundResource(R.color.green_2);
        ans4.setBackgroundResource(R.color.green_2);

        Button startedBtn = (Button) view;



        if (startedBtn.getId() == R.id.btn_confirm) {
            if(answer.equals(QuestionAnswer.correctAnswer[questionIndex])){
                score++;
            }
            questionIndex++;
            NewQuestion();

        } else {
            answer = startedBtn.getText().toString();
            startedBtn.setBackgroundResource(R.color.green_1);

        }
    }

    void NewQuestion() {
        if(questionIndex==max){
            finishLevel();
            return;
        }
        txt_question.setText(QuestionAnswer.question[questionIndex]);
        ans1.setText(QuestionAnswer.allAnswers[questionIndex][0]);
        ans2.setText(QuestionAnswer.allAnswers[questionIndex][1]);
        ans3.setText(QuestionAnswer.allAnswers[questionIndex][2]);
        ans4.setText(QuestionAnswer.allAnswers[questionIndex][3]);
    }
    void finishLevel(){
        String lvlStatus = "";
        if(score > max*0.60){
            lvlStatus = "Победа! Вы доказали, что вы дилетант широкого профиля.";
        }else{
            lvlStatus = "Ой! Кажется, Вы недотягиваете до звания псевдоинтеллектуала.";
        }
        new AlertDialog.Builder(this)
                .setTitle(lvlStatus)
                .setMessage("Вы набрали " + score + " из " + max)
                .setPositiveButton("Попробовать снова",(dialogInterface, i) -> restartLvl())
                .show();

    }
    void restartLvl(){
        score = 0;
        questionIndex = 0;
        NewQuestion();
    }
}

