package com.mike.data;

import android.hardware.SensorEvent;

public class Event {
    static private long lastEventTimeMs = 0;

    long dtMs;
    float x;
    float y;
    float z;

    public Event(SensorEvent event) {
        this.dtMs = event.timestamp - lastEventTimeMs;
        lastEventTimeMs = event.timestamp;

        this.x = event.values[0];
        this.y = event.values[1];
        this.z = event.values[2];
    }
}
