package com.example.nangshwekyi.drummachineapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private  SoundManager soundManager;
    private Display display;
    private int drum_roll, metalclack_potspans;
    private ImageView imageView;
    private LinearLayout screenWidth, screenHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        soundManager = new SoundManager(this);
        drum_roll = soundManager.addSound(R.raw.drum_roll);
        metalclack_potspans = soundManager.addSound(R.raw.metalclack_potspans);
        imageView = (ImageView) findViewById(R.id.imageView);



        imageView.setImageResource(R.drawable.drum_photo);


        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int centerX = imageView.getWidth() / 2;
                int centerY = imageView.getHeight() / 2;

                String X = Integer.toString(centerX);
                String Y = Integer.toString(centerY);

                //Log.i("My Activity", "center X:" + X + "centerY:" + Y);

                float X1 = event.getX();
                float Y1 = event.getY();

                Log.i("My Activity222", "EventX:" + X1 + "EventY:" + Y1);

                if (X1 < 300.33 && Y1 < 430.41) {
                    soundManager.play(metalclack_potspans);
                } else if (X1 > 450.36 && Y1 < 670.47) {
                    soundManager.play(drum_roll);
                } else if (X1 < 863.85 && Y1 < 590.66) {
                    soundManager.play(metalclack_potspans);
                } else if (X1 < 500.68 && Y1 < 821.48) {
                    soundManager.play(drum_roll);
                } else if (X1 < 244.19 && Y1 < 398.26) {
                    soundManager.play(metalclack_potspans);
                } else if (X1 < 572.49 && Y1 < 675.41) {
                    soundManager.play(drum_roll);
                } else if (X1 == 572.49 && Y1 == 675.41) {
                    soundManager.play(drum_roll);
                } else if (X1 <= 712.60 && Y1 <= 785.45) {
                    soundManager.play(drum_roll);
                } else if (X1 <= 285.22 && Y1 <= 791.48) {
                    soundManager.play(metalclack_potspans);
                } else if (X1 > 620.54 && Y1 > 780.48){
                    soundManager.play(drum_roll);
                }else if (X1 <= 230.23 && Y1 <= 395.27){
                    soundManager.play(metalclack_potspans);
                }else if (X1 < 540.44 && Y1 < 639.37){
                    soundManager.play(drum_roll);
                }else if ( X1 < 150.09 && Y1 < 640.38){
                    soundManager.play(metalclack_potspans);
                }else if ( X1 < 840.71 && Y1 < 579.35){
                    soundManager.play(metalclack_potspans);
                }
                else{
                    soundManager.play(metalclack_potspans);
                }


                return true;
            }
        });

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Position position = display.getPosition(event.getX(), event.getY());
        switch (position){
            case BOTTOM_LEFT:
                soundManager.play(drum_roll);
                break;
            case BOTTOM_RIGHT:
                soundManager.play(drum_roll);
                break;
            case TOP_LEFT:
                soundManager.play(metalclack_potspans);
                break;
            case TOP_RIGHT:
                soundManager.play(metalclack_potspans);
                break;
            case MIDDLE:
                soundManager.play(drum_roll);
                break;
        }
        return super.onTouchEvent(event);
    }

}
