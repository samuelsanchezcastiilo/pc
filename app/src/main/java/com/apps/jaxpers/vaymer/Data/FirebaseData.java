package com.apps.jaxpers.vaymer.Data;

import android.util.Log;

import com.google.android.gms.internal.firebase_database.zzcc;
import com.google.android.gms.internal.firebase_database.zzch;
import com.google.android.gms.internal.firebase_database.zzck;
import com.google.android.gms.internal.firebase_database.zzdn;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static android.content.ContentValues.TAG;

public class FirebaseData  {

    private  DatabaseReference databaseReference;
    private  String ciudad ;
    private  String digito ;
    private  String salida;
    private  List<String> dias;

    public FirebaseData( String ciudad) {

        this.ciudad = ciudad;
        dias = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("RestricionCiudades/"+ciudad);

        dias.add("prueba");
    }


    public void prueba()
    {
        Log.e(TAG, "prueba: en el metodo" );
        ciudad = ciudad+"_particulare";
        databaseReference = FirebaseDatabase.getInstance().getReference("RestricionCiudades/"+ciudad);
        databaseReference.orderByKey().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for ( DataSnapshot dataSnapshot1: dataSnapshot.getChildren()) {
                    String iterator = dataSnapshot1.getChildren().toString();
                    iterator = iterator.replace("-","");
                    dias.add(iterator);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public String  istPartticulares(){
        String salidad = "8";
        prueba();
        if (dias.size() <= 1)
        {
            prueba();
        }


        Log.e(TAG, "istPartticulares: "+ dias.size()  );
        return salidad;
    }

}
