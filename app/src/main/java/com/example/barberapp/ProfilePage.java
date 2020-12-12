package com.example.barberapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.barberapp.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class ProfilePage extends AppCompatActivity {
    public ImageView backbutton;
    public EditText name,phone,password;
    public Button updadeProfile;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        backbutton = findViewById(R.id.back_button5);
        name = findViewById(R.id.update_name);
        phone = findViewById(R.id.update_phone);
        password = findViewById(R.id.update_password);
        updadeProfile = findViewById(R.id.updade_profile);
        name.setText(ExistPage.user.name);
        phone.setText(ExistPage.user.phone);
        password.setText(ExistPage.user.password);
        setBackToMenu();
        updadeProfileUser();
    }
    private void updadeProfileUser() {
        updadeProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String document=ExistPage.user.getPhone();
                db.collection("user").document(document).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        String name_= name.getText().toString();
                        String password_= password.getText().toString();
                        String phone_= phone.getText().toString();
                        ExistPage.user.setPassword(password.getText().toString());
                        HashMap<String,Object> user=new HashMap<>();
                        user.put("user",ExistPage.user);
                        task.getResult().getReference().update("name",name_, "phone",phone_,"password",password_);
                        Toast.makeText(ProfilePage.this, "המידע עודכן במערכת", Toast.LENGTH_LONG).show();
                    }
                });
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
}