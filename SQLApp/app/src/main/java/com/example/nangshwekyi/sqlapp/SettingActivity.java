package com.example.nangshwekyi.sqlapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class SettingActivity extends AppCompatActivity {
    Switch simpleSwitch1;
    Button submitButton;
    protected SoundBackground app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        // initiate view's
        simpleSwitch1 = (Switch) findViewById(R.id.simpleSwitch1);
        submitButton = (Button) findViewById(R.id.submitButton);

        app = (SoundBackground)getApplication();

        if(app.getMedia_electronicloop_state()){
            simpleSwitch1.setChecked(true);
        }else{
            simpleSwitch1.setChecked(false);
        }

        simpleSwitch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    Toast.makeText(getApplication(), "Music On", Toast.LENGTH_SHORT).show();
                    app.setMedia_electronicloop_state(true);
                    app.musicPlay();
                }else{
                    Toast.makeText(getApplication(), "Music Off", Toast.LENGTH_SHORT).show();
                    app.setMedia_electronicloop_state(false);
                    app.musicStop();
                }
            }
        });
    }

    public void buttonSetting(View view){
        Intent intent = new Intent (this, MainActivity.class);
        startActivity(intent);

    }




}

        /*simpleSwitch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Sound  is disabled
                    simpleSwitch1.setSoundEffectsEnabled(true);
                } else {
                    // Sound is enabled
                    simpleSwitch1.setSoundEffectsEnabled(false);
                }
            }

        });

        simpleSwitch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Sound  is disabled
                    simpleSwitch2.setSoundEffectsEnabled(false);
                } else {
                    // Sound is enabled
                    simpleSwitch2.setSoundEffectsEnabled(true);
                }

            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String statusSwitch1, statusSwitch2;
                if (simpleSwitch1.isChecked()) {
                    statusSwitch1 = simpleSwitch1.getTextOn().toString();
                    //simpleSwitch1.setSoundEffectsEnabled(true);
                }
                else
                    statusSwitch1 = simpleSwitch1.getTextOff().toString();
                if (simpleSwitch2.isChecked()) {
                    statusSwitch2 = simpleSwitch2.getTextOn().toString();
                    //simpleSwitch2.setSoundEffectsEnabled(false);
                }
                else
                    statusSwitch2 = simpleSwitch2.getTextOff().toString();
                Intent intent = new Intent(SettingActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

                Toast.makeText(getApplicationContext(), "Switch1 :" + statusSwitch1 + "\n" + "Switch2 :" + statusSwitch2, Toast.LENGTH_LONG).show(); // display the current state for switch's
            }
        });

    }
}*/

