package com.example.keepb;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.keepb.Imera.ImeraClass;
import com.example.keepb.Imera.ImeraHelper;


public class imera extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, RatingBar.OnRatingBarChangeListener {
    Switch ibtv,ibwv,idtv,idwv,imtv,imwv,iatv,iawv,iltv,ilwv;
    EditText imera;
    Button saveswitch;
    ImeraHelper mydbimera;
    CheckBox ialcoolv,igymv,icocav;
    RatingBar iypnosv,iwaterv;
    EditText iepipleonv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imera);




        mydbimera =new ImeraHelper(this);


        ibtv= findViewById(R.id.breakTime);
        ibwv= findViewById(R.id.breakwhat);
        idtv= findViewById(R.id.decTime);
        idwv= findViewById(R.id.decwhat);
        imtv= findViewById(R.id.mesiTime);
        imwv= findViewById(R.id.mesiwhat);
        iatv= findViewById(R.id.apogTime);
        iawv= findViewById(R.id.apogwhat);
        iltv= findViewById(R.id.launchTime);
        ilwv= findViewById(R.id.launchwhat);
        imera=findViewById(R.id.NoImeras);
        saveswitch=findViewById(R.id.savemera);


        ibtv.setOnCheckedChangeListener(this);
        ibwv.setOnCheckedChangeListener(this);
        idtv.setOnCheckedChangeListener(this);
        ibwv.setOnCheckedChangeListener(this);
        imtv.setOnCheckedChangeListener(this);
        imwv.setOnCheckedChangeListener(this);
        iatv.setOnCheckedChangeListener(this);
        iawv.setOnCheckedChangeListener(this);
        iltv.setOnCheckedChangeListener(this);
        ilwv.setOnCheckedChangeListener(this);

        iwaterv= findViewById(R.id.ratingBarwater);
        ialcoolv= findViewById(R.id.alcoolbtn);
        igymv= findViewById(R.id.gymbtn);
        icocav=findViewById(R.id.cocabtn);
        iypnosv= findViewById(R.id.ratingBarYpnos);
        iepipleonv= findViewById(R.id.epipleon);


        iwaterv.setOnRatingBarChangeListener(this);
        final MediaPlayer failgeyma   = MediaPlayer.create(this,R.raw.failgeyma);


        ialcoolv.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                failgeyma.start();

            }
        });



        igymv.setOnCheckedChangeListener(this);
        icocav.setOnCheckedChangeListener(this);
        iypnosv.setOnRatingBarChangeListener(this);













        saveswitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveswitches();
            }
        });

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }
    public void saveswitches(){
        String  sbtv =String.valueOf(ibtv.isChecked()?1:0);
        String sbwv =String.valueOf(ibwv.isChecked()?1:0);
        String sdtv =String.valueOf(idtv.isChecked()?1:0);
        String sdwv =String.valueOf(idwv.isChecked()?1:0);
        String smtv =String.valueOf(imtv.isChecked()?1:0);
        String smwv =String.valueOf(imwv.isChecked()?1:0);
        String satv =String.valueOf(iatv.isChecked()?1:0);
        String sawv =String.valueOf(iawv.isChecked()?1:0);
        String sltv =String.valueOf(iltv.isChecked()?1:0);
        String slwv =String.valueOf(ilwv.isChecked()?1:0);
        String swater =String.valueOf(iwaterv.getRating());
        String salcool =String.valueOf(ialcoolv.isChecked()?1:0);
        String sgym =String.valueOf(igymv.isChecked()?1:0);
        String scoca =String.valueOf(icocav.isChecked()?1:0);
        String sypnos=String.valueOf(iypnosv.getRating());
        String sepipleon= iepipleonv.getText().toString();

        String  simera= imera.getText().toString();



        int sum = (ibtv.isChecked()?1:0)+(ibwv.isChecked()?1:0)+(idtv.isChecked()?1:0)+(idwv.isChecked()?1:0)+(imtv.isChecked()?1:0)+(imwv.isChecked()?1:0)+
                (iatv.isChecked()?1:0)+(iawv.isChecked()?1:0)+(iltv.isChecked()?1:0)+(ilwv.isChecked()?1:0)+(icocav.isChecked()?1:0)+(ialcoolv.isChecked()?1:0);
        int sumo =(ibwv.isChecked()?1:0)+(idwv.isChecked()?1:0)+(imwv.isChecked()?1:0)+
                +(iawv.isChecked()?1:0)+(ilwv.isChecked()?1:0)+(icocav.isChecked()?1:0)+(ialcoolv.isChecked()?1:0);


        if(sum==0 && iwaterv.getRating()>=2 ){
            Toast.makeText(this,"Mπραβο",Toast.LENGTH_LONG).show();

            openBravo();
        }
        else{

            final MediaPlayer epicfail   = MediaPlayer.create(this,R.raw.fail);
            epicfail.start();
            Intent intent = new Intent(this, Tirisi.class);
            startActivity(intent);


        }

        if (sumo!=0){
            final MediaPlayer suzi1   = MediaPlayer.create(this,R.raw.failgeyma);
            suzi1.start();
            Intent intent = new Intent(this, Tirisi.class);
            startActivity(intent);

        }








        mydbimera = new ImeraHelper(this);

        ImeraClass imera =new ImeraClass(simera,sbtv,sbwv,sdtv,sdwv,smtv,smwv,satv,sawv,sltv,slwv,swater,salcool,scoca,sgym,sypnos,sepipleon);

        boolean insertImera =mydbimera.insertImera(imera);

        if (insertImera=true)
            Toast.makeText(this,"Ημερα καταχωρηθηκε",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this,"Ημερα δεν καταχωρηθηκε",Toast.LENGTH_LONG).show();








    }

    private void openBravo() {

        Intent intent = new Intent(this, bravosou.class);
        startActivity(intent);
    }



    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

    }
}
