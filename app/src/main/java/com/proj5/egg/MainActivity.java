package com.proj5.egg;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {


    ArrayList<Integer> ids = new ArrayList<>();

    private final String PROJECT_NAME = "Egg";
    private final String ONE_EGG_MESSAGE = "One Added!";
    private final String TWO_EGGS_MESSAGE = "Two Added!";

    private final int CONSTANT_ONE_EGG = 1;
    private final int CONSTANT_TWO_EGGS = 2;
    private final int CONSTANT_MINUS_EGG = -1;
    private final int CONSTANT_MAKE_BREAKFAST = -6;
    private final int CONSTANT_OMELET_AMOUNT = 6;

    Button addOne;
    Button addTwo;
    Button subOne;
    Button makeBreakfast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializes buttons
        addOne = (Button) findViewById(R.id.add_one);
        addTwo = (Button) findViewById(R.id.add_two);
        subOne = (Button) findViewById(R.id.subtract_one);
        makeBreakfast = (Button) findViewById(R.id.make_bfast);

        //Starts the service that keeps track of egg data
        startService(new Intent(getApplicationContext(), EggService.class));

        addOne.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                createNotification(CONSTANT_ONE_EGG);
                intentCaller(CONSTANT_ONE_EGG);
                Toast.makeText(MainActivity.this, "" + EggService.getCurrentEggCount(), Toast.LENGTH_SHORT).show();
            }
        });

        addTwo.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                createNotification(CONSTANT_TWO_EGGS);
                intentCaller(CONSTANT_TWO_EGGS);
                Toast.makeText(MainActivity.this, "" + EggService.getCurrentEggCount(), Toast.LENGTH_SHORT).show();
            }
        });

        subOne.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                intentCaller(CONSTANT_MINUS_EGG);
                Toast.makeText(MainActivity.this, "" + EggService.getCurrentEggCount(), Toast.LENGTH_SHORT).show();
            }
        });

        makeBreakfast.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                createNotification(CONSTANT_MAKE_BREAKFAST);
                intentCaller(CONSTANT_MAKE_BREAKFAST);
                Toast.makeText(MainActivity.this, "" + EggService.getCurrentEggCount(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void intentCaller(int num) {
        Intent intent = new Intent(getApplicationContext(), EggBR.class);
        intent.putExtra("eggCount", num);
        sendBroadcast(intent);
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
        }

        else if (message == CONSTANT_TWO_EGGS) {
            Notification n = new Notification.Builder(this)
                    .setContentTitle(PROJECT_NAME)
                    .setContentText(TWO_EGGS_MESSAGE)
                    .setSmallIcon(R.drawable.egg)
                    .setContentIntent(pIntent)
                    .setAutoCancel(true).build();
            notificationManager.notify(requestCode, n);
        }

        else if (message == CONSTANT_MAKE_BREAKFAST) {
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
