package com.example.barberapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barberapp.Model.Barber;
import com.example.barberapp.Model.Service;
import com.example.barberapp.Recycler.TimeSlotAdapter;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.HorizontalCalendarView;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

public class TimeSlotPage extends AppCompatActivity  {
    public static String date=null;
    public static final String KEY_DISPLAY_TIME_SLOT = "DISPLAY_TIMESLOT";
    ImageView backbutton;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    LocalBroadcastManager localBroadcastManager;
    Calendar selected_date;
    Service service;
    Barber barber;
    Button queue_button;
    RecyclerView recycler_time_slot;
    @BindView(R.id.calenderView)
    HorizontalCalendarView calendarView;
    SimpleDateFormat simpleDateFormat;
    View itemView;
    BroadcastReceiver displayTimeSlot = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Calendar date =Calendar.getInstance();
            date.add(Calendar.DATE,0);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_slot_page);
        barber=(Barber)getIntent().getExtras().get("currentBarber");
        service=(Service)getIntent().getExtras().get("service");
        backbutton = findViewById(R.id.back_button9);
        queue_button = findViewById(R.id.queue);
        localBroadcastManager = LocalBroadcastManager.getInstance(getApplicationContext());
        localBroadcastManager.registerReceiver(displayTimeSlot,new IntentFilter(KEY_DISPLAY_TIME_SLOT));
        simpleDateFormat = new SimpleDateFormat("dd_MM_yyyy");
        recycler_time_slot=findViewById(R.id.recycler_time_slot);
        selected_date = Calendar.getInstance();
        selected_date.add(Calendar.DATE,0);
        setTheRecyclerView();
        queue_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(date==null){
                    Toast.makeText(TimeSlotPage.this,"יש לבחור תור",Toast.LENGTH_SHORT).show();
                }else{

                    ArrayList<String>timeSlot=barber.getTimeSlot();
                    Calendar date =Calendar.getInstance();
                    date.add(Calendar.DATE,0);

                    String date_="נקבע תור בתאריך: "+ selected_date.getTime().toLocaleString().replace("0:00:00","")+"\n";
                    String time="בשעה: "+ TimeSlotPage.date+"\n";
                    String name="שם הלקוח: "+ExistPage.user.getName()+"\n";
                    String phone="מספר הטלפון של הלקוח: "+ExistPage.user.getPhone()+"\n";
                    String type= "סוג הטיפול הוא "+service.getName()+"\n";
                    timeSlot.add(date_+time+name+phone+type);
                    db.collection("barber").document(barber.getBarberId()).update("timeSlot",timeSlot);
                    Intent intent = new Intent(TimeSlotPage.this, QueueConfirmationPage.class);
                    intent.putExtra("timeSlot2",date_+time+name+phone+type);

                    ArrayList<String> queuesNotAvailable=new ArrayList<>();
                    if(barber.getService().get((Integer) getIntent().getExtras().get("indexOfService")).getQueuesNotAvailable()!=null){
                        queuesNotAvailable=barber.getService().get((Integer) getIntent().getExtras().get("indexOfService")).getQueuesNotAvailable();
                    }
                    queuesNotAvailable.add(service.getName()+"|"+selected_date.getTime().toLocaleString().replace("0:00:00","")+"|"+TimeSlotPage.date);
                    barber.getService().get((Integer) getIntent().getExtras().get("indexOfService")).setQueuesNotAvailable(queuesNotAvailable);
                    db.collection("barber").document(barber.getBarberId()).set(barber);
                    startActivity(intent);
                }
            }
        });
        init(itemView);
        setBackToMenu();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        date=null;
    }

    public void setBackToMenu() {
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void init(View itemView) {
        TimeSlotAdapter timeSlotAdapter = new TimeSlotAdapter(TimeSlotPage.this,barber,(Integer) getIntent().getExtras().get("indexOfService"),selected_date.getTime().toLocaleString().replace(" ","|"));
        recycler_time_slot.setAdapter(timeSlotAdapter);
        recycler_time_slot.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(TimeSlotPage.this,3);
        recycler_time_slot.setLayoutManager(gridLayoutManager);

        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.DATE,0);
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.DATE,10);



     HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(findViewById(R.id.line1),R.id.calenderView).range(startDate,endDate).datesNumberOnScreen(1)
                .mode(HorizontalCalendar.Mode.DAYS)
                .defaultSelectedDate(startDate)
                .build();

        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
                if (selected_date.getTimeInMillis()!= date.getTimeInMillis())
                {
                    TimeSlotAdapter timeSlotAdapter = new TimeSlotAdapter(TimeSlotPage.this,barber,(Integer) getIntent().getExtras().get("indexOfService"),selected_date.getTime().toLocaleString().replace(" ","|"));
                    recycler_time_slot.setAdapter(timeSlotAdapter);
                    recycler_time_slot.setHasFixedSize(true);
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(TimeSlotPage.this,3);
                    recycler_time_slot.setLayoutManager(gridLayoutManager);
                    selected_date= date;
                    setTheRecyclerView();
                }
            }
        });
    }

    public void setTheRecyclerView(){
        TimeSlotAdapter timeSlotAdapter = new TimeSlotAdapter(TimeSlotPage.this,barber,(Integer) getIntent().getExtras().get("indexOfService"),selected_date.getTime().toLocaleString().replace(" ","|"));
        recycler_time_slot.setAdapter(timeSlotAdapter);
    }
}