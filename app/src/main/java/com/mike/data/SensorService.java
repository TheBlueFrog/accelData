package com.mike.data;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class SensorService extends Service {
    public static final String TAG = SensorService.class.getSimpleName();

    static private AccelerometerSensorListener myListener = null;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        Log.i(TAG + ".onStartCommand", "");
        if (myListener == null) {
            myListener = new AccelerometerSensorListener(this);
            myListener.start();
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG + ".onDestroy", "");

        Intent broadcastIntent = new Intent(this, SensorRestarterBroadcastReceiver.class);
        sendBroadcast(broadcastIntent);

        if (myListener != null) {
            myListener.stop();
            myListener = null;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}