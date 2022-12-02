package com.example.keepb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class web extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);


        String url="https://www.diatrofisygeia.com/?fbclid=IwAR2Rac_ccbNR54vTxRDIHzSKZvvfDXSmWcdjPlYeH0ExGlQlPORoa140MNk";


        WebView web =(WebView) findViewById(R.id.webView);

        web.loadUrl(url);
    }
}
