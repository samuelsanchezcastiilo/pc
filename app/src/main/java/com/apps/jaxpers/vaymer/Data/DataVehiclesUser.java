package com.apps.jaxpers.vaymer.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.apps.jaxpers.vaymer.Model.Vehicle;

import java.sql.Time;
import java.util.LinkedList;
import java.util.List;

public class DataVehiclesUser extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "vehicles.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "vehicles";
    public static final String COLUMN_VEHICLE_NAME = "name";
    public static final String COLUMN_VEHICLE_TYPE = "type";
    public static final String COLUMN_VEHICLE_CLASE = "clase";
    public static final String COLUMN_VEHICLE_DIGITO = "digito";

    public static final String TABLE_ALARM = "recordatorios";
    public static final String COLUMN_ALARM_HORA= "hour";
    public static final String COLUMN_ALARM_MINUTO= "minute";

    public DataVehiclesUser(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(" CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_VEHICLE_NAME + " TEXT NOT NULL, " +
                COLUMN_VEHICLE_TYPE + " TEXT NOT NULL, " +
                COLUMN_VEHICLE_CLASE + " TEXT NOT NULL, " +
                COLUMN_VEHICLE_DIGITO + " NUMBER NOT NULL);"
        );

        sqLiteDatabase.execSQL("CREATE TABLE recordatorios (hour INTEGER, minute INTEGER,day INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ALARM);
        this.onCreate(db);
    }


    /**
     * create record
     **/
    public void saveNewVehicle(Vehicle vehicle) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_VEHICLE_NAME, vehicle.getNameVehicle());
        values.put(COLUMN_VEHICLE_TYPE, vehicle.getTypeVehicle());
        values.put(COLUMN_VEHICLE_CLASE, vehicle.getClassVehicle());
        values.put(COLUMN_VEHICLE_DIGITO, vehicle.getDigitoVehicle());
        // insert
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void saveAlarma(int hour, int minute){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ALARM_HORA,hour);
        values.put(COLUMN_ALARM_MINUTO,minute);
        db.insert(TABLE_ALARM,null,values);

    }

    public void saveAlarmDefault(){
        int hour = 6;
        int minute = 05;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ALARM_HORA,hour);
        values.put(COLUMN_ALARM_MINUTO,minute);
        db.insert(TABLE_ALARM,null,values);
    }

    public List<Vehicle> vehicleList() {
        String query;

        query = "SELECT  * FROM " + TABLE_NAME;
        List<Vehicle> vehicleLinkedList = new LinkedList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Vehicle vehicle;
        if (cursor.moveToFirst()) {
            do {
                vehicle = new Vehicle();
                vehicle.setNameVehicle(cursor.getString(cursor.getColumnIndex(COLUMN_VEHICLE_NAME)));
                vehicle.setClassVehicle(cursor.getString(cursor.getColumnIndex(COLUMN_VEHICLE_CLASE)));
                vehicle.setTypeVehicle(cursor.getString(cursor.getColumnIndex(COLUMN_VEHICLE_TYPE)));
                vehicle.setDigitoVehicle(cursor.getInt(cursor.getColumnIndex(COLUMN_VEHICLE_DIGITO)));
                vehicleLinkedList.add(vehicle);
            } while (cursor.moveToNext());
        }

        return vehicleLinkedList;
    }


    /**
     * Query only 1 record
     **/
    public Vehicle getVehicle(int digito) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT  * FROM " + TABLE_NAME + " WHERE digito=" + digito;
        Cursor cursor = db.rawQuery(query, null);

        Vehicle receivedVehicle = new Vehicle();
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            receivedVehicle.setNameVehicle(cursor.getString(cursor.getColumnIndex(COLUMN_VEHICLE_NAME)));
            receivedVehicle.setTypeVehicle(cursor.getString(cursor.getColumnIndex(COLUMN_VEHICLE_TYPE)));
            receivedVehicle.setClassVehicle(cursor.getString(cursor.getColumnIndex(COLUMN_VEHICLE_CLASE)));
            receivedVehicle.setDigitoVehicle(cursor.getInt(cursor.getColumnIndex(COLUMN_VEHICLE_DIGITO)));
        }


        return receivedVehicle;


    }

    public  boolean  getDataAlarm()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT  * FROM  recordatorios " ;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() > 0) {
         return true ;
        }
        return false;
    }




    /**
     * delete record
     **/
    public void deletePersonRecord(int digito, Context context) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE digito='" + digito + "'");
        Toast.makeText(context, "Deleted successfully.", Toast.LENGTH_SHORT).show();
    }
    /**
     * update record
     **/
    public void updateVehicleRecord(int digito, Context context, Vehicle updatedvehicle) {
        SQLiteDatabase db = this.getWritableDatabase();
        //you can use the constants above instead of typing the column names
        db.execSQL("UPDATE  " + TABLE_NAME + " SET name ='" + updatedvehicle.getNameVehicle() + "', type ='" + updatedvehicle.getTypeVehicle() + "', clase ='" + updatedvehicle.getClassVehicle() + "'  WHERE digito ='" + digito + "'");
        Toast.makeText(context, "Updated successfully.", Toast.LENGTH_SHORT).show();
    }

    public void udpdateAlarma(int hour, int minute)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("hour",hour); //These Fields should be your String values of actual column names
        cv.put("minute",minute);
        db.update(TABLE_ALARM,cv,null,null);






    }

}