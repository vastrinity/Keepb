package com.example.keepb;


import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;


import com.example.keepb.Imera.ImeraClass;
import com.example.keepb.Imera.ImeraHelper;


public class UpImera extends AppCompatActivity {

    Switch ibtv,ibwv,idtv,idwv,imtv,imwv,iatv,iawv,iltv,ilwv;
    EditText imera;
    Button updateswitch;
    ImeraHelper mydbimera;
    CheckBox ialcoolv,igymv,icoca;
    RatingBar iypnosv,iwaterv;
    EditText iepipleonv;

    private ImeraHelper dbHelper;
    private long receivedImeraId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_imera);



        ibtv= findViewById(R.id.breakTimeu);
        ibwv= findViewById(R.id.breakwhatu);
        idtv= findViewById(R.id.decTimeu);
        idwv= findViewById(R.id.decwhatu);
        imtv= findViewById(R.id.mesiTimeu);
        imwv= findViewById(R.id.mesiwhatu);
        iatv= findViewById(R.id.apogTimeu);
        iawv= findViewById(R.id.apogwhatu);
        iltv= findViewById(R.id.launchTimeu);
        ilwv= findViewById(R.id.launchwhatu);
        imera=findViewById(R.id.NoImerasu);


        iwaterv= findViewById(R.id.ratingBarwateru);
        iypnosv= findViewById(R.id.ratingBarYpnosu);

        ialcoolv= findViewById(R.id.alcoolbtnu);
        igymv= findViewById(R.id.gymbtnu);
        icoca=findViewById(R.id.cocabtnu);

        iepipleonv= findViewById(R.id.epipleonu);
        updateswitch=findViewById(R.id.savemerau);

        dbHelper = new ImeraHelper(this);

        try {
            //get intent to get person id
            receivedImeraId= getIntent().getLongExtra("IDDAY", 1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /***populate user data before update***/
        //set field to this user data


        ImeraClass queriedImera = dbHelper.getImeraClass(receivedImeraId);


        if((queriedImera.getBreaktime()).equals("0"))
        {ibtv.setChecked(false);}
        else{ibtv.setChecked(true);}

        if((queriedImera.getBreakwhat()).equals("0"))
        {ibwv.setChecked(false);}
        else{ibwv.setChecked(true);}


        if( (queriedImera.getDectime().equals("0")))
        {idtv.setChecked(false);}
        else{idtv.setChecked(true);}

        if( (queriedImera.getDecwhat().equals("0"))){
            idwv.setChecked(false);
        }
        else{idwv.setChecked(true);}

        if( (queriedImera.getMesitime().equals("0"))){
            imtv.setChecked(false);
        }
        else{imtv.setChecked(true);}

        if( (queriedImera.getMesiwhat().equals("0"))){
            imwv.setChecked(false);
        }
        else{imwv.setChecked(true);}


        if( (queriedImera.getApotime().equals("0"))){
            iatv.setChecked(false);
        }
        else{iatv.setChecked(true);}

        if( (queriedImera.getApowhat().equals("0"))){
            iawv.setChecked(false);
        }
        else{iawv.setChecked(true);}

        if( (queriedImera.getLaunchtime().equals("0"))){
            iltv.setChecked(false);
        }
        else{iltv.setChecked(true);}

        if( (queriedImera.getLaunchwhat().equals("0"))){
            ilwv.setChecked(false);
        }
        else{ilwv.setChecked(true);}


        if( (queriedImera.getAlcool().equals("0"))){
            ialcoolv.setChecked(false);
        }
        else{ialcoolv.setChecked(true);}

        if( (queriedImera.getGym().equals("0"))){
            igymv.setChecked(false);
        }
        else{igymv.setChecked(true);}



        if( (queriedImera.getAnapsiktiko().equals("0"))){
            icoca.setChecked(false);
        }
        else{icoca.setChecked(true);}









        iwaterv.setRating(Float.parseFloat(queriedImera.getWater()));

        iypnosv.setRating(Float.parseFloat(queriedImera.getYpnos()));
        iepipleonv.setText(queriedImera.getEpileon());
        imera.setText(queriedImera.getImera());


        updateswitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //call the save person method
                updateImera();
            }
        });








    }




    private void updateImera(){
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
        String scoca =String.valueOf(icoca.isChecked()?1:0);
        String sypnos=String.valueOf(iypnosv.getRating());
        String sepipleon= iepipleonv.getText().toString();
        String simera= imera.getText().toString();




        int sum = (ibtv.isChecked()?1:0)+(ibwv.isChecked()?1:0)+(idtv.isChecked()?1:0)+(idwv.isChecked()?1:0)+(imtv.isChecked()?1:0)+(imwv.isChecked()?1:0)+
                (iatv.isChecked()?1:0)+(iawv.isChecked()?1:0)+(iltv.isChecked()?1:0)+(ilwv.isChecked()?1:0)+(icoca.isChecked()?1:0)+(ialcoolv.isChecked()?1:0);

        int sumo =(ibwv.isChecked()?1:0)+(idwv.isChecked()?1:0)+(imwv.isChecked()?1:0)+
                +(iawv.isChecked()?1:0)+(ilwv.isChecked()?1:0)+(icoca.isChecked()?1:0)+(ialcoolv.isChecked()?1:0);


        if(sum==0 && iwaterv.getRating()>=2 ){

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




        //create updated person
        ImeraClass updatedImera = new  ImeraClass(simera,sbtv,sbwv,sdtv,sdwv,smtv,smwv,satv,sawv,sltv,slwv,swater,salcool,scoca,sgym,sypnos,sepipleon);


        //call dbhelper update
        dbHelper.updateDayRecord(receivedImeraId,this,updatedImera);
        //finally redirect back home
        // NOTE you can implement an sqlite callback then redirect on success delete


    }

   private void openBravo() {

       Intent intent = new Intent(this, bravosou.class);
       startActivity(intent);
   }

    private void goBackHome(){
        Intent intent = new Intent(this, Tirisi.class);
        startActivity(intent);
    }

}
