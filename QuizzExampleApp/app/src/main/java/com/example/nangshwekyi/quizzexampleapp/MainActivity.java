package com.example.nangshwekyi.quizzexampleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.nangshwekyi.quizzexampleapp.data.DbHelper;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView txtQuestion, textView2, textView3, textView4;
    private FrameLayout frameLayout1, frameLayout2, frameLayout3;
    Button butNext;
    List<Question> quesList;
    int score = 0;
    int qid = 0;
    Question currentQ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DbHelper db = new DbHelper(this);
        quesList = db.getAllQuestions();
        currentQ = quesList.get(qid);
        txtQuestion = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
        textView4 = (TextView) findViewById(R.id.textView4);
        frameLayout1 = (FrameLayout) findViewById(R.id.ball_1);
        frameLayout2 = (FrameLayout) findViewById(R.id.ball_2);
        frameLayout3 = (FrameLayout) findViewById(R.id.ball_3);
        butNext = (Button) findViewById(R.id.button1);

        butNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frameLayout1 = (FrameLayout) findViewById(R.id.ball_1);
                frameLayout2 = (FrameLayout) findViewById(R.id.ball_2);
                frameLayout3 = (FrameLayout) findViewById(R.id.ball_3);

                TextView answer = (TextView) findViewById(frameLayout1.getId());
                TextView answer1 = (TextView) findViewById(frameLayout2.getId());
                TextView answer2 = (TextView) findViewById(frameLayout3.getId());

                Log.d("yourans", currentQ.getANSWER() + " " + answer.getText());
                if (currentQ.getANSWER().equals(answer.getText())) {
                    score++;
                    Log.d("score", "Your score" + score);
                }
                if (currentQ.getANSWER().equals(answer1.getText())) {
                    score++;
                    Log.d("score", "Your score" + score);
                }
                if (currentQ.getANSWER().equals(answer2.getText())) {
                    score++;
                    Log.d("score", "Your score" + score);
                }
                if (qid < 5) {
                    currentQ = quesList.get(qid);
                    setQuestionView();
                } else {
                    Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                    Bundle b = new Bundle();
                    b.putInt("score", score); //Your score
                    intent.putExtras(b); //Put your score to your next Intent
                    startActivity(intent);
                    finish();


                }

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    private void setQuestionView()
    {
        txtQuestion.setText(currentQ.getQUESTION());
        textView2.setText(currentQ.getOPTA());
        textView3.setText(currentQ.getOPTB());
        textView4.setText(currentQ.getOPTC());
        qid++;
    }

}