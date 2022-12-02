package com.example.keepb;

import androidx.annotation.Dimension;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Point;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Size;
import android.view.Display;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import static android.content.res.Configuration.SCREENLAYOUT_SIZE_NORMAL;
import static android.content.res.Configuration.SCREENLAYOUT_SIZE_SMALL;

public class MainActivity extends AppCompatActivity {
    Animation welcomeleme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int screenSize = getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK;
        Button letsstart =(Button) findViewById(R.id.Welcomebutton);
        ImageView welcome =(ImageView)findViewById(R.id.imageView3);




           if (screenSize==SCREENLAYOUT_SIZE_SMALL){
                openpage();
                }




        else {
               welcome.setImageResource(R.drawable.anime);
               AnimationDrawable welcomeanime = (AnimationDrawable) welcome.getDrawable();

               welcomeanime.start();


               welcomeleme = AnimationUtils.loadAnimation(this, R.anim.letsart);
               letsstart.setAnimation(welcomeleme);

               final MediaPlayer opening = MediaPlayer.create(this, R.raw.electricity);
               opening.start();

               //koumpi enarksis
               letsstart = (Button) findViewById(R.id.Welcomebutton);
               letsstart.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       openpage();
                   }
               });

           }

    }



    //me8odos gia na paei sto menu
    public void openpage() {
        Intent intent = new Intent(this, menu.class);
        startActivity(intent);
    }
}
