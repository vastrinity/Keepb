package com.example.keepb;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.keepb.RecyclerViewClasses.DatabaseHelper;
import com.example.keepb.RecyclerViewClasses.ModelClass;

import java.util.Calendar;


public class NeaMetrisi extends AppCompatActivity {
    DatabaseHelper mydb2;
    EditText kila,lipos,ygra,mys,meta_age,fat_level;
    Button save,datebtn;
    TextView date;
    Calendar c;
    DatePickerDialog dpd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nea_metrisi);
        mydb2 = new DatabaseHelper(this);



        date = (TextView) findViewById(R.id.date);
        datebtn = (Button) findViewById(R.id.datebtn);
        datebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c = Calendar.getInstance();
                int dday = c.get(Calendar.DAY_OF_MONTH);
                int dmonths = c.get(Calendar.MONTH);
                int dyears = c.get(Calendar.YEAR);

                dpd = new DatePickerDialog(NeaMetrisi.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        date.setText(dayOfMonth + "/" + (month + 1) + "/" + year);

                    }
                }, dyears , dmonths,dday);
                dpd.show();

            }
        });




        kila= (EditText)findViewById(R.id.kila);
        lipos= (EditText)findViewById(R.id.lipos);
        ygra= (EditText)findViewById(R.id.ygra);
        mys= (EditText)findViewById(R.id.mys);
        meta_age= (EditText)findViewById(R.id.met_age);
        fat_level= (EditText)findViewById(R.id.level_fat);
        save=(Button)findViewById(R.id.save);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();

            }
        });


    }
    public void saveData(){

        DatabaseHelper lastmet = new DatabaseHelper(this);
        float kilaT= lastmet.getKilaLast();




        String mdate=date.getText().toString().trim();
        String mkila= kila.getText().toString().trim();
        String mlipos= lipos.getText().toString().trim();
        String mygra=ygra.getText().toString().trim();
        String mmys= mys.getText().toString().trim();
        String mmeta_age=meta_age.getText().toString().trim();
        String mfat_level=fat_level.getText().toString().trim();
        float kilaA= Float.parseFloat(mkila);

        mydb2 = new DatabaseHelper(this);

        if(kilaT>kilaA){

            Toast.makeText(getApplicationContext(), "Μπραβο!!",Toast.LENGTH_LONG).show();


        }





        ModelClass metrisi = new ModelClass(mdate,mkila,mlipos,mygra,mmys,mmeta_age,mfat_level);
        boolean isInserted=mydb2.insertData(metrisi);

        if (isInserted=true){


            if(kilaT>kilaA){

                Toast.makeText(getApplicationContext(), "Μπραβο!!",Toast.LENGTH_LONG).show();


            }
            else{Toast.makeText(NeaMetrisi.this,"Μετρηση καταχωρηθηκε",Toast.LENGTH_LONG).show();}

        }
        else
            Toast.makeText(NeaMetrisi.this,"Μετρηση δεν καταχωρηθηκε",Toast.LENGTH_LONG).show();


        //finally redirect back home
        // NOTE you can implement an  goBackPaliesMetriseis();
        goBackPaliesMetriseis();

    }

    private void goBackPaliesMetriseis(){
        Intent intent = new Intent(this, EisagwgiMetrisewn.class);
        startActivity(intent);

    }






}



