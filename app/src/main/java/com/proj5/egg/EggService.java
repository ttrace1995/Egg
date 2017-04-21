package com.proj5.egg;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

/**
 * Created by traceys5 on 4/18/17.
 */
public class EggService extends Service {

    private static int currentEggCount;


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "In Service", Toast.LENGTH_SHORT).show();
        currentEggCount = 0;
        //this was zero in example not sure what ive changed it to but it was yelling at me
        return Service.START_NOT_STICKY;
    }

    public static void incrementEggCountOnce(){
        currentEggCount++;
    }

    public static void incrementEggCountTwice(){
        currentEggCount += 2;
    }

    public static void decrementEggCountOnce() {
        currentEggCount--;
    }

    public static void makeBreakfast() {
        if(currentEggCount >= 6) {
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

}
