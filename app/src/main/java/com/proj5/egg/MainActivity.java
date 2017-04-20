package com.proj5.egg;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final int CONSTANT_ONE_EGG = 1;
    private final int CONSTANT_TWO_EGGS = 2;
    private final int CONSTANT_MINUS_EGG = -1;
    private final int CONSTANT_MAKE_BREAKFAST = -6;

    ArrayList<Integer> ids = new ArrayList<>();

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
                createNotification(v);
                intentCaller(CONSTANT_ONE_EGG);
            }
        });

        addTwo.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                createNotification(v);
                createNotification(v);
                intentCaller(CONSTANT_TWO_EGGS);
            }
        });

        subOne.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                removeNotification(CONSTANT_MINUS_EGG);
                intentCaller(CONSTANT_MINUS_EGG);
            }
        });

        makeBreakfast.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                removeNotification(CONSTANT_MAKE_BREAKFAST);
                intentCaller(CONSTANT_MAKE_BREAKFAST);
            }
        });
    }

    private void intentCaller(int num) {
        Intent intent = new Intent(getApplicationContext(), EggBR.class);
        intent.putExtra("eggCount", num);
        sendBroadcast(intent);
    }

    public void removeNotification(int num) {
        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        if(ids.size() != 0) {
            if(num == CONSTANT_MINUS_EGG) {
                notificationManager.cancel(ids.get(0));
                ids.remove(0);
            } else if (num == CONSTANT_MAKE_BREAKFAST) {
                if(ids.size() >= 6) {
                    for(int i = 0; i < 6; i++) {
                        notificationManager.cancel(ids.get(0));
                        ids.remove(0);
                    }
                }
            }
        }
    }

    public void createNotification(View view) {
        NotificationManager notificationManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);

        int requestCode = ("" + System.currentTimeMillis()).hashCode();
        ids.add(requestCode);

        Intent intent = new Intent(this, EggBR.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, requestCode, intent, 0);

//        PendingIntent pIntent = PendingIntent.getActivity(this, requestCode , intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // build notification
        // the addAction re-use the same intent to keep the example short
        Notification n  = new Notification.Builder(this)
                .setContentTitle("My message")
                .setContentText("Subject: " + requestCode)
                .setSmallIcon(R.drawable.egg)
                .setContentIntent(pIntent)
                .setAutoCancel(true).build();
        //  .addAction(R.drawable.line, "", pIntent).build();
//        n.flags |= Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(requestCode, n);
    }


}
