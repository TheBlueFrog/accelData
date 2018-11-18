package com.mike.data;

import android.hardware.SensorEvent;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PersistentOutput {
    static private final String TAG = PersistentOutput.class.getSimpleName();

    private static PersistentOutput singleton = new PersistentOutput( );

    private static List<Event> events = new ArrayList<>();
    private static File outputFile = null;

    private static boolean collecting = false;

    private PersistentOutput() {
    }

    public static PersistentOutput getInstance(File output) {
        outputFile = output;
        return singleton;
    }

    public void persist(SensorEvent event) {

        if (events.size() > 100) {
            persist(events);
            events = new ArrayList<>();
        }

        events.add(new Event(event));
    }

    private void persist(List<Event> events) {
        Log.i(TAG, "write to file");
        try {
//            File outFile = context.getFileStreamPath("fred");
            FileOutputStream fos = new FileOutputStream(outputFile, true);
            for (Event e : events) {
                String s = String.format("%d,%.4f,%.4f,%.4f\n", e.dtMs, e.x, e.y, e.z);
                fos.write(s.getBytes());
            }
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isCollecting() {
        return collecting;
    }

    public void setCollecting(boolean b) {
        collecting = b;
    }
}
