package com.mike.data;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.mike.data.R;

public class MainActivity extends AppCompatActivity {
    static final String TAG = MainActivity.class.getSimpleName();
    Intent mServiceIntent;
//    private SensorService mSensorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ctx = this;
        setContentView(R.layout.activity_main);
//        mSensorService = new SensorService(this);
        mServiceIntent = new Intent(this, SensorService.class);

//        if (!isMyServiceRunning(SensorService.class)) {
//            Log.i (TAG + ".onCreate","Service not running, start it");
            startService(mServiceIntent);
//        }
//        else {
//            Log.i (TAG + ".onCreate","Service is already running");
//        }
    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }


    @Override
    protected void onDestroy() {
        stopService(mServiceIntent);
        Log.i(TAG + ".onDestroy", "stopped service");
        super.onDestroy();

    }
}


