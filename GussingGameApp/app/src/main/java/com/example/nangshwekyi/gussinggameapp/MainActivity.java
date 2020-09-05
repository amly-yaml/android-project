package com.example.nangshwekyi.gussinggameapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView statusText;
    private EditText guessField;
    private int secretNumber;
    private SharedPreferences preferences;
    private String minNumString, maxNumString;
    private TextView RangeText;
    private int minNum, maxNum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = getSharedPreferences("value", MODE_PRIVATE);
        minNum = preferences.getInt("min seek bar", 0);
        maxNum = preferences.getInt("max seek bar", 0);



        statusText = (TextView) findViewById(R.id.statusText);
        guessField = (EditText) findViewById(R.id.guessField);
        RangeText = (TextView) findViewById(R.id.rangeText);
        minNumString = Integer.toString(minNum);
        maxNumString = Integer.toString(maxNum);


        RangeText.setText("Range is : " + minNumString + "-" + maxNumString);
        Random random = new Random();

        if (minNum == 0 && maxNum == 0){
            minNum = 1;
            maxNum = 10;
            secretNumber = minNum+ random.nextInt(minNum);

        }
        else {
            int  n = maxNum - minNum + 1;
            int i = random.nextInt()%n;
            secretNumber = minNum + i;
            System.out.println("MainActivity" + secretNumber);
        }



        statusText.setText("Guess Number!");


        guessField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence string, int start, int before, int count) {

                try {
                    int value = Integer.parseInt(string.toString());

                    if (value == secretNumber) {
                        statusText.setText("You won!");
                    }
                } catch (Exception e){

                }



                statusText.setText(string);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }


        });

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    public void buttonHandler (View view) {
        Intent intent = new Intent (this, SecondaryActivity.class);
        startActivity(intent);

    }
}
