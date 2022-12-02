package com.example.keepb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


import com.example.keepb.Imera.ImeraHelper;
import com.example.keepb.RecyclerViewClasses.DatabaseHelper;


import static com.example.keepb.Imera.ImeraHelper.ALCOOL;
import static com.example.keepb.Imera.ImeraHelper.ANAPSIKTIKO;
import static com.example.keepb.Imera.ImeraHelper.APOGTIME;
import static com.example.keepb.Imera.ImeraHelper.APOGWHAT;
import static com.example.keepb.Imera.ImeraHelper.BREAKTIME;
import static com.example.keepb.Imera.ImeraHelper.BREAKWHAT;
import static com.example.keepb.Imera.ImeraHelper.DECTIME;
import static com.example.keepb.Imera.ImeraHelper.DECWHAT;
import static com.example.keepb.Imera.ImeraHelper.LAUNCHTIME;
import static com.example.keepb.Imera.ImeraHelper.LAUNCHWHAT;
import static com.example.keepb.Imera.ImeraHelper.MESITIME;
import static com.example.keepb.Imera.ImeraHelper.MESIWHAT;
import static com.example.keepb.Imera.ImeraHelper.WATER;

public class Progress extends AppCompatActivity {
    private Button eisagwgi_metrisewnbtn,ekseliksi_swmatosbtn,liposbtn;
    SharedPreferences sharedPreferences;

    TextView   progDay;
    EditText stoxosView;
    ProgressBar progBarimeres;
    ProgressBar progFinal;
    TextView progfinal;
    Button homeProg;
    TextView katStoxou;

    ImeraHelper imeraDb;
    private static final String SHARED_PREF_STOXOS = "mysharedpref";
    private static final String KEY_NAME ="keyname";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        progressImera();



        //ΕΙΣΑΓΩΓΗ ΜΕΤΡΙΣΕΩΝ BUTTON

        eisagwgi_metrisewnbtn = (Button) findViewById(R.id.eisagwgimetrisewn);
        eisagwgi_metrisewnbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openpageeisagvgiMetrisewn();
            }
        });



        sharedPreferences = getSharedPreferences(SHARED_PREF_STOXOS, Context.MODE_PRIVATE);




        stoxosView=findViewById(R.id.stoxosView);
        katStoxou =findViewById(R.id.kat_stoxou);




        //save stoxo button
        liposbtn = (Button) findViewById(R.id.save_liposbtn);
        liposbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePrefer();
                displayPrefer();
                openpageprogress();



            }
        });



        progressFinal();


        //home
        homeProg =findViewById(R.id.Homeprog);
        homeProg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Homescreen();
            };
        });


        displayPrefer();





    }

    private void openpageprogress() {
        Intent intent = new Intent(this, Progress.class);
        startActivity(intent);
    }

    private void Homescreen() {
        Intent intent = new Intent(this,menu.class);
        startActivity(intent);

    }

    public void savePrefer(){
        String stoxos1 = stoxosView.getText().toString();

        if (stoxos1.isEmpty()) {

            stoxosView.setError("πειτε το στοχο σας");
            stoxosView.requestFocus();
            return;
        }

        SharedPreferences sp = getSharedPreferences(SHARED_PREF_STOXOS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        editor.putString(KEY_NAME, stoxos1);

        editor.apply();

        editor.commit();


        Toast.makeText(getApplicationContext(), "Kαλή Αρχή!",Toast.LENGTH_SHORT).show();

    }


    private void displayPrefer() {
        stoxosView =(EditText) findViewById(R.id.stoxosView);
        SharedPreferences sp = getSharedPreferences(SHARED_PREF_STOXOS, MODE_PRIVATE);
        String stoxos1 = stoxosView.getText().toString();
        if (stoxos1.isEmpty()){
            katStoxou.setText("καταχωριση στοχου");
        }
        if(sharedPreferences.contains(KEY_NAME)) {
            katStoxou.setText("Ο στοχος μου: "+(sharedPreferences.getString(KEY_NAME, "")+"Kg"));

        }


    }


    public void openpageeisagvgiMetrisewn() {
        Intent intent = new Intent(this,EisagwgiMetrisewn.class);
        startActivity(intent);
    }


   /* public void openpageEkseliksiSwmatos() {
        Intent intent = new Intent(this,EkseliksiSwmatos.class);
        startActivity(intent);
    }*/

    public void progressImera(){

        ImeraHelper avg =new ImeraHelper (this);

        float qbt = avg.get_Avg(BREAKTIME);
        float qbw = avg.get_Avg(BREAKWHAT);
        float qdt = avg.get_Avg(DECTIME);
        float qdw = avg.get_Avg(DECWHAT);
        float qmt = avg.get_Avg(MESITIME);
        float qmw = avg.get_Avg(MESIWHAT);
        float qat = avg.get_Avg(APOGTIME);
        float qaw = avg.get_Avg(APOGWHAT);
        float qlt = avg.get_Avg(LAUNCHTIME);
        float qlw = avg.get_Avg(LAUNCHWHAT);
        float qwater = avg.get_Avg(WATER);
        float qalcool = avg.get_Avg(ALCOOL);
        float qcoca =avg.get_Avg(ANAPSIKTIKO);
        int days =avg.countDays();





        int prog = (int) (((10-(qbt+qbw+qdt+qdw+qmt+qmw+qat+qaw+qlt+qlw))*0.09 +qwater*0.033 -qalcool*0.001 -qcoca*0.05)*100);
        progDay=findViewById(R.id.progDay);


        progBarimeres = findViewById(R.id.next_progress);
        progBarimeres.setMax(100);
        progBarimeres.setEnabled(false);

        if(days!=0){
            progBarimeres.setProgress(prog);
            progDay.setText("Ειμαι συνεπης στη διατροφη κατα:"+String.valueOf(prog)+"%");}
        else{
            progBarimeres.setProgress(0);
            progDay.setText("Ξεκινήστε να καταχωρείτε τις μερες σας");
            progBarimeres.setEnabled(false);}





    }
    public void progressFinal(){


        DatabaseHelper kilaMenei =new DatabaseHelper(this);
        String stoxos_final;

        float kilaT = kilaMenei.getKilaLast();
        float kilaA=kilaMenei.getKilaFirst();
        progFinal = findViewById(R.id.final_progress);
        progFinal.setMax(100);
        progFinal.setEnabled(false);

        progfinal = findViewById(R.id.progfinal);
        SharedPreferences sp = getSharedPreferences(SHARED_PREF_STOXOS, MODE_PRIVATE);
        if(sharedPreferences.contains(KEY_NAME)) {
            stoxos_final = sharedPreferences.getString(KEY_NAME, "");
            if (kilaA == 0) {
                progfinal.setText("περιμένοντας τη πρώτη σας μέτρηση");
            }


            if (kilaT != Integer.valueOf(stoxos_final)) {


                int menei = (int) (((-(kilaT - Integer.valueOf(stoxos_final)) + (kilaA - Integer.valueOf(stoxos_final))) / (kilaA - Integer.valueOf(stoxos_final))) * 100);

                progFinal.setProgress(menei);
                progfinal.setText("Ειμαι κατα:" + String.valueOf(menei) + "% κοντα στο στοχο");
            } else {
                openBravo();

            }
        }


        else{
                progFinal.setProgress(0);
                progfinal.setText("Καταχωρίστε το στοχο σας!");

                progFinal.setEnabled(false);

            }





        }
    private void openBravo() {

        Intent intent = new Intent(this, bravosou.class);
        startActivity(intent);
    }








    }








