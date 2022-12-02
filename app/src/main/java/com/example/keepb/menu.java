package com.example.keepb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class menu extends AppCompatActivity {

    private Button progressbtn, dietbtn, notesbtn, emailbtn,CallBtn,WebBtn,faceBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
//PROGRESS BUTTON
        //koumpi Progress
        progressbtn = (Button) findViewById(R.id.progressbtn);
        progressbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openpageprogress();

            }
        });

        //DIET BUTTON
        //koumpi diet
        dietbtn = (Button) findViewById(R.id.tirisibtn);
        dietbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openpagetirisi();

            }
        });

        //NOTES BUTTON
        //koumpi NOTES
        notesbtn = (Button) findViewById(R.id.prefsbtn);
        notesbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openpageprefs();

            }
        });

        //email BUTTON
        //koumpi email
        emailbtn = (Button) findViewById(R.id.emailbtn);
        emailbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openpageemail();

            }
        });

        //koympi gia klisi

        CallBtn = findViewById(R.id.CallBtn);
        CallBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialContactPhone("+306943251732");


            }
        });

        ////////

      /*  ///////////koumpi gia wev
        WebBtn.findViewById(R.id.webBtn);


        /////////koumpi face
        faceBtn.findViewById(R.id.faceBtn);*/

    }

    //me8odos gia na paei sti selida Progress
    public void openpageprogress() {
        Intent intent = new Intent(this,Progress.class);
        startActivity(intent);
    }

    //me8odos gia na paei sti selida diet
    public void openpagetirisi() {
        Intent intent = new Intent(this,Tirisi.class);
        startActivity(intent);
    }

    //me8odos gia na paei sti selida notes
    public void openpageprefs() {
        Intent intent = new Intent(this, prefs.class);
        startActivity(intent);
    }



    //me8odos gia na steilei mail
    public void openpageemail() {
        Intent intent = new Intent(this, Message.class);
        startActivity(intent);
    }
    /////////////me8odos gia klisi
    public void dialContactPhone(final String phoneNumber) {
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)));
    }
    //me8odos gia na anoiksei to web
    public void loadWebPage(View v) {
        Intent intent = new Intent(this, web.class);
        startActivity(intent);

    }
    //me8odos gia na anoiksei to facebook

    public void loadfacePage(View v) {
        Intent intent = new Intent(this, facebook.class);
        startActivity(intent);

    }
}

