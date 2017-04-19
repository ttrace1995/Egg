package com.proj5.egg;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

/**
 * Created by traceys5 on 4/18/17.
 */
public class EggService extends Service {

    private int currentEggCount;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "In Service", Toast.LENGTH_SHORT).show();
        doAgain();

        stopSelf();
        //this was zero in example not sure what ive changed it to but it was yelling at me
        return Service.START_NOT_STICKY;
    }

    void doAgain(){
//        Intent startIntent = new Intent("com.proj5.egg.MYACTION");
//        PendingIntent startPIntent = PendingIntent.getBroadcast(this, 0, startIntent, 0);
//        AlarmManager alarm = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
//        alarm.set(AlarmManager.RTC_WAKEUP, SystemClock.elapsedRealtime() + + TWO_SECONDS, startPIntent);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
