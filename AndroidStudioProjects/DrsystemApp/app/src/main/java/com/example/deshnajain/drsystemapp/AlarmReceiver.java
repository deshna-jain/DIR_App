package com.example.deshnajain.drsystemapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context k1, Intent k2) {
        Toast.makeText(k1,"Alarm Received!",Toast.LENGTH_LONG).show();
    }
}
