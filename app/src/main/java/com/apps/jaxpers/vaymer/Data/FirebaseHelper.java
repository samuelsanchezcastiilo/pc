package com.apps.jaxpers.vaymer.Data;


import android.util.Log;

import com.apps.jaxpers.vaymer.Model.ciudades;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.ContentValues.TAG;

public class FirebaseHelper {

    private DatabaseReference databaseReference;

    public static  class SingletonFirebase{

        private  static final FirebaseHelper INTANCE = new FirebaseHelper();

    }
    public static FirebaseHelper getIntance(){
        return  SingletonFirebase.INTANCE;
    }

    public FirebaseHelper()
    {
        databaseReference = FirebaseDatabase.getInstance().getReference();
        //FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }


    public void getCiudades(){

        databaseReference.child("Ciudades").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()) {
                    ciudades ciudades = dataSnapshot1.getValue(ciudades.class);



                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }





}
