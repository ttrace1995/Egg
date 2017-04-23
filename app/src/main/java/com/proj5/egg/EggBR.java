package com.proj5.egg;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Tyler Tracey and Patrick Hennis
 */
public class EggBR extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        int eggAmount = intent.getIntExtra("eggCount", 0);
        if (eggAmount == CONSTANTS.CONSTANT_ONE_EGG) {
            Intent serve = new Intent(context, EggService.class);
            serve.putExtra("val", "1");
            context.startService(serve);
        } else if (eggAmount == CONSTANTS.CONSTANT_TWO_EGGS) {
            Intent serve = new Intent(context, EggService.class);
            serve.putExtra("val", "2");
            context.startService(serve);
        } else if (eggAmount == CONSTANTS.CONSTANT_MINUS_EGG) {
            Intent serve = new Intent(context, EggService.class);
            serve.putExtra("val", "-1");
            context.startService(serve);
        } else if (eggAmount == CONSTANTS.CONSTANT_MAKE_BREAKFAST) {
            Intent serve = new Intent(context, EggService.class);
            serve.putExtra("val", "-6");
            context.startService(serve);
        }
    }

}

