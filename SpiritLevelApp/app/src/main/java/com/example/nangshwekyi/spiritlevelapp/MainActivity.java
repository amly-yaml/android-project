package com.example.nangshwekyi.spiritlevelapp;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements SensorEventListener{
    private SensorManager sensorManager;
    private Sensor sensor;
    private ImageView circleX, circleY;
    private Vibrator vibrating;
    private float deltaX = 0;
    private float deltaY = 0;
    private LinearLayout layout1;
    private LinearLayout layout2;
    private float x,y;
    private float marginX, marginY;
    private int height, width;
    private float vibrateThreshold = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        circleX = (ImageView) findViewById(R.id.circle);
        circleY = (ImageView) findViewById(R.id.circle1);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);


        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;

        marginX = width/2;
        marginY = height/2;

        x = circleX.getX();
        y = circleY.getY();

        //System.out.println("Display" + displayMetrics.widthPixels);


        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {

            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            sensorManager.registerListener( this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
            vibrateThreshold = sensor.getMaximumRange() /2;


        } else {
            vibrating = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        }
    }

    public void onSensorChanged (SensorEvent event){
        deltaX = (float) ((x + event.values[0]) * width/10);
        deltaY = (float) ((y + event.values[1]) * height/30);

        if (deltaX > marginX){
            deltaX = marginX;
        }
        else if (deltaX < -marginX){
            deltaX = -marginX;
        }
        else if (deltaY > marginY){
            deltaY = marginY;
        }
        else if (deltaY < -marginY){
            deltaY = marginY;
        }
        circleX.setX(deltaX);
        circleY.setY(deltaY);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}

