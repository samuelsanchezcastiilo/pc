package com.apps.jaxpers.vaymer.View;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.apps.jaxpers.vaymer.Apdapters.VehicleAdapter;
import com.apps.jaxpers.vaymer.Data.DataVehiclesUser;
import com.apps.jaxpers.vaymer.Model.Vehicle;
import com.apps.jaxpers.vaymer.R;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewVehicle extends Activity {

    private  DataVehiclesUser dataVehiclesUser;
    private VehicleAdapter vehicleAdapter;

    @BindView(R.id.name_vehicle)
    EditText nameVehicle;
    @BindView(R.id.digito)
    EditText digito;
    @BindView(R.id.type_vehiculo)
    Spinner typeVehicle;
    @BindView(R.id.class_vehicle)
    Spinner class_vehicle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_add_vehicle);
        ButterKnife.bind(this);


    }
    @OnClick(R.id.register_vehicle)
    public void  saveVehicle(){
        String name = nameVehicle.getText().toString();

        String type = typeVehicle.getSelectedItem().toString();
        String clase = class_vehicle.getSelectedItem().toString();
        dataVehiclesUser = new DataVehiclesUser(this);
        if(name.isEmpty()){
            nameVehicle.setError("completa este campo");
            return;
        }

        if (digito.getText().toString().isEmpty()){
            digito.setError("completa el campo");
            return;
        }else if(Integer.parseInt(digito.getText().toString()) < 0 || Integer.parseInt(digito.getText().toString()) > 9){
            digito.setError("Ingrese el ultimo numero de su placa");
            return;
        }
        int number = Integer.parseInt(digito.getText().toString());

        Vehicle vehicle = new Vehicle(name,number,type,clase);
        dataVehiclesUser.saveNewVehicle(vehicle);
        dataVehiclesUser.saveAlarmDefault();
        Toast.makeText(this,"Nuevo Vehiculo Registrado",Toast.LENGTH_LONG).show();
        DataVehiclesUser dataVehiclesUser = new DataVehiclesUser(this);
        onBackPressed();
        finish();
    }


}
