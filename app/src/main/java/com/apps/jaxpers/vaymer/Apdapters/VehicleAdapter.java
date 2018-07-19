package com.apps.jaxpers.vaymer.Apdapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.apps.jaxpers.vaymer.Model.Vehicle;
import com.apps.jaxpers.vaymer.R;

import java.util.List;

public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.ViewHolder>{

    private List<Vehicle> mVehicleList;
    private Context context;
    private RecyclerView recyclerView;


    public VehicleAdapter(List<Vehicle> mVehicleList, Context context, RecyclerView recyclerView) {
        this.mVehicleList = mVehicleList;
        this.context = context;
        this.recyclerView = recyclerView;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.card_recycler, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Vehicle vehicle = mVehicleList.get(position);
        holder.name_description_vehicle.setText(vehicle.getNameVehicle());
        holder.type_class_vehicle.setText(vehicle.getClassVehicle());
        holder.type_vehicle.setText(vehicle.getTypeVehicle());
        holder.digito.setText(vehicle.getDigitoVehicle());
    }

    @Override
    public int getItemCount() {
        return mVehicleList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView name_description_vehicle;
        public TextView type_vehicle;
        public TextView type_class_vehicle;
        public TextView digito;


        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            name_description_vehicle = (TextView) v.findViewById(R.id.name_description_vehicle);
            type_vehicle = (TextView) v.findViewById(R.id.type_vehicle);
            type_class_vehicle = (TextView) v.findViewById(R.id.type_class_vehicle);
            digito = (TextView) v.findViewById(R.id.digito);





        }
    }


}
