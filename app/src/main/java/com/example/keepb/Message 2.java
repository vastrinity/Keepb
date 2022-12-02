package com.example.keepb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import java.net.URLEncoder;

public class Message extends AppCompatActivity {
    EditText editTextMessage;
    Button send;
    Button homemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        editTextMessage=(EditText)findViewById(R.id.message);
        send=(Button)findViewById(R.id.sendBtn);

        send.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                String message = editTextMessage.getText().toString();
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{"ilia.alexiadi@gmail.com"});


                email.putExtra(Intent.EXTRA_TEXT, message);

                //need this to prompts email client only
                email.setType("text/plaintext");

                startActivity(Intent.createChooser(email, "Choose an Email client :"));











            };
        });
        homemail=findViewById(R.id.Homemail);
        homemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Homescreen();

            }
        });
    }
    private void Homescreen() {
        Intent intent = new Intent(this,menu.class);
        startActivity(intent);

    }
}
