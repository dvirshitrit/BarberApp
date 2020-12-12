package com.example.barberapp.Recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barberapp.BarberLoginPage;
import com.example.barberapp.R;

public class MonthlyDiaryAdapter extends RecyclerView.Adapter<MonthlyDiaryAdapter.MyViewHolder> {
    Context context;

    public MonthlyDiaryAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MonthlyDiaryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_recycler_barber_diary,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MonthlyDiaryAdapter.MyViewHolder holder, int position) {
        holder.client.setText(BarberLoginPage.currentBarber.getTimeSlot().get(position));
    }

    @Override
    public int getItemCount() {
        return BarberLoginPage.currentBarber.getTimeSlot().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView client;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            client =itemView.findViewById(R.id.client);
        }
    }
}
