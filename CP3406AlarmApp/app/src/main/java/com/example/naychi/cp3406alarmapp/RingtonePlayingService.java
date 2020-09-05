package com.example.naychi.cp3406alarmapp;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;



public class RingtonePlayingService extends Service {

    MediaPlayer mediaPlayer;
    int startId;
    boolean isRunning;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public int onStartCommand (Intent intent, int flags, int startId){
        Log.i ("LocalService", "Received start id" + startId + ":" + intent);

        //Fetch the extra string values
        String state = intent.getExtras().getString("extra");

        Log.e("Ringtone state:extra is", state);

        //put the notification here//
        //notification for my app
        //setting up the service
        NotificationManager notificationManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);

        //setting the intent goes to the main activity
        Intent intent_main_activity = new Intent(this.getApplicationContext(), MainActivity.class);
        //setting up the padding intent
        PendingIntent pending_intent_main_activity = PendingIntent.getActivity(this, 0, intent_main_activity, 0);

        //notification making
        Notification notification_popup = new Notification.Builder(this)
                .setContentTitle("Alarm is going off")
                .setContentText("Click!")
                .setContentIntent(pending_intent_main_activity)
                .setAutoCancel(true)
                .build();


        //switch and case statement
        assert state != null;
        switch (state) {
            case "on":
                startId = 1;

                break;
            case "off":
                startId = 0;
                Log.e("State ID is", state);


                break;
            default:
                startId = 0;
                break;
        }


        //if and else statement'

        //if there is no music playing, the user pressed "alarm on"
        //music start playing
        if (!this.isRunning && startId == 1) {

            Log.e("there is no music", "and you want start");
            //create the instance of media player
            mediaPlayer = MediaPlayer.create(this, R.raw.song);
            //start the song
            mediaPlayer.start();

            this.isRunning = true;
            this.startId = 0;


            //set up the notification command call
            assert notificationManager != null;
            notificationManager.notify(0, notification_popup);

        }

        //if there is music playing and press "alarm off"
        //music stop playing
        else if (this.isRunning && startId == 0){
            Log.e("there is music", "and you want end");

            //stopping the song
            mediaPlayer.stop();
            mediaPlayer.reset();

            this.isRunning = false;
            this.startId = 0;



        }

        //if there is no music, pressed the alarm off
        //do nothing
        else if(!this.isRunning && startId == 0) {
            Log.e("there is no music", "and you want end");

            this.isRunning = false;
            this.startId =0;


        }

        //if there is music playing and the user preseed "alarm on"
        //do nothing
        else if (this.isRunning && startId == 1){
            Log.e("there is music", "and you want start");

            this.isRunning = true;
            this.startId = 1;

        }

        //catching teh odd event
        else {
            Log.e("else", "you reach here somehow");

        }


        return START_NOT_STICKY;

    }


    public void onDestroy(){
        Log.e ("on Destory called", "la la");
        super.onDestroy();
        this.isRunning = false;
    }


}
