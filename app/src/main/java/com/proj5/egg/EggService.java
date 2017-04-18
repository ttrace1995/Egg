package com.proj5.egg;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by traceys5 on 4/18/17.
 */
public class EggService extends Service {

    private int currentEggCount;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
