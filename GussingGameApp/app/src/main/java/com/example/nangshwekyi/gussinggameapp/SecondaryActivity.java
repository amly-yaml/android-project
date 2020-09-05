package com.example.nangshwekyi.gussinggameapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.nio.channels.SeekableByteChannel;

public class SecondaryActivity extends AppCompatActivity {

    private SeekBar minseekBar, maxseekBar;
    private SharedPreferences preferences;
    private Button goBack;
    private TextView textView1, textView2;
    private int min, max;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("Secondary", "on create called");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);

        textView1 = (TextView) findViewById(R.id.textView1);
        minseekBar = (SeekBar) findViewById(R.id.seekBar1);
        maxseekBar = (SeekBar) findViewById(R.id.seekBar2);
        goBack = (Button) findViewById(R.id.goBack);
        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);

        preferences = getSharedPreferences("value", MODE_PRIVATE);

        seekbar();

    }
    public void seekbar(){

        int progress = minseekBar.getProgress();
        textView1.setText(String.valueOf(progress));

        int progress2 = maxseekBar.getProgress();
        textView2.setText(String.valueOf(progress2));


        minseekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView1.setText(String.valueOf(progress));
                preferences.edit().putInt("min seek bar", progress).apply();

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        maxseekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView2.setText(String.valueOf(progress));
                preferences.edit().putInt("max seek bar", progress).apply();

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Secondary", "on create called");

        int progress = preferences.getInt("seek bar", 0);
        maxseekBar.setProgress(progress);
        minseekBar.setProgress(progress);

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Secondary", "on stop called");

        preferences.edit().putInt("min seek bar", minseekBar.getProgress()).apply();
        preferences.edit().putInt("max seek bar", maxseekBar.getProgress()).apply();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Secondary","on destory called");
    }

    public void buttonPress(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


}
