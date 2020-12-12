package com.example.barberapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.barberapp.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class ExistPage extends AppCompatActivity {
    public String nameOfCollection = "user";
    public static User user;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ImageView backButton;
    TextView forgetPassword;
    Button loginButton;
    EditText phone,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exist_page);
        backButton = findViewById(R.id.back_button2);
        forgetPassword = findViewById(R.id.forgetpassword);
        loginButton = findViewById(R.id.login);
        phone =findViewById(R.id.enter_phone);
        password=findViewById(R.id.enter_pass);
        BackToMenu();
        ForgetPassword();
        LoadFromFireBase();
    }
    private void LoadFromFireBase() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.collection(nameOfCollection).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        boolean isExist=false;
                        for (int i = 0; i < task.getResult().getDocuments().size(); i++) {
                            if(task.getResult().getDocuments().get(i).get("phone").equals(phone.getText().toString())&&task.getResult().getDocuments().get(i).get("password").equals(password.getText().toString()))
                            {
                                user=task.getResult().getDocuments().get(i).toObject(User.class);
                                isExist=true;
                                break;
                            }
                        }
                        if(isExist)
                        {

                            goToBarberPage();
                            Toast.makeText(ExistPage.this, "ברוך שובך "+user.getName(), Toast.LENGTH_LONG).show();
                        }else
                            Toast.makeText(ExistPage.this, "לקוח לא קיים", Toast.LENGTH_LONG).show();
                    }

                });
            }
        });
    }
    public void goToBarberPage() {
        Intent intent = new Intent(ExistPage.this, BarberChoicePage.class);

        startActivity(intent);

    }
    public void BackToMenu(){
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public void ForgetPassword() {
        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExistPage.this, ForgetPasswordPage.class);
                startActivity(intent);
            }
        });
    }




}
