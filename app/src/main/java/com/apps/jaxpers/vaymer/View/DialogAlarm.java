package com.apps.jaxpers.vaymer.View;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.apps.jaxpers.vaymer.Controller.Notifications;
import com.apps.jaxpers.vaymer.Data.DataVehiclesUser;
import com.apps.jaxpers.vaymer.R;
import 	java.util.Calendar;
import static android.content.ContentValues.TAG;

public class DialogAlarm extends DialogFragment {
    
    private  DataVehiclesUser dataVehiclesUser;
    private  TimePicker timePicker;




    public DialogAlarm() {
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return createNewVehicle();
    }
    View view;
    public AlertDialog createNewVehicle(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view =inflater.inflate(R.layout.time_alarm,null);
        timePicker = (TimePicker)view.findViewById(R.id.time);

         Button register_alarm = (Button)view.findViewById(R.id.saveAlarm);

        register_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             gethour();
         }
        });
        builder.setView(view);
        return builder.create();
    }



    @TargetApi(Build.VERSION_CODES.M)
    public void gethour(){
        Calendar calendar = Calendar.getInstance();
        int dia = calendar.get(Calendar.DAY_OF_WEEK);
        Log.e(TAG, "dia de la semana: "+ dia );
        int hour = timePicker.getHour();
        int minute = timePicker.getMinute();

        dataVehiclesUser = new DataVehiclesUser(getContext());
        if(dataVehiclesUser.getDataAlarm()){
            dataVehiclesUser.udpdateAlarma(hour,minute);
            Toast.makeText(getContext(),"Se actualizo la hora de aviso",Toast.LENGTH_LONG).show();
            Log.e(TAG, "gethour: " +timePicker.getHour());
            service();
        }else {
            dataVehiclesUser.saveAlarma(hour,minute);
            Toast.makeText(getContext(),"A esa hora te avisaremos cuando tengas pico y placa",Toast.LENGTH_LONG).show();
            service();
        }

        dismiss();
    }

    public void service(){
        Intent intent =  new Intent(getContext(), Notifications.class);
        final PendingIntent pIntent = PendingIntent.getBroadcast(getContext(), Notifications.REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        long firstMillis = System.currentTimeMillis();
        int intervalMillis = 1 * 3 * 1000;
        AlarmManager alarm = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
        alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, firstMillis, intervalMillis, pIntent);

    }






}
