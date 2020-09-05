package com.example.nangshwekyi.sensorshakerapp;

import android.content.Context;
import android.content.res.Resources;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;


public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager senSensorManager;
    private Sensor senAccelerometer;
    private long lastUpdate = 0;
    private float last_x, last_y, last_z;
    private static final int SHAKE_THRESHOLD = 600;
    private RadioButton rda, rdb, rdc;
    private String[] theString;
    private static final  Random rand = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        senSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }


    private void getStringRandom(){

        Resources res = getResources();
        theString = res.getStringArray(R.array.chem_answer_array);

        String chem_answer1 = theString[rand.nextInt(theString.length)];

        String chem_answer2 = theString[rand.nextInt(theString.length)];

        String chem_answer3 = theString[rand.nextInt(theString.length)];

        rda = (RadioButton) findViewById(R.id.radio0);
        rda.setText(chem_answer1);

        rdb =(RadioButton)findViewById(R.id.radio1);
        rdb.setText(chem_answer2);

        rdc =(RadioButton)findViewById(R.id.radio2);
        rdc.setText(chem_answer3);

        Animation a = AnimationUtils.loadAnimation(this, R.anim.move_down_ball_first);
        rda.setVisibility(View.VISIBLE);
        rda.clearAnimation();
        rda.startAnimation(a);

        rdb.setVisibility(View.VISIBLE);
        rdb.clearAnimation();
        rdb.startAnimation(a);

        rdc.setVisibility(View.VISIBLE);
        rdc.clearAnimation();
        rdc.startAnimation(a);


    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor mySensor = sensorEvent.sensor;

        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            long curTime = System.currentTimeMillis();

            if ((curTime - lastUpdate) > 100) {
                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;

                float speed = Math.abs(x + y + z - last_x - last_y - last_z)/ diffTime * 10000;

                if (speed > SHAKE_THRESHOLD) {
                    getStringRandom();
                }

                last_x = x;
                last_y = y;
                last_z = z;
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    @Override
    protected void onPause() {
        super.onPause();
        senSensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }
}