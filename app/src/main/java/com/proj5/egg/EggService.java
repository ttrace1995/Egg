package com.proj5.egg;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.util.ArrayList;

/**
 * Created by Tyler Tracey and Patrick Hennis
 */
public class EggService extends Service {

    private static int currentEggCount = 0;
    ArrayList<Integer> ids = new ArrayList<>();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(getCurrentEggCount() != 0) {
            currentEggCount = getCurrentEggCount();
        }

        int extra = Integer.valueOf(intent.getStringExtra("val"));
        createNotification(extra);
        //this was zero in example not sure what ive changed it to but it was yelling at me
        stopSelf();
        return Service.START_NOT_STICKY;
    }

    public static void incrementEggCountOnce() {
        currentEggCount++;
        MainActivity.preferences.edit().putInt( "egg_count", currentEggCount ).commit();
    }

    public static void incrementEggCountTwice() {
        currentEggCount++;
        currentEggCount++;
        MainActivity.preferences.edit().putInt( "egg_count", currentEggCount ).commit();
    }

    public static void decrementEggCountOnce() {
        if (currentEggCount > 0) {
            currentEggCount--;
            MainActivity.preferences.edit().putInt( "egg_count", currentEggCount ).commit();
        }
    }

    public static void makeBreakfast() {
        if (getCurrentEggCount() >= 6) {
            currentEggCount -= 6;
            MainActivity.preferences.edit().putInt( "egg_count", currentEggCount ).commit();
        }
    }

    public static int getCurrentEggCount() {
        return MainActivity.preferences.getInt( "egg_count", currentEggCount );
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void createNotification(int message) {

        NotificationManager notificationManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);
        int requestCode = ("" + System.currentTimeMillis()).hashCode();
        ids.add(requestCode);

        Intent intent = new Intent(getApplicationContext(), EggBR.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, requestCode, intent, 0);

        if (message == CONSTANTS.CONSTANT_ONE_EGG) {
            incrementEggCountOnce();
            Notification n = new Notification.Builder(this)
                    .setContentTitle(CONSTANTS.PROJECT_NAME)
                    .setContentText(CONSTANTS.ONE_EGG_MESSAGE)
                    .setSmallIcon(R.drawable.egg)
                    .setContentIntent(pIntent)
                    .setAutoCancel(true).build();
            notificationManager.notify(requestCode, n);
        } else if (message == CONSTANTS.CONSTANT_TWO_EGGS) {
            incrementEggCountTwice();
            Notification n = new Notification.Builder(this)
                    .setContentTitle(CONSTANTS.PROJECT_NAME)
                    .setContentText(CONSTANTS.TWO_EGGS_MESSAGE)
                    .setSmallIcon(R.drawable.egg)
                    .setContentIntent(pIntent)
                    .setAutoCancel(true).build();
            notificationManager.notify(requestCode, n);
        } else if (message == CONSTANTS.CONSTANT_MINUS_EGG) {
            decrementEggCountOnce();
            Notification n = new Notification.Builder(this)
                    .setContentTitle(CONSTANTS.PROJECT_NAME)
                    .setContentText(CONSTANTS.ONE_EGG_REMOVED)
                    .setSmallIcon(R.drawable.egg)
                    .setContentIntent(pIntent)
                    .setAutoCancel(true).build();
            notificationManager.notify(requestCode, n);
        } else if (message == CONSTANTS.CONSTANT_MAKE_BREAKFAST) {
            int eggs = getCurrentEggCount();
            if (eggs >= CONSTANTS.CONSTANT_OMELET_AMOUNT) {
                makeBreakfast();
                String omeletMessage = "We are having omelets, we have " + (eggs - CONSTANTS.CONSTANT_OMELET_AMOUNT) + " eggs available";
                Notification n = new Notification.Builder(this)
                        .setContentTitle(CONSTANTS.PROJECT_NAME)
                        .setContentText(omeletMessage)
                        .setSmallIcon(R.drawable.egg)
                        .setContentIntent(pIntent)
                        .setAutoCancel(true).build();
                notificationManager.notify(requestCode, n);
            } else {
                String otherMessage = "We are having gruel, we have " + eggs + " eggs available";
                Notification n = new Notification.Builder(this)
                        .setContentTitle(CONSTANTS.PROJECT_NAME)
                        .setContentText(otherMessage)
                        .setSmallIcon(R.drawable.egg)
                        .setContentIntent(pIntent)
                        .setAutoCancel(true).build();
                notificationManager.notify(requestCode, n);
            }
        }
    }
}
