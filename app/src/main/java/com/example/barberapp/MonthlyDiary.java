package com.example.barberapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barberapp.Recycler.MonthlyDiaryAdapter;

public class MonthlyDiary extends AppCompatActivity {
    ImageView backbutton ;
    RecyclerView recyclerMonthly;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly_diary);
        backbutton = findViewById(R.id.back_button8);
        recyclerMonthly =findViewById(R.id.recyclerBarberDairy);
        BackToMenu();
        setTheRecyclerView();
    }
    public void BackToMenu() {
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public void setTheRecyclerView(){
        MonthlyDiaryAdapter monthlyDiaryAdapter =new MonthlyDiaryAdapter(this);
        recyclerMonthly.setAdapter(monthlyDiaryAdapter);
        recyclerMonthly.setLayoutManager(new LinearLayoutManager(this));
    }

}
