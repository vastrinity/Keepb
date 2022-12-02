package com.example.keepb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class bravosou extends AppCompatActivity {
    Animation homebravoleme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bravosou);


        /////////animation

        ImageView bravoanime =(ImageView)findViewById(R.id.imageBravo);
        bravoanime.setImageResource(R.drawable.bravoanime);
        AnimationDrawable welcomeanime = (AnimationDrawable)bravoanime.getDrawable();

        welcomeanime.start();


        Button homebravo =(Button) findViewById(R.id.HomeBravo);
        homebravoleme = AnimationUtils.loadAnimation(this,R.anim.homebravobtn);
        homebravo.setAnimation(homebravoleme);


        //////sound
        final MediaPlayer ops    = MediaPlayer.create(this,R.raw.bravoraw);
        ops.start();





        homebravo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                home();

            }
        });




    }
    private void home(){
        Intent intent = new Intent(this, Tirisi.class);
        startActivity(intent);


    }
}
