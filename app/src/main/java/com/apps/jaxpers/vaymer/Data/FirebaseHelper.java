package com.apps.jaxpers.vaymer.Data;


import android.util.Log;
import android.widget.ArrayAdapter;

import com.apps.jaxpers.vaymer.Model.ciudades;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class FirebaseHelper {

    private DatabaseReference databaseReference;
    private List<String> lciudades;
    private static final String PARTICULARES = "_id_R_001";
    private static final String PUBLICOS = "_id_R_002";
    private String digitoPublicos;
    private String digitoParticulares;
    private List<String> listCiudades;

    public static  class SingletonFirebase{

        private  static final FirebaseHelper INTANCE = new FirebaseHelper();

    }
    public static FirebaseHelper getIntance(){
        return  SingletonFirebase.INTANCE;
    }

    public FirebaseHelper()
    {
        databaseReference = FirebaseDatabase.getInstance().getReference();
        lciudades =  new ArrayList<>();
        digitoParticulares = null;
        listCiudades = null;


    }


    public List<String> getCiudades(){

        databaseReference.child("ciudades").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                lciudades.clear();
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    ciudades ciudades  = dataSnapshot1.getValue(ciudades.class);
                    lciudades.add(ciudades.getNombre());

                }
                //adapterCiudades.notifyDataSetChanged();
                Log.e(TAG, "onDataChange firebaseHelper: "+lciudades.size() );
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
return  lciudades;
    }





}
