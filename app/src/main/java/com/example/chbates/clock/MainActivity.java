package com.example.chbates.clock;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {

    // to make out alarm manager
    AlarmManager alarm_manager;
    TimePicker alarm_timepicker;
    TextView update_text;
    Context context;
    PendingIntent pending_intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.context = this;

        //intialise alarm manager

        alarm_manager = (AlarmManager) getSystemService(ALARM_SERVICE);


        //intialise time picker
        alarm_timepicker = (TimePicker) findViewById(R.id.timePicker);


        //initialise our text update box
        update_text = (TextView) findViewById(R.id.update_text);

        //create a instance of a calandar

        final Calendar calendar = Calendar.getInstance();

        //create an intent to the Alarm Receiver
        final Intent my_intent = new Intent(this.context, Alarm_Receiver.class);

        //intialise start button
        final Button alarm_on = (Button) findViewById(R.id.alarm_on);
        //create an onClick Listener to start the alarm



        alarm_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // setting calendar withthe hour and minuet picked
                calendar.set(Calendar.HOUR_OF_DAY, alarm_timepicker.getHour());
                calendar.set(Calendar.MINUTE, alarm_timepicker.getMinute());

                // get the string values of the hour and minute
                int hour = alarm_timepicker.getHour();
                int minute = alarm_timepicker.getMinute();

                // convert the int values to string
                String hour_string = String.valueOf(hour);
                String minute_string = String.valueOf(minute);

                if (hour > 12) {
                    hour_string = String.valueOf(hour - 12);
                }

                if (minute < 10) {
                    //10:7 ---> 10:07
                    minute_string = "0" + String.valueOf(minute);
                }




                //method to change the update text
                set_alarm_text("Alarm Set to: " + hour_string + ":" + minute_string);

                // create a pending intent, that delays the intent till the specified calendar time
                pending_intent = PendingIntent.getBroadcast(MainActivity.this, 0,
                        my_intent, PendingIntent.FLAG_UPDATE_CURRENT);

                alarm_manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pending_intent);
            }
        });



        //initialise stop button
        Button alarm_off = (Button) findViewById(R.id.alarm_off);
        //create a onClick Listener to end the alarm

        alarm_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //method to turn alarm off
                set_alarm_text("Alarm Turned Off!");

                alarm_manager.cancel(pending_intent);
            }
        });



    }

    private void set_alarm_text(String output) {
        update_text.setText(output);

    }
}
