package com.proj5.egg;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by traceys5 on 4/18/17.
 */
public class Broadcast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        int eggModify = intent.getIntExtra("doEggThing", 0);
        Intent mIntent = new Intent(context.getApplicationContext(), EggService.class);
        Bundle extras = mIntent.getExtras();
        extras.putString("egg", String.valueOf(eggModify));
        Toast.makeText(context, "Egg", Toast.LENGTH_SHORT).show();
        //startService(mIntent);
    }
}

