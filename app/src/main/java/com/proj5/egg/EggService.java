package com.proj5.egg;

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

/**
 * Created by traceys5 on 4/18/17.
 */
public class EggService extends Service {

    private static int currentEggCount = 0;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "In Service", Toast.LENGTH_SHORT).show();
        if (fileExists()) {
            String hold = readFromInternalStorage();
            if (hold == "") {
                Toast.makeText(EggService.this, "File found empty", Toast.LENGTH_SHORT).show();
                currentEggCount = 0;
            }
            else {
                Toast.makeText(EggService.this, "File found with number", Toast.LENGTH_SHORT).show();
                currentEggCount = Integer.valueOf(hold);
            }
        }
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
        currentEggCount--;
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
        Log.d("xxxxxxxxxx",string);
        FileOutputStream outputStream;
        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(string.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void writeToInternalStorage(String eggCount) {

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

}
