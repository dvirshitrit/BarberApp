package com.example.barberapp.Recycler;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barberapp.TimeSlotPage;
import com.example.barberapp.Model.Barber;
import com.example.barberapp.Model.Service;
import com.example.barberapp.R;

import java.util.ArrayList;
import java.util.List;

public class ServiceSelectionAdapter extends RecyclerView.Adapter<ServiceSelectionAdapter.MyviewHolder> {

    List<Service> serviceSelection =new ArrayList<>();
    int images[];
    Context context;
    Barber barber;

    public ServiceSelectionAdapter(Context context, ArrayList<Service> serviceSelection,  int img[], Barber barber){
    this.context =context;
    this.serviceSelection = serviceSelection;
    this.images = img;
    this.barber=barber;
}
    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_recycler_service_selection,parent,false);
        return new MyviewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final MyviewHolder holder, final int position) {
        holder.service.setText(serviceSelection.get(position).getName());
        holder.price.setText(serviceSelection.get(position).getPrice());
        holder.myImage.setImageResource(images[position]);
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), TimeSlotPage.class);
                intent.putExtra("service",serviceSelection.get(position));
                intent.putExtra("indexOfService",position);
                intent.putExtra("currentBarber", barber);
                v.getContext().startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return serviceSelection.size();
    }
    public class MyviewHolder extends RecyclerView.ViewHolder {
        TextView service,price;
        ImageView myImage;
        ConstraintLayout constraintLayout;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            service = itemView.findViewById(R.id.barber);
            price = itemView.findViewById(R.id.price);
            myImage= itemView.findViewById(R.id.myImageView);
            constraintLayout=itemView.findViewById(R.id.container);
        }
    }
}
