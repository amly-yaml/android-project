package com.example.nangshwekyi.cp3406assignment2app1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.nangshwekyi.cp3406assignment2app1.helper.Helper;

public class InstructionActivity extends AppCompatActivity {
    private TextView instructionText;
    private Button buttonBack, buttonPlayGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction);

        setTitle(getString(R.string.quiz_information_inst));

        TextView instructionText = (TextView)findViewById(R.id.quiz_instruction);
        buttonBack = (Button) findViewById(R.id.buttonBack);
        buttonPlayGame = (Button) findViewById(R.id.buttonPlayGame);
        instructionText.setText(Helper.instruction);
    }
    public void buttonBack(View view){
        Intent intent = new Intent (this, MainActivity.class);
        startActivity(intent);

    }
    public void buttonGame (View view){
        Intent intent = new Intent (this, QuizzActivity.class);
        startActivity(intent);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_settings) {
            // launch settings activity
            Intent settingIntent = new Intent(InstructionActivity.this, SettingActivity.class);
            startActivity(settingIntent);
            return true;
        }else if (id==R.id.another_settings){
            // launch settings activity
            Intent settingIntent = new Intent(InstructionActivity.this, ResultActivity.class);
            startActivity(settingIntent);
        }

        return super.onOptionsItemSelected(item);
    }




}

