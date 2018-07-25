package com.apps.jaxpers.vaymer.View;




import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.apps.jaxpers.vaymer.Apdapters.VehicleAdapter;
import com.apps.jaxpers.vaymer.Data.DataVehiclesUser;

import com.apps.jaxpers.vaymer.Data.FirebaseData;
import com.apps.jaxpers.vaymer.Model.ciudades;
import com.apps.jaxpers.vaymer.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity  {
    private static ConnectivityManager manager;
    private static final String TAG = "Ma";
    private static final String PARTICULARES = "_particulares";
    private static final String PUBLICOS = "_publicos";
    private String selectSpiner ;

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private DataVehiclesUser dbHelper;
    private VehicleAdapter vehicleAdapter;
    private DatabaseReference databaseReference;
    private List<String> lciudades;
    private List<String> ldigitospubl;
    private List<String> ldigitospart;

    private ArrayAdapter<String> adapterCiudades;
    private ciudades ciudades;
    private String idCiudad;
    @BindView(R.id.ciudades)
    Spinner spinnerCiudades;
    @BindView(R.id.digitos_particulares)
    TextView digitoParticulares;
    @BindView(R.id.digito_publicos)
    TextView digitoPublico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        ButterKnife.bind(this);
        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_vehicles);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(),LinearLayoutManager.HORIZONTAL));
        mLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        mRecyclerView.setLayoutManager(mLayoutManager);


        databaseReference = FirebaseDatabase.getInstance().getReference();
        lciudades =  new ArrayList<>();
        ldigitospubl = new ArrayList<>();
        ldigitospart = new ArrayList<>();
        adapterCiudades = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,lciudades);
        spinnerCiudades.setAdapter(adapterCiudades);
        getCiudades();
        selectSpinner();



        Button opennewdialog = (Button)findViewById(R.id.add_new_vehicle);
        opennewdialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),NewVehicle.class);
                startActivity(intent);

            }
        });

    }
    public void getCiudades(){
        databaseReference.child("ciudades").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                lciudades.clear();
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    ciudades  = dataSnapshot1.getValue(ciudades.class);
                    lciudades.add(ciudades.getNombre());
                }
                adapterCiudades.notifyDataSetChanged();
                dataVehicles();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }

    public void getDigitosPublicos(String id_ciudad){
        id_ciudad = id_ciudad+PUBLICOS;
        digitoPublico.setText("");


        Log.e(TAG, "el digito: "+getDay());
        databaseReference = FirebaseDatabase.getInstance().getReference("RestricionCiudades/"+id_ciudad);
        databaseReference.orderByKey().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e(TAG, "onChildAddedPublicos: "+ dataSnapshot.child(getDay()).getValue());
                if (dataSnapshot.getValue() != null){
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                    {
                        Log.e(TAG, "onDataChange: "+ dataSnapshot1.getValue());
                        ldigitospubl.add(dataSnapshot1.getValue().toString());
                    }
                    digitoPublico.setText(dataSnapshot.child(getDay()).getValue().toString());
                }else  {
                    digitoPublico.setText("N/A");
                }



            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void getDigitosParticulares(String id_ciudad){
        id_ciudad = id_ciudad+PARTICULARES;
        digitoParticulares.setText("");

        databaseReference = FirebaseDatabase.getInstance().getReference("RestricionCiudades/"+id_ciudad);
        databaseReference.orderByKey().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue() != null)
                {
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                    {
                        Log.e(TAG, "onDataChange: "+ dataSnapshot1.getValue());
                        ldigitospart.add(dataSnapshot1.getValue().toString());
                    }

                    digitoParticulares.setText(dataSnapshot.child(getDay()).getValue().toString());
                }else
                {
                    digitoParticulares.setText("N/A");
                }





            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    public static boolean isOnline(Context context) {

        ConnectivityManager connectivityManager = (ConnectivityManager)  context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();
    }



    public String   getDay()
    {
        int  dayString = 0;
        Calendar calendar = Calendar.getInstance();
       int day = calendar.get(Calendar.DAY_OF_WEEK);
        Log.e(TAG, "getDay: "+ day );
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


    public String  selectSpinner()
    {
        spinnerCiudades.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                  selectSpiner = adapterView.getItemAtPosition(i).toString();
                   getDigitosPublicos(selectSpiner);
                   getDigitosParticulares(selectSpiner);
                   dataVehicles();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        return  selectSpiner;

    }
    @Override
    protected void onRestart() {
        super.onRestart();
        dataVehicles();
        Log.e(TAG, "onRestart: " );
    }
    @Override
    protected void onResume() {
        super.onResume();
        dataVehicles();
    }
    public void dataVehicles(){
        dbHelper = new DataVehiclesUser(this);
            vehicleAdapter = new VehicleAdapter(dbHelper.vehicleList(),getApplicationContext(),mRecyclerView,ldigitospubl,ldigitospart);
            mRecyclerView.setAdapter(vehicleAdapter);





    }
    @OnClick(R.id.openAlarm)
    public void openTime(){

        DialogFragment dialogFragment = new DialogAlarm();
        dialogFragment.show(getSupportFragmentManager(),"DialogAlarm");

    }

}
