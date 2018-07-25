package com.apps.jaxpers.vaymer.Apdapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apps.jaxpers.vaymer.Data.DataVehiclesUser;
import com.apps.jaxpers.vaymer.Model.Vehicle;
import com.apps.jaxpers.vaymer.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.vehicleViewHolder>{

    private List<Vehicle> mVehicleList;
    private Context context;
    private RecyclerView recyclerView;
    private DataVehiclesUser dataVehiclesUser;
    private List<String> digitosPublicos;
    private  List<String> digitosPr;



    public VehicleAdapter(List<Vehicle> mVehicleList, Context context, RecyclerView recyclerView, List<String> digitosPublicos, List<String> digitosPr) {
        this.mVehicleList = mVehicleList;
        this.context = context;
        this.recyclerView = recyclerView;
        this.digitosPublicos = digitosPublicos;
        this.digitosPr = digitosPr;

    }

    @NonNull
    @Override
    public vehicleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_recycler,parent,false);
        return new vehicleViewHolder(view);
    }
    @Override
    public void onBindViewHolder(vehicleViewHolder holder, final int position) {

        final Vehicle vehicle = mVehicleList.get(position);
        holder.name_description_vehicle.setText(vehicle.getNameVehicle());
        holder.type_class_vehicle.setText(vehicle.getClassVehicle()+" con numero terminado en: ");
        holder.type_vehicle.setText(vehicle.getTypeVehicle());
        final String digito = String.valueOf(vehicle.getDigitoVehicle());
        holder.digito.setText(digito);

        if (vehicle.getTypeVehicle().equalsIgnoreCase("Carro"))
        {
            holder.imageView.setImageResource(R.drawable.ic_car_24dp);
            holder.imageView1.setImageResource(R.drawable.ic_car_24dp);
        }else
        {
            holder.imageView.setImageResource(R.drawable.ic_motorcycle_24dp);
            holder.imageView1.setImageResource(R.drawable.ic_motorcycle_24dp);
        }

            holder.d0.setBackgroundColor(Color.BLACK);



        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mVehicleList.remove(position);
                recyclerView.removeViewAt(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,mVehicleList.size());
                notifyDataSetChanged();
                dataVehiclesUser = new DataVehiclesUser(context);
                dataVehiclesUser.deletePersonRecord(Integer.parseInt(digito),context);

            }
        });
    }

    @Override
    public int getItemCount() {
        if(mVehicleList != null)return mVehicleList.size();
        else return 0;

    }
    public void remove(int position){
        mVehicleList.remove(position);
        notifyItemRemoved(position);

    }






    public class vehicleViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView name_description_vehicle;
        public TextView type_vehicle;
        public TextView type_class_vehicle;
        public TextView digito;
        public ImageButton delete;
        public ImageView imageView;
        public  ImageView imageView1;

        public TextView d0;
        public TextView d1;
        public TextView d2;
        public TextView d3;
        public TextView d4;
        public TextView d5;

        public vehicleViewHolder(View v) {
            super(v);
            name_description_vehicle = (TextView) v.findViewById(R.id.name_description_vehicle);
            type_vehicle = (TextView) v.findViewById(R.id.type_vehicle);
            type_class_vehicle = (TextView) v.findViewById(R.id.type_class_vehicle);
            digito = (TextView) v.findViewById(R.id.digito);
            delete = (ImageButton)v.findViewById(R.id.deleteVehicle);
            imageView = (ImageView)v.findViewById(R.id.imagetypeVehicle);
            imageView1 = (ImageView)v.findViewById(R.id.imagetypeVehicle1);
            d0 = (TextView)v.findViewById(R.id.day_0);
            d1 = (TextView)v.findViewById(R.id.day_1);
            d2 = (TextView)v.findViewById(R.id.day_2);
            d3 = (TextView)v.findViewById(R.id.day_3);
            d4 = (TextView)v.findViewById(R.id.day_4);
            d5 = (TextView)v.findViewById(R.id.day_5);






        }
    }



}
