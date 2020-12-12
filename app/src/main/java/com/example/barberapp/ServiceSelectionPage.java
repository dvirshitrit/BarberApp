package com.example.barberapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barberapp.Model.Barber;
import com.example.barberapp.Model.Service;
import com.example.barberapp.Recycler.ServiceSelectionAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ServiceSelectionPage extends AppCompatActivity {
    RecyclerView recyclerView;
    Barber barber;
    int images[]={R.drawable.men,R.drawable.beard,R.drawable.women,R.drawable.hairdryer,R.drawable.color_skin,R.drawable.hair_straightener};
    ImageView backbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_selection_page);
        barber=(Barber)getIntent().getExtras().get("currentBarber");
        backbutton = findViewById(R.id.back_button7);
        recyclerView =findViewById(R.id.recyclerView);
        setBackToMenu();
        setTheRecyclerView();
    }
    public void setBackToMenu() {
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public void setTheRecyclerView(){
        ArrayList<Service> services = new ArrayList<>(barber.getService());
        ServiceSelectionAdapter serviceSelectionAdapter =new ServiceSelectionAdapter(this,services,images,barber);
        recyclerView.setAdapter(serviceSelectionAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
