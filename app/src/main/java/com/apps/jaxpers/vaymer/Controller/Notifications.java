package com.apps.jaxpers.vaymer.Controller;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.apps.jaxpers.vaymer.Data.DataVehiclesUser;
import com.apps.jaxpers.vaymer.R;
import com.apps.jaxpers.vaymer.Service.ServiceAlarm;
import com.apps.jaxpers.vaymer.View.MainActivity;

import 	java.util.Calendar;

import static android.support.constraint.Constraints.TAG;

public class Notifications extends BroadcastReceiver {
    public static final int REQUEST_CODE = 12345;
    private SQLiteDatabase bd;
    private DataVehiclesUser dataVehiclesUser;
    private Cursor fila;
    private int hour,minute;
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i =  new Intent(context, ServiceAlarm.class);
        context.startService(i);

        Calendar calendar = Calendar.getInstance();
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);

        Log.e(TAG, "hora: "+ hour );
        Log.e(TAG, "miuto: "+ minute );

        dataVehiclesUser =  new DataVehiclesUser(context);
        bd  = dataVehiclesUser.getWritableDatabase();

        if (bd !=  null)
        {
            fila = bd.rawQuery("SELECT * FROM recordatorios WHERE hour='"+hour+"' AND minute= '"+minute+"'", null);
            if (fila.moveToFirst()){
                showNotificatiom(context);
                Log.d(TAG, "en la consulta sql");
            }
        }
        bd.close();

    }

    public void showNotificatiom(Context context)
    {
        Intent intent =  new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent,0);
        Uri defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        long[] pattern = new long[]{2000, 1000, 2000};
        NotificationCompat.Builder builder =  new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
                .setContentTitle("Hoy Tienes Pico y placa")
                .setContentText("revisa tus vehiculos hoy tienes pico y placa");
        builder.setContentIntent(pendingIntent);
        builder.setSound(defaultSound);
        builder.setDefaults(Notification.DEFAULT_LIGHTS);
        builder.setAutoCancel(true);
        builder.setVibrate(pattern);
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(0, builder.build());
    }
}
