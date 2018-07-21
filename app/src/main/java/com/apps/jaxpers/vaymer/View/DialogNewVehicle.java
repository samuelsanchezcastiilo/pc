package com.apps.jaxpers.vaymer.View;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.apps.jaxpers.vaymer.Data.DataVehiclesUser;
import com.apps.jaxpers.vaymer.Model.Vehicle;
import com.apps.jaxpers.vaymer.R;

public class DialogNewVehicle extends DialogFragment {

    private EditText nameVehicle;
    private EditText digito;
    private Spinner typeVehicle;
    private Spinner class_vehicle;
    private  DataVehiclesUser dataVehiclesUser;

    public DialogNewVehicle() {
    }

    public  interface Actualizar{
        public void refresh();

    }
    Actualizar actualizar;



    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return createNewVehicle();
    }
    View view;
    public AlertDialog createNewVehicle(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view =inflater.inflate(R.layout.fragment_add_vehicle,null);
         nameVehicle = (EditText)view.findViewById(R.id.name_vehicle);
         digito =      (EditText)view.findViewById(R.id.digito);
         typeVehicle = (Spinner)view.findViewById(R.id.type_vehiculo) ;
         class_vehicle = (Spinner)view.findViewById(R.id.class_vehicle) ;

         Button register_vehicle = (Button)view.findViewById(R.id.register_vehicle);

        register_vehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addVehicle();
            }
        });
        builder.setView(view);
        return builder.create();
    }



    public void addVehicle(){

        String name = nameVehicle.getText().toString();

        String type = typeVehicle.getSelectedItem().toString();
        String clase = class_vehicle.getSelectedItem().toString();
        dataVehiclesUser = new DataVehiclesUser(getContext());
        if(name.isEmpty()){
            nameVehicle.setError("completa este campo");
            return;
        }

        if (digito.getText().toString().isEmpty()){
            digito.setError("completa el campo");
            return;
        }else if(Integer.parseInt(digito.getText().toString()) < 0 || Integer.parseInt(digito.getText().toString()) > 9){
            digito.setError("numero de placa no valido");
            return;
        }
            int number = Integer.parseInt(digito.getText().toString());

        Vehicle vehicle = new Vehicle(name,number,type,clase);
        dataVehiclesUser.saveNewVehicle(vehicle);
        actualizar.refresh();
        Toast.makeText(getContext(),"Nuevo Vehiculo Registrado",Toast.LENGTH_LONG).show();

        dismiss();


    }


}
