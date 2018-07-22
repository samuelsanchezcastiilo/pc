package com.apps.jaxpers.vaymer.Service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import static android.content.ContentValues.TAG;

public class ServiceAlarm extends IntentService {

    public ServiceAlarm() {
        super("ServiceAlarm");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i(TAG, "onHandleIntent: en ejecucion");

    }
}
