package com.proj5.egg;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by traceys5 on 4/18/17.
 */
public class BroadcastReciever extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Egg", Toast.LENGTH_SHORT).show();
        }
    }

