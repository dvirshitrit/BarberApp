package com.example.barberapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.barberapp.Model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;


public class RegisterPage extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    EditText name,phone,password,email;
    ImageView backbutton;
    Button register;
    public String nameOfCollection = "user";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        backbutton = findViewById(R.id.back_button);
        name = findViewById(R.id.enter_name);
        phone = findViewById(R.id.enter_phone);
        password = findViewById(R.id.enter_email);
        email = findViewById(R.id.enter_password2);
        register=findViewById(R.id.send_data_to_firebase);
        saveUserInFireBase();
        BackToMenu();
    }
    //חזרה עמוד אחורה
    private void BackToMenu() {
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                finish();
            }
        });
    }
    //יצירת לקוח חדש ושמירה בפיירבייס
    public void saveUserInFireBase(){
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User(name.getText().toString(), phone.getText().toString(), password.getText().toString(),email.getText().toString());
                db.collection(nameOfCollection).document(phone.getText().toString()).set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(RegisterPage.this, "המידע נקלט בהצלחה! להתחברות הכנס ללקוח קיים.", Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegisterPage.this, "שגיאה מערכתית", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}
