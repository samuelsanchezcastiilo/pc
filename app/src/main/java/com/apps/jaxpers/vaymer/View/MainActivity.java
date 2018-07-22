package com.apps.jaxpers.vaymer.View;




import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.apps.jaxpers.vaymer.Apdapters.VehicleAdapter;
import com.apps.jaxpers.vaymer.Controller.Notifications;
import com.apps.jaxpers.vaymer.Data.DataVehiclesUser;
import com.apps.jaxpers.vaymer.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity  {

    private static final String TAG = "Ma";
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private DataVehiclesUser dbHelper;
    private VehicleAdapter vehicleAdapter;
    private DatabaseReference databaseReference;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_vehicles);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(),LinearLayoutManager.HORIZONTAL));
        mLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        mRecyclerView.setLayoutManager(mLayoutManager);


        //alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        //final Calendar calendar = new Calendar.getInstance();
        //final Intent intent = new Intent(getApplicationContext(),Notifications.class);


        /*FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        String ciudades ;
        ciudades = databaseReference.child("Ciudades").child("cucuta").toString();

        Log.e(TAG, "onCreate"+ ciudades);*/
        notifycationPicoPlaca();
        dataVehicles();
        Button opennewdialog = (Button)findViewById(R.id.add_new_vehicle);
        opennewdialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),NewVehicle.class);
                startActivity(intent);
                //DialogFragment dialogFragment = new DialogAlarm();
                //dialogFragment.show(getSupportFragmentManager(),"DialogAlarm");

            }
        });

    }

    public  void notifycationPicoPlaca()
    {
        Intent i = new Intent(getApplicationContext(), Notifications.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, i, 0);
        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
        long updateInterval = AlarmManager.INTERVAL_FIFTEEN_MINUTES;
        am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + updateInterval, updateInterval, pendingIntent);
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        dataVehicles();
        Log.e(TAG, "onRestart: " );
    }

    @Override
    protected void onPause() {
        super.onPause();
        dataVehicles();
    }

    @Override
    protected void onResume() {
        super.onResume();
        dataVehicles();
    }

    public void dataVehicles(){
        dbHelper = new DataVehiclesUser(this);
        vehicleAdapter = new VehicleAdapter(dbHelper.vehicleList(),getApplicationContext(),mRecyclerView);
        mRecyclerView.setAdapter(vehicleAdapter);
    }

    @OnClick(R.id.openAlarm)
    public void openTime(){

        DialogFragment dialogFragment = new DialogAlarm();
        dialogFragment.show(getSupportFragmentManager(),"DialogAlarm");

    }

}
