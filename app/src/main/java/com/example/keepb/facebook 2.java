package com.example.keepb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class facebook extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook);



        String url="https://www.facebook.com/ilia.alexiadismirniotaki";


        WebView web =(WebView) findViewById(R.id.facebookView);

        web.loadUrl(url);
    }
}
