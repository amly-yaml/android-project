package com.example.nangshwekyi.cp3406assignment2app1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private Button buttonPlay, button_instruction, buttonScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Chemistry Quiz");

        imageView = (ImageView) findViewById(R.id.image_chemistry);
        buttonPlay = (Button) findViewById(R.id.buttonPlay);
        button_instruction = (Button) findViewById(R.id.button_instruction);
        buttonScore = (Button) findViewById(R.id.buttonResult);

        Intent svc=new Intent(this, SoundBackground.class);
        startService(svc);
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
            Intent settingIntent = new Intent(MainActivity.this, SettingActivity.class);
            startActivity(settingIntent);
            return true;
        }else if (id==R.id.another_settings){
            // launch settings activity
            Intent settingIntent = new Intent(MainActivity.this, ResultActivity.class);
            startActivity(settingIntent);
        }

        return super.onOptionsItemSelected(item);
    }


    public void buttonPress(View view){
        Intent intent = new Intent (this, QuizzActivity.class);
        startActivity(intent);

    }

    public void buttonGo(View View){
        Intent intent = new Intent (this, InstructionActivity.class);
        startActivity(intent);

    }
    public void buttonHandler(View view){
        Intent intent = new Intent (this, ResultActivity.class);
        startActivity(intent);

    }
}
