package com.example.barberapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
public class ForgetPasswordPage extends AppCompatActivity {
    public String nameOfCollection = "user";
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ImageView backbutton ;
    private Button resetPasswordEmailButton;
    private EditText email,phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        backbutton = findViewById(R.id.back_button3);
        resetPasswordEmailButton = findViewById(R.id.reset_password_button);
        phone = findViewById(R.id.reset_email_input);
        email = findViewById(R.id.reset_phone_input);
        BackToMenu();
        LoadFromFireBase();
    }
    public void BackToMenu(){
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void LoadFromFireBase() {
        resetPasswordEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.collection(nameOfCollection).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        boolean isExist=false;
                        for ( int i = 0; i < task.getResult().getDocuments().size(); i++) {
                            if(task.getResult().getDocuments().get(i).get("email").equals(email.getText().toString())&&task.getResult().getDocuments().get(i).get("phone").equals(phone.getText().toString()))
                            {
                                isExist=true;
                                break;
                            }
                        }
                        if(isExist)
                        {
                            goToProfilePage();
                            Toast.makeText(ForgetPasswordPage.this, "אתה מועבר לעריכת פרופיל", Toast.LENGTH_LONG).show();
                        }else
                            Toast.makeText(ForgetPasswordPage.this, "אולי לא הזנת נכון את הפרטים..נסה שוב", Toast.LENGTH_LONG).show();
                    }

                });
            }
        });
    }
    public void goToProfilePage() {
        Intent intent = new Intent(ForgetPasswordPage.this, ProfilePage.class);
        startActivity(intent);
    }
}
