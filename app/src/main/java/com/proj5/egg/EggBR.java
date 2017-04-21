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
//        Toast.makeText(context, "eggAmount: " + EggService.getCurrentEggCount(), Toast.LENGTH_SHORT).show();
        if (eggAmount == 1) {
            EggService.incrementEggCountOnce();
        } else if (eggAmount == 2) {
            EggService.incrementEggCountTwice();
        } else if (eggAmount == -1) {
            EggService.decrementEggCountOnce();
        } else if (eggAmount == -6) {
            EggService.makeBreakfast();
        }
    }

}

