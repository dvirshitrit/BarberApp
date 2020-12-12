package com.example.barberapp.Recycler;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barberapp.Model.Barber;
import com.example.barberapp.TimeSlotPage;
import com.example.barberapp.Model.TimeSlot;
import com.example.barberapp.R;

public class TimeSlotAdapter extends RecyclerView.Adapter<TimeSlotAdapter.MyViewHolder> {
    Context context;
    public static final int TIME_SLOT_TOTAL = 20;
    Barber barber;
    Integer indexOfService;
    String selectedDate;


    public TimeSlotAdapter(Context context, Barber barber, Integer indexOfService, String selectedDay) {

        this.selectedDate=selectedDay.split("\\|")[1].replace(",","");
        this.context = context;
        this.barber = barber;
        this.indexOfService=indexOfService;
    }

    @NonNull
    @Override
    public TimeSlotAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_recycler_time_slot, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int position) {

        myViewHolder.text_time_slot.setText(new StringBuilder(TimeSlot.convertTimeSlotToString(position)).toString());
        myViewHolder.card_time_slot.setContentDescription("" + position);
        myViewHolder.card_time_slot.setCardBackgroundColor(context.getResources().getColor(android.R.color.white));
        myViewHolder.text_time_slot_description.setText("זמין");
        myViewHolder.text_time_slot_description.setTextColor(context.getResources().getColor(android.R.color.black));

        try{
            for (String queue:barber.getService().get(indexOfService).getQueuesNotAvailable()) {
                String time= queue.split("\\|")[2];
                String timeOnCurrentPick=TimeSlot.convertTimeSlotToString(position);
                String date=queue.split("\\|")[1].replace(" ","|").split("\\|")[1].replace(",","");

                if(
                        time.equals(timeOnCurrentPick)&&this.selectedDate.equals(date)

                ){
                    myViewHolder.card_time_slot.setCardBackgroundColor(Color.parseColor("#1d97ff"));
                    myViewHolder.text_time_slot_description.setText("לא זמין");
                    myViewHolder.text_time_slot_description.setTextColor(context.getResources().getColor(android.R.color.white));

                    break;
                }else
                {
                    myViewHolder.card_time_slot.setCardBackgroundColor(context.getResources().getColor(android.R.color.white));
                    myViewHolder.text_time_slot_description.setText("זמין");
                    myViewHolder.text_time_slot_description.setTextColor(context.getResources().getColor(android.R.color.black));
                }
            }
        }catch (Exception e){

        }

        myViewHolder.card_time_slot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myViewHolder.text_time_slot_description.getText().toString().equals("זמין") && TimeSlotPage.date == null) {
                    myViewHolder.card_time_slot.setCardBackgroundColor(Color.parseColor("#1d97ff"));
                    TimeSlotPage.date = "" + myViewHolder.text_time_slot.getText().toString();
                    myViewHolder.text_time_slot_description.setText("לא זמין");
                    Toast.makeText(context, "לשמירה לחץ על הכפתור", Toast.LENGTH_SHORT).show();


                } else if (myViewHolder.text_time_slot_description.getText().toString().equals("לא זמין") && TimeSlotPage.date != null) {
                    TimeSlotPage.date = null;
                    myViewHolder.card_time_slot.setCardBackgroundColor(Color.parseColor("white"));
                    myViewHolder.text_time_slot_description.setText("זמין");
                    Toast.makeText(context, "ביטלת את הבחירה לתור זה", Toast.LENGTH_SHORT).show();
                } else {
                    if (myViewHolder.text_time_slot_description.getText().toString().equals("לא זמין")) {
                        Toast.makeText(context, "שים לב תור זה אינו פנוי", Toast.LENGTH_SHORT).show();
                    } else if (TimeSlotPage.date != null) {
                        Toast.makeText(context, "ניתן לבחור תור אחד בלבד", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return TIME_SLOT_TOTAL;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView text_time_slot, text_time_slot_description;
        CardView card_time_slot;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            card_time_slot = itemView.findViewById(R.id.card_barber_diary);
            text_time_slot = itemView.findViewById(R.id.text_time_slot);
            text_time_slot_description = itemView.findViewById(R.id.textDescription);
        }
    }
}
