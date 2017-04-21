package com.proj5.egg;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by traceys5 on 4/18/17.
 */
public class EggBR extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        int eggAmount = intent.getIntExtra("eggCount", 0);
        if (eggAmount == 1) {
            Intent serve = new Intent(context, EggService.class);
            serve.putExtra("val", "1");
            context.startService(serve);
        } else if (eggAmount == 2) {
            Intent serve = new Intent(context, EggService.class);
            serve.putExtra("val", "2");
            context.startService(serve);
        } else if (eggAmount == -1) {
            Intent serve = new Intent(context, EggService.class);
            serve.putExtra("val", "-1");
            context.startService(serve);
            EggService.decrementEggCountOnce();
        } else if (eggAmount == -6) {
            Intent serve = new Intent(context, EggService.class);
            serve.putExtra("val", "-6");
            context.startService(serve);
        }
    }

}

