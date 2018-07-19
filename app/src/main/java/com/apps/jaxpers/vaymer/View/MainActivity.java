package com.apps.jaxpers.vaymer.View;



import android.app.AlertDialog;
import android.content.Context;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;


import com.apps.jaxpers.vaymer.Apdapters.VehicleAdapter;
import com.apps.jaxpers.vaymer.Data.DataVehiclesUser;
import com.apps.jaxpers.vaymer.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private DataVehiclesUser dbHelper;
    private VehicleAdapter adapter;
    private String filter = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_vehicles);
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        Button opennewdialog = (Button)findViewById(R.id.add_new_vehicle);

        //populate recyclerview
        //populaterecyclerView(filter);
        opennewdialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dialogFragment = new DialogNewVehicle();
                dialogFragment.show(getSupportFragmentManager(),"DialogNewVehicle");
            }
        });

    }








    public AlertDialog createLoginDialogo() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getApplication());
        LayoutInflater inflater = (LayoutInflater) getApplication()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v = inflater.inflate(R.layout.fragment_add_vehicle, null);
        builder.setView(v);
        Button newvehicle = (Button) v.findViewById(R.id.register_vehicle);


        newvehicle.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Crear Cuenta...

                    }
                }
        );

        return builder.create();
    }
}
