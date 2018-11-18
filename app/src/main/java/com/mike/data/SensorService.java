package com.mike.data;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by fabio on 30/01/2016.
 */
public class SensorService extends Service {
    public static final String TAG = SensorService.class.getSimpleName();

    private MyListener myListener = null;

    public int counter=0;
//    public SensorService(Context applicationContext) {
//        super();
//        Log.i(TAG + ".ctor", "service constructor");
//        this.context = applicationContext;
//        myListener = new MyListener(context);
//    }

    public SensorService() {
//        Context application = getApplicationContext();
//        myListener = new MyListener(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        Log.i(TAG + ".onStartCommand", "");
        myListener = new MyListener(this);
        myListener.start();
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
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}