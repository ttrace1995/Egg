package com.proj5.egg;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private final int CONSTANT_ONE_EGG = 1;
    private final int CONSTANT_TWO_EGGS = 2;
    private final int CONSTANT_MINUS_EGG = -1;
    private final int CONSTANT_MAKE_BREAKFAST = -6;

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
                //Do stuff here
                createNotification(v);
                Intent intent = new Intent(getApplicationContext(), BroadcastReciever.class);
                intent.putExtra("addOneEgg", CONSTANT_ONE_EGG);
            }
        });

        addTwo.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                //Do stuff here
                Intent intent = new Intent(getApplicationContext(), BroadcastReciever.class);
                intent.putExtra("addTwoEggs", CONSTANT_TWO_EGGS);
            }
        });

        subOne.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                //Do stuff here
                Intent intent = new Intent(getApplicationContext(), BroadcastReciever.class);
                intent.putExtra("subOneEgg", CONSTANT_MINUS_EGG);
            }
        });

        makeBreakfast.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                //Do stuff here
                Intent intent = new Intent(getApplicationContext(), BroadcastReciever.class);
                intent.putExtra("makeBreakfast", CONSTANT_MAKE_BREAKFAST);
            }
        });


    }

    public void createNotification(View view) {
        NotificationManager notificationManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);

        Intent intent = new Intent(this, BroadcastReciever.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);

        // build notification
        // the addAction re-use the same intent to keep the example short
        Notification n  = new Notification.Builder(this)
                .setContentTitle("My message")
                .setContentText("Subject")
                .setSmallIcon(R.drawable.egg)
                .setContentIntent(pIntent)
                .setAutoCancel(true).build();
        //  .addAction(R.drawable.line, "", pIntent).build();
        n.flags |= Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(0, n);
    }


}
