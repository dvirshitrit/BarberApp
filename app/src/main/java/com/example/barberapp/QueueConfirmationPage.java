package com.example.barberapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.barberapp.Model.TimeSlot;

import java.sql.Time;

public class QueueConfirmationPage extends AppCompatActivity {
    Button exitbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue_confirmation_page);
        TextView textView=findViewById(R.id.queue1);
        exitbutton = findViewById(R.id.exit_q);
        String timeSlot=getIntent().getExtras().getString("timeSlot2");
        textView.setText(timeSlot);
        BackToMenu();
    }
    public void BackToMenu() {
        exitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QueueConfirmationPage.this, MainActivity.class);
                TimeSlotPage.date=null;
                startActivity(intent);
            }
        });
    }
}