package com.apps.jaxpers.vaymer.Data;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.List;

public class FirebaseData {

    private static final String PARTICULARES = "_particulares";
    private static final String PUBLICOS = "publico";
    private DatabaseReference databaseReference;
    private String digitoPublicos;
    private String digitoParticulares;

    public FirebaseData(String digitoPublicos, String digitoParticulares) {
        this.digitoPublicos = digitoPublicos;
        this.digitoParticulares = digitoParticulares;
        databaseReference  = FirebaseDatabase.getInstance().getReference();
    }

    public FirebaseData() {

        databaseReference  = FirebaseDatabase.getInstance().getReference();
    }

    public String getDigitoPublicos(String ciudad) {
        databaseReference = FirebaseDatabase.getInstance().getReference("RestricionCiudades/"+ciudad);
        databaseReference.child(getDay()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                digitoPublicos = dataSnapshot.getValue().toString();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return digitoPublicos;
    }

    public void setDigitoPublicos(String digitoPublicos) {
        this.digitoPublicos = digitoPublicos;
    }

    public String getDigitoParticulares() {
        return digitoParticulares;
    }

    public void setDigitoParticulares(String digitoParticulares) {
        this.digitoParticulares = digitoParticulares;
    }

    public String   getDay()
    {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        String dayString = null;
        switch (day){
            case 1:
                dayString = "domingo";
                break;
            case 2:
                dayString = "lunes";
                break;
            case 3:
                dayString = "martes";
                break;
            case 4:
                dayString = "miercoles";
                break;
            case 5:
                dayString = "jueves";
                break;
            case 6:
                dayString = "viernes";
                break;
            case 7:
                dayString = "sabado";
                break;

        }
        return  dayString;
    }
}
