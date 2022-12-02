package com.example.keepb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class enter extends AppCompatActivity {
    Button signInBtn,registerbtn,deleteAccountBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);


        signInBtn=findViewById(R.id.signinBtn);
        registerbtn=findViewById(R.id.registerBtn);
        deleteAccountBtn=findViewById(R.id.DeletAccountBtn);



        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignIn();
            }
        });


        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerPage();
            }
        });


        deleteAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletepage();

            }
        });


    }

    private void deletepage() {
        Intent intent = new Intent(this, deleteUser.class);
        startActivity(intent);

    }


    public void openSignIn(){
        Intent intent = new Intent(this, login.class);
        startActivity(intent);
    }

    public void registerPage(){
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

}
