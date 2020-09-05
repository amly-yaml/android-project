package com.example.nangshwekyi.cp3406assignment2app;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;



public class SoundBackground extends Service implements MediaPlayer.OnCompletionListener{
    MediaPlayer mediaPlayer;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        super.onCreate();
        mediaPlayer = MediaPlayer.create(this, R.raw.melody);// raw/melody.mp3
        mediaPlayer.setOnCompletionListener(this);
        mediaPlayer.setLooping(true); // Set looping
        mediaPlayer.setVolume(100,100);

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

    @Override
    public void onCompletion(MediaPlayer _mediaPlayer) {
        stopSelf();

    }
}
