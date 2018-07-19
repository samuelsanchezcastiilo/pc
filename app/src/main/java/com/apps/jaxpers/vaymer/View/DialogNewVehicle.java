package com.apps.jaxpers.vaymer.View;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;

import com.apps.jaxpers.vaymer.R;

public class DialogNewVehicle extends DialogFragment {

    public DialogNewVehicle() {
    }



    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return createNewVehicle();
    }
    View view;

    public AlertDialog createNewVehicle(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view =inflater.inflate(R.layout.fragment_add_vehicle,null);

        /*builder.setPositiveButton("Guardar", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Registro();
            }
        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });*/


        builder.setView(view);

        return builder.create();
    }

}
