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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import 	java.util.Calendar;

import static android.support.constraint.Constraints.TAG;

public class Notifications extends BroadcastReceiver {
    public static final int REQUEST_CODE = 12345;
    private SQLiteDatabase bd;
    private DataVehiclesUser dataVehiclesUser;
    private Cursor fila;
    private Cursor filavehicle;
    private int hour,minute;
    private DatabaseReference databaseReference;
    @Override
    public void onReceive(final Context context, Intent intent) {
        Intent i =  new Intent(context, ServiceAlarm.class);
        context.startService(i);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
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
                String id_ciudad = "cucuta";
                id_ciudad = id_ciudad+"_particulares";
                databaseReference = FirebaseDatabase.getInstance().getReference("RestricionCiudades/"+id_ciudad);
                databaseReference.orderByKey().addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.getValue() != null)
                        {
                            String dia = dataSnapshot.child(getDay()).getValue().toString();
                            dia = dia.replace("-","");
                            filavehicle =bd.rawQuery("SELECT digito FROM vehicles WHERE  digito LIKE'%"+dia+"%'",null);
                            if (filavehicle.moveToFirst())
                            {
                                showNotificatiom(context);
                            }

                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
                Log.d(TAG, "en la consulta sql");
            }
        }
        bd.close();






    }

    public String   getDay()
    {
        int  dayString = 0;
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        //Log.e(TAG, "getDay: "+ day );
        switch (day){
            case 1:
                dayString = 6;
                break;
            case 2:
                dayString = 0;
                break;
            case 3:
                dayString = 1;
                break;
            case 4:
                dayString = 2;
                break;
            case 5:
                dayString = 3;
                break;
            case 6:
                dayString = 4;
                break;
            case 7:
                dayString = 5;
                break;

        }
        return  String.valueOf(dayString);
    }

    public void showNotificatiom(Context context)
    {
        Intent intent =  new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent,0);
        Uri defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        long[] pattern = new long[]{2000, 1000, 2000};
        NotificationCompat.Builder builder =  new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_car_24dp)
                .setContentTitle("Hoy Tienes Pico y placa")
                .setContentText("revisa tus vehiculos hoy tienes pico y placa")
                .setAutoCancel(true)
                .setSound(defaultSound)
                .setVibrate(pattern)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setContentIntent(pendingIntent);


        builder.setDefaults(Notification.DEFAULT_LIGHTS);

        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(0, builder.build());
    }
}
