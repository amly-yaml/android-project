package com.example.nangshwekyi.welcomedietapp;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.app.Activity;
import android.content.Context;
import android.widget.TextView;


import java.util.Random;
public class MainActivity extends AppCompatActivity {
    private String[] theString;
    private static final  Random rand = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ImageView imageView=(ImageView) findViewById(R.id.imageRandom);
        int rndInt = rand.nextInt(2) + 1; // n = the number of images, that start at idx 1
        String imageName = "image" + rndInt; // random image display

        int imageID = getResources().getIdentifier(imageName, "drawable", getPackageName());
        imageView.setImageResource(imageID);

        TextView textView = (TextView) findViewById(R.id.text01);
        Resources res = getResources();
        theString = res.getStringArray(R.array.diet_array);
        String line = theString[rand.nextInt(theString.length)];//getting the random array words display
        textView.setText(line);


    }
}



