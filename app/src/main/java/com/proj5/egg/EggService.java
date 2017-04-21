package com.proj5.egg;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by traceys5 on 4/18/17.
 */
public class EggService extends Service {

    private static int currentEggCount = 0;
    ArrayList<Integer> ids = new ArrayList<>();
    private final String PROJECT_NAME = "Egg";
    private final String ONE_EGG_MESSAGE = "One Added!";
    private final String TWO_EGGS_MESSAGE = "Two Added!";
    private final int CONSTANT_ONE_EGG = 1;
    private final int CONSTANT_TWO_EGGS = 2;
    private final int CONSTANT_MINUS_EGG = -1;
    private final int CONSTANT_MAKE_BREAKFAST = -6;
    private final int CONSTANT_OMELET_AMOUNT = 6;


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "In Service", Toast.LENGTH_SHORT).show();

        if (fileExists()) {
            String hold = readFromInternalStorage();
            if (hold == "") {
                Toast.makeText(EggService.this, "File found empty", Toast.LENGTH_SHORT).show();
                currentEggCount = 0;
            } else {
                Toast.makeText(EggService.this, "File found with number", Toast.LENGTH_SHORT).show();
                currentEggCount = Integer.valueOf(hold);
            }
        }

        int extra = Integer.valueOf(intent.getStringExtra("val"));
        createNotification(extra);
        //this was zero in example not sure what ive changed it to but it was yelling at me
        return Service.START_NOT_STICKY;
    }

    public static void incrementEggCountOnce() {
        currentEggCount++;
    }

    public static void incrementEggCountTwice() {
        currentEggCount += 2;
    }

    public static void decrementEggCountOnce() {
        if (currentEggCount > 0) {
            currentEggCount--;
        }
    }

    public static void makeBreakfast() {
        if (currentEggCount >= 6) {
            currentEggCount -= 6;
        } else {
//            im not really sure
        }
    }

    public static int getCurrentEggCount() {
        return currentEggCount;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        String filename = "eggcount_android.txt";
        String string = String.valueOf(currentEggCount);
        FileOutputStream outputStream;
        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(string.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String readFromInternalStorage() {
        FileInputStream fis = null;
        try {
            fis = this.openFileInput("eggcount_android.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader bufferedReader = new BufferedReader(isr);
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString().trim();
    }

    public boolean fileExists() {
        File file = getBaseContext().getFileStreamPath("eggcount_android.txt");
        return file.exists();
    }

    public void createNotification(int message) {

        NotificationManager notificationManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);
        int requestCode = ("" + System.currentTimeMillis()).hashCode();
        ids.add(requestCode);

        Intent intent = new Intent(getApplicationContext(), EggBR.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, requestCode, intent, 0);

        if (message == CONSTANT_ONE_EGG) {
            Notification n = new Notification.Builder(this)
                    .setContentTitle(PROJECT_NAME)
                    .setContentText(ONE_EGG_MESSAGE)
                    .setSmallIcon(R.drawable.egg)
                    .setContentIntent(pIntent)
                    .setAutoCancel(true).build();
            notificationManager.notify(requestCode, n);
        } else if (message == CONSTANT_TWO_EGGS) {
            Notification n = new Notification.Builder(this)
                    .setContentTitle(PROJECT_NAME)
                    .setContentText(TWO_EGGS_MESSAGE)
                    .setSmallIcon(R.drawable.egg)
                    .setContentIntent(pIntent)
                    .setAutoCancel(true).build();
            notificationManager.notify(requestCode, n);
        } else if (message == CONSTANT_MAKE_BREAKFAST) {
            int eggs = EggService.getCurrentEggCount();
            if (eggs >= CONSTANT_OMELET_AMOUNT) {
                String omeletMessage = "We are having omelets, we have " + (eggs - CONSTANT_OMELET_AMOUNT) + " eggs available";
                Notification n = new Notification.Builder(this)
                        .setContentTitle(PROJECT_NAME)
                        .setContentText(omeletMessage)
                        .setSmallIcon(R.drawable.egg)
                        .setContentIntent(pIntent)
                        .setAutoCancel(true).build();
                notificationManager.notify(requestCode, n);
            } else {
                String otherMessage = "We are having gruel, we have " + eggs + " eggs available";
                Notification n = new Notification.Builder(this)
                        .setContentTitle(PROJECT_NAME)
                        .setContentText(otherMessage)
                        .setSmallIcon(R.drawable.egg)
                        .setContentIntent(pIntent)
                        .setAutoCancel(true).build();
                notificationManager.notify(requestCode, n);
            }
        }
    }
}
