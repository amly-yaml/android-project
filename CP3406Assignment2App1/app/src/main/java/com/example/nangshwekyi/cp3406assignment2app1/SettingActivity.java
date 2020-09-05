package com.example.nangshwekyi.cp3406assignment2app1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class SettingActivity extends AppCompatActivity {
    private Switch simpleSwitch1;
    private Button submitButton;

    //protected SoundBackground service;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        // initiate view's
        simpleSwitch1 = (Switch) findViewById(R.id.simpleSwitch1);
        submitButton = (Button) findViewById(R.id.submitButton);


        simpleSwitch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    Toast.makeText(getApplication(), "Music Off", Toast.LENGTH_SHORT).show();
                    //service.musicPlay();

                }else{
                    Toast.makeText(getApplication(), "Music On", Toast.LENGTH_SHORT).show();
                    //service.musicStop();
                }
            }
        });



    }
    public void buttonSetting(View view){
        Intent intent = new Intent (this, MainActivity.class);
        startActivity(intent);

    }


}

