package com.example.barberapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button register,exist,barber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        register = findViewById(R.id.b_new_c);
        exist = findViewById(R.id.b_exist_c);
        barber = findViewById(R.id.barber_button);
        moveToExistUserLogin();
        moveToCreateUser();
        moveToBarberLogin();
    }

    private void moveToBarberLogin() {
        barber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BarberLoginPage.class);
                startActivity(intent);
            }
        });
    }
    private void moveToCreateUser(){
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterPage.class);
                startActivity(intent);
            }
        });
    }
    private void moveToExistUserLogin(){
        exist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ExistPage.class);
                startActivity(intent);
            }
        });
    }
    private void setTheFacebookIntent() {

        Intent facebook = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/1713658120"));
        try {
            startActivity(facebook);


        } catch (Exception e) {
            showError(e);
        }


    }
    private void setTheInstegramIntent() {

        Intent instegram = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/hay_bohadana/"));
        instegram.setPackage("com.instagram.android");

        try {
            startActivity(instegram);
        } catch (Exception e) {
            showError(e);
        }
    }
    private void setTheMessageIntent() {


        Intent textMessage = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:0528088486"));
        textMessage.putExtra("sms_body", "תשאירו הודעה ונחזור אליכם בהקדם. צוות BARBERAPP");
        try {
            startActivity(textMessage);
        } catch (Exception e) {
            showError(e);
        }


    }
    private void setTheMapsIntent() {
        Intent map = new Intent(android.content.Intent.ACTION_VIEW);
        map.setData(Uri.parse("geo:31.534739, 34.587945"));
        startActivity(map);
        try {
            startActivity(map);
        } catch (Exception e) {
            showError(e);
        }
    }
    private void showError(Exception e) {
        Toast.makeText(this, "אין אפשרות להפעיל פעילות זו במכשיר זה.", Toast.LENGTH_LONG).show();
        e.printStackTrace();
    }
    public void implicitIntentLiseners(View view) {
        switch (view.getId()) {
            case R.id.facebook:
                setTheFacebookIntent();
                break;
            case R.id.instegram:
                setTheInstegramIntent();
                break;
            case R.id.whatsapp:
                setTheMessageIntent();
                break;
            case R.id.maps:
                setTheMapsIntent();
                break;
        }
    }
}


