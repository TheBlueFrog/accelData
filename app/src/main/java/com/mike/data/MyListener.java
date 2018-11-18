package com.mike.data;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import static android.content.Context.SENSOR_SERVICE;

class MyListener  implements SensorEventListener {
    static private final String TAG = MyListener.class.getSimpleName();

    private Context context;
    private SensorManager mSensorManager;
    private Sensor mSensor;

    public MyListener(Context context) {
        this.context = context;
        mSensorManager = (SensorManager) context.getSystemService(SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    public PersistentOutput getPersistence() {
        return PersistentOutput.getInstance(context.getFileStreamPath("fred"));
    }

    public boolean isCollecting() {
        return getPersistence().isCollecting();
    }

    public void start() {
        Log.i(TAG + ".start", "");
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_GAME);
        getPersistence().setCollecting(true);
    }

    public void stop() {
        Log.i(TAG + ".stop", "");
        mSensorManager.unregisterListener(this);
        getPersistence().setCollecting(false);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        PersistentOutput.getInstance(context.getFileStreamPath("fred")).persist(event);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public int size() {
        return 0;
    }
}
