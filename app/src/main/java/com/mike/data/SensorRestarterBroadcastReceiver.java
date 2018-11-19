package com.mike.data;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class SensorRestarterBroadcastReceiver extends BroadcastReceiver {
    static final String TAG = SensorRestarterBroadcastReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG + ".onReceive", "Service no running, start it");
        context.startService(new Intent(context, SensorService.class));;
    }

}
