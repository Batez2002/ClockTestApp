package com.example.chbates.clock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by chbates on 09/10/2017.
 */

public class Alarm_Receiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Log.e("We are in the receiver." , "Yay");

        // create an intent to the ringtone service
        Intent service_intent = new Intent(context, RingtonePlayingService.class);


        //start the rington service
        context.startService(service_intent);

    }
}
