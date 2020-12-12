package com.example.barberapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.barberapp.Model.Barber;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class BarberLoginPage extends AppCompatActivity {
    public String nameOfCollection = "barber";

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    public static Barber currentBarber=new Barber();
    ImageView backbutton;
    Button login_button;
    EditText phone,barberid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barber_login_page);
        backbutton = findViewById(R.id.back_button10);
        login_button = findViewById(R.id.login_baber_button);
        phone =findViewById(R.id.enter_barber_phone);
        barberid=findViewById(R.id.enter_barber_id);
        BackToMenu();
        LoadFromFireBase();
    }

    private void LoadFromFireBase() {
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.collection(nameOfCollection).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        boolean isExist=false;
                        int i;
                        for ( i = 0; i < task.getResult().getDocuments().size(); i++) {
                            if(task.getResult().getDocuments().get(i).get("phone").equals(phone.getText().toString())&&task.getResult().getDocuments().get(i).get("barberId").equals(barberid.getText().toString()))
                            {
                                currentBarber=task.getResult().getDocuments().get(i).toObject(Barber.class);
                                isExist=true;
                                break;

                            }
                        }
                        if(isExist)
                        {
                            goToMonthlyD();
                            Toast.makeText(BarberLoginPage.this, "שלום "+task.getResult().getDocuments().get(i).get("name"), Toast.LENGTH_LONG).show();
                        }else
                            Toast.makeText(BarberLoginPage.this, "ספר לא קיים", Toast.LENGTH_SHORT).show();
                    }

                });
            }
        });
    }
    public void goToMonthlyD() {
        Intent intent = new Intent(BarberLoginPage.this, MonthlyDiary.class);
        startActivity(intent);

    }
    public void BackToMenu(){
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}