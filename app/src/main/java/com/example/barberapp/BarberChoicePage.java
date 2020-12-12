package com.example.barberapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barberapp.Model.Barber;
import com.example.barberapp.Model.Service;
import com.example.barberapp.Recycler.BarberChoiceAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BarberChoicePage extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ImageView backbutton ;
    TextView change_p,contact_us;
    public String nameOfCollection = "barber";
    RecyclerView recyclerView;
    ArrayList<Barber> barberlist = new ArrayList<>();
    ArrayList<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barber_choice_page);
        backbutton = findViewById(R.id.back_button4);
        change_p = findViewById(R.id.change_profile);
        contact_us= findViewById(R.id.contact_us);
        recyclerView =findViewById(R.id.recyclerView2);
        setBackToMenu();
        setChangeProfile();
        setContactUs();
        setTheRecyclerView();
        setLoadFromFireBase();
    }

    private void setLoadFromFireBase() {
        db.collection(nameOfCollection).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task)
            {
                for (int i = 0; i < task.getResult().getDocuments().size(); i++)
                {
                    List<Service> services =  task.getResult().getDocuments().get(i).toObject(Barber.class).getService();
                    ArrayList<String> timeSlot =  task.getResult().getDocuments().get(i).toObject(Barber.class).getTimeSlot();
                    Barber barber = new Barber( task.getResult().getDocuments().get(i).get("name").toString(), task.getResult().getDocuments().get(i).get("barberId").toString(), task.getResult().getDocuments().get(i).get("phone").toString()
                            ,services,timeSlot);
                   list.add((String) task.getResult().getDocuments().get(i).get("name"));
                    barberlist.add(barber);
                    setTheRecyclerView();
                }
            }
        });
    }
    public void setBackToMenu() {
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public void setChangeProfile() {
        change_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BarberChoicePage.this, ProfilePage.class);
                startActivity(intent);
            }
        });
    }
    public void setContactUs() {
        contact_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BarberChoicePage.this, ContactUsPage.class);
                startActivity(intent);
            }
        });
    }
    public void setTheRecyclerView(){
        BarberChoiceAdapter barberChoiceAdapter =new BarberChoiceAdapter(this, barberlist);
        recyclerView.setAdapter(barberChoiceAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
