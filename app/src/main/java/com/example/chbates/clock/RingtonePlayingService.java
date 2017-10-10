package com.example.chbates.clock;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.security.Provider;

/**
 * Created by chbates on 09/10/2017.
 */

public class RingtonePlayingService extends Service {

    MediaPlayer media_song;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    public int onStartCommand(Intent intent, int flags, int startID) {

        Log.i("LocalService", "Received start id " + startID + ": " + intent);

        media_song = MediaPlayer.create(this, R.raw.sampleaudio);
        media_song.start();

        return START_NOT_STICKY;
    }

    public void onDestroy() {
        //Tell the user we stopped
        Toast.makeText(this, "on Destroy called", Toast.LENGTH_SHORT).show();
    }
}
