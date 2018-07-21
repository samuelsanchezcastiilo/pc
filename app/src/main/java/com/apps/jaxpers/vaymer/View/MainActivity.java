package com.apps.jaxpers.vaymer.View;




import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.apps.jaxpers.vaymer.Apdapters.VehicleAdapter;
import com.apps.jaxpers.vaymer.Data.DataVehiclesUser;
import com.apps.jaxpers.vaymer.R;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity  {

    private static final String TAG = "Ma";
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private DataVehiclesUser dbHelper;
    private VehicleAdapter vehicleAdapter;


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

        dataVehicles();
        Button opennewdialog = (Button)findViewById(R.id.add_new_vehicle);
        opennewdialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),NewVehicle.class);
                startActivity(intent);
                //DialogFragment dialogFragment = new DialogNewVehicle();
                //dialogFragment.show(getSupportFragmentManager(),"DialogNewVehicle");
            }
        });

    }
    @Override
    protected void onRestart() {
        super.onRestart();
        dataVehicles();
        Log.e(TAG, "onRestart: " );
    }
    public void dataVehicles(){
        dbHelper = new DataVehiclesUser(this);
        vehicleAdapter = new VehicleAdapter(dbHelper.vehicleList(),getApplicationContext(),mRecyclerView);
        mRecyclerView.setAdapter(vehicleAdapter);
    }

}
