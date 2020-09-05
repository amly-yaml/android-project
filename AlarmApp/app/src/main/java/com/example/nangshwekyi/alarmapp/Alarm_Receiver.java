package com.example.nangshwekyi.alarmapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


public class Alarm_Receiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("We are in the receiver.", "Yay!");

        //fetch
        String get_your_string = intent.getExtras().getString("extra");

        Log.e("We is the key!", get_your_string);

        //this action tells which values is the user picked from the dop down menu
        Integer get_your_song_choice = intent.getExtras().getInt("song_choice");

        Log.e("Song Choice is", get_your_song_choice.toString());



        //creating the intnet to the ringtone service
        Intent service_intent = new Intent (context, RingtonePlayingService.class);

        //passing the extra string from main activity
        service_intent.putExtra("extra", get_your_string);

        context.startService(service_intent);

    }
}
