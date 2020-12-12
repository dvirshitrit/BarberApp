package com.example.barberapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class ContactUsPage extends AppCompatActivity {
    public ImageView backbutton;
    private EditText mEditTextSubject;
    private EditText mEditTextMessage;
    public Button buttonSend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us_page);
        backbutton = findViewById(R.id.back_button6);
        mEditTextSubject = findViewById(R.id.enter_name_contact);
        mEditTextMessage = findViewById(R.id.enter_phone_contact);
        buttonSend = findViewById(R.id.mail_intent);
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();
            }
        });
        setBackToMenu();
    }
    private void sendMail() {
        String subject = mEditTextSubject.getText().toString();
        String message = mEditTextMessage.getText().toString();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL,new String[]{"haybuadana@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        startActivity(Intent.createChooser(intent, "Choose an email client"));
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
