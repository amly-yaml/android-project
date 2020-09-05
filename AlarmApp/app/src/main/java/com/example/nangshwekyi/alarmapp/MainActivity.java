package com.example.nangshwekyi.alarmapp;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    AlarmManager alarmManager;


    private TimePicker timePicker;
    private TextView textView;
    private Button alarm_on;
    private Button alarm_off;
    int choose_song;

    Context context;
    PendingIntent pending_intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.context = this;

        //alarm = new AlarmReceiver();
        textView = (TextView) findViewById(R.id.textView);


        // Get the alarm manager service
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        // set the alarm to the time that you picked

        timePicker = (TimePicker) findViewById(R.id.timePicker);

        final Calendar calendar = Calendar.getInstance();

        //creating the intent
        final Intent my_intent = new Intent(this.context, Alarm_Receiver.class);


        //creating the spinner in the main
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.cp3406_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        //set the on song click item
        spinner.setOnItemSelectedListener(this);


        Button alarm_on = (Button) findViewById(R.id.alarm_on);
        //create the alarm on
        alarm_on.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {


                calendar.set(Calendar.HOUR_OF_DAY, timePicker.getHour());
                calendar.set(Calendar.MINUTE, timePicker.getMinute());

                int hour = timePicker.getHour();
                int minute = timePicker.getMinute();

                String hour_string = String.valueOf(hour);
                String minute_string = String.valueOf(minute);

                //converting 24 hour time to 12 hour time
                if (hour > 12) {
                    hour_string = String.valueOf(hour - 12);
                }
                if (minute < 10) {
                    //10:7 --> 10:07
                    minute_string = "0" + String.valueOf(minute);
                }

                set_alarm_text("Alarm on!" + hour_string + ":" + minute_string);

                //putting the extra string into the intent
                my_intent.putExtra("extra", "on");
                //drop down menu
                my_intent.putExtra("song_choice", choose_song);
                Log.e("Song id is", String.valueOf(choose_song));

                //Creating the pending intent
                //until calender time
                pending_intent = PendingIntent.getBroadcast(MainActivity.this, 0,
                        my_intent, PendingIntent.FLAG_UPDATE_CURRENT);

                //set the alarm manager
                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pending_intent);

            }

        });


        Button alarm_off = (Button) findViewById(R.id.alarm_off);
        //create the alarm off

        alarm_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //set the update

                set_alarm_text("Alarm Off!");

                //canceling the alarm
                alarmManager.cancel(pending_intent);

                //putting the extra to tell the clock the pressed the off button
                my_intent.putExtra("extra", "off");
                //prevent craches from the Null
                my_intent.putExtra("song_choice", choose_song);

                //stopping ringtone
                sendBroadcast(my_intent);
            }
        });
    }

    private void set_alarm_text(String output) {
        textView.setText(output);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        //the item selected
        Toast.makeText(adapterView.getContext(), "the spinner item is" + id, Toast.LENGTH_SHORT).show();
        choose_song = (int) id;


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    @Override
    public void onStart() {
        super.onStart();
        Log.e ("On Start", "The Demo App");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("on stop called");
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.e("MyActivity", "on Destroy");
    }
}
