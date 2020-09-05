package com.example.nangshwekyi.sqlapp;

import android.app.Activity;
import android.app.Application;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Window;

import java.io.IOException;


/*public class SoundBackground extends Service implements MediaPlayer.OnCompletionListener{
    MediaPlayer mediaPlayer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(this, R.raw.melody);// raw/s.mp3
        mediaPlayer.setOnCompletionListener(this);
        mediaPlayer.setLooping(true); // Set looping
        mediaPlayer.setVolume(100, 100);

    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
        return START_STICKY;

    }

    public void onDestroy() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        mediaPlayer.release();
    }

    //public void onPause(){
        //super.onPause();
        //mediaPlayer.stop();
    //}




    public void onCompletion(MediaPlayer _mediaPlayer) {
        mediaPlayer.stop();
        stopSelf();
    }

}*/


public class SoundBackground extends Application{
    MediaPlayer mediaPlayer;
    @Override
    public void onCreate() {
        super.onCreate();
        setMedia_electronicloop_state(false);
        mediaPlayer = new MediaPlayer();
    }
    protected void musicPlay() {
        mediaPlayer.reset();
        mediaPlayer.setLooping(true);

        try{
            mediaPlayer.setDataSource(getApplicationContext(), Uri.parse("android.resource://net.clientuser.droid101/" + R.raw.melody));
            mediaPlayer.prepare();
            mediaPlayer.start();


        }catch(IOException e) {
            e.printStackTrace();
        }

    }
    protected void musicStop() {
        mediaPlayer.stop();

    }

    public Boolean getMedia_electronicloop_state(){
        return media_electronicloop_state;
    }

    public void setMedia_electronicloop_state(Boolean media_electronicloop_state) {
        this.media_electronicloop_state = media_electronicloop_state;
    }

    Boolean media_electronicloop_state;

}
