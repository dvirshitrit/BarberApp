package com.example.barberapp.Recycler;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barberapp.Model.Barber;
import com.example.barberapp.R;
import com.example.barberapp.ServiceSelectionPage;

import java.io.Serializable;
import java.util.ArrayList;

public class BarberChoiceAdapter extends RecyclerView.Adapter<BarberChoiceAdapter.MyviewHolder> {
    ArrayList<Barber> barberlist = new ArrayList<>();
    Context context;
    public BarberChoiceAdapter(Context context, ArrayList<Barber> barberlist) {
        this.context =context;
        this.barberlist = barberlist;
    }

    @NonNull
    @Override
    public BarberChoiceAdapter.MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_recycler_barber_choise,parent,false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BarberChoiceAdapter.MyviewHolder holder, final int position) {
        holder.barbers.setText(barberlist.get(position).getName());
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), ServiceSelectionPage.class);
                intent.putExtra("currentBarber", (Serializable) barberlist.get(position));
                v.getContext().startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return barberlist.size();
    }
    public class MyviewHolder extends RecyclerView.ViewHolder {
        TextView barbers;
        ConstraintLayout constraintLayout;
        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            barbers = itemView.findViewById(R.id.barber);
            constraintLayout=itemView.findViewById(R.id.container);
        }
    }
}
