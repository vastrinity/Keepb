package com.example.keepb;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.keepb.RecyclerViewClasses.DatabaseHelper;
import com.example.keepb.RecyclerViewClasses.ModelClass;

import java.util.Calendar;


public class UpMetrisi extends AppCompatActivity {

    EditText kila,lipos,ygra,mys,meta_age,fat_level;
    TextView date;
    private Button mUpdateBtn,datebtnu;
    Calendar c;
    DatePickerDialog dpd;

    private DatabaseHelper dbHelper;
    private long receivedMetrisiId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_metrisi);

        //init
        date = (TextView) findViewById(R.id.dateu);
        datebtnu = (Button) findViewById(R.id.datebtnu);
        datebtnu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c = Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);

                dpd = new DatePickerDialog(UpMetrisi.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        date.setText(dayOfMonth + "/" + (month + 1) + "/" + year);

                    }
                }, day, month, year);
                dpd.show();

            }
        });

        kila= (EditText)findViewById(R.id.kilau);
        lipos= (EditText)findViewById(R.id.liposu);
        ygra= (EditText)findViewById(R.id.ygrau);
        mys= (EditText)findViewById(R.id.mysu);
        meta_age= (EditText)findViewById(R.id.met_ageu);
        fat_level= (EditText)findViewById(R.id.level_fatu);
        mUpdateBtn = (Button)findViewById(R.id.saveu);

        dbHelper = new DatabaseHelper(this);

        try {
            //get intent to get person id
            receivedMetrisiId= getIntent().getLongExtra("ID", 1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /***populate user data before update***/
        ModelClass queriedMetrisi = dbHelper.getModelClass(receivedMetrisiId);
        //set field to this user data
        kila.setText(queriedMetrisi.getKila());
        lipos.setText(queriedMetrisi.getLipos());
        ygra.setText(queriedMetrisi.getYgra());
        meta_age.setText(queriedMetrisi.getMeta_age());
        fat_level.setText(queriedMetrisi.getFat_level());
        mys.setText(queriedMetrisi.getMys());
        date.setText(queriedMetrisi.getDate());



        //listen to add button click to update
        mUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //call the save person method
                updatePerson();
            }
        });





    }

    private void updatePerson(){

        String mdate=date.getText().toString().trim();
        String mkila= kila.getText().toString().trim();
        String mlipos= lipos.getText().toString().trim();
        String mygra=ygra.getText().toString().trim();
        String mmys= mys.getText().toString().trim();
        String mmeta_age=meta_age.getText().toString().trim();
        String mfat_level=fat_level.getText().toString().trim();




        if(mdate.isEmpty())
            Toast.makeText(this, " Συμπληρωστε το πεδιο της ημερομηνιας "+" πατώντας το κουμπι ΗΜ/ΜΗ/ΕΤΟΣ", Toast.LENGTH_SHORT).show();

        if(mkila.isEmpty()){
            Toast.makeText(this, " Συμπληρωστε το πεδιο <<κιλα>> ", Toast.LENGTH_SHORT).show();
        }
        if(mlipos.isEmpty()){
            Toast.makeText(this, " Συμπληρωστε το πεδιο <<λιποσ>> ", Toast.LENGTH_SHORT).show();
        }
        if(mygra.isEmpty()){
            Toast.makeText(this, " Συμπληρωστε το πεδιο <<υγρα>> ", Toast.LENGTH_SHORT).show();
        }
        if(mmys.isEmpty()){
            Toast.makeText(this, " Συμπληρωστε το πεδιο <<μυς>> ", Toast.LENGTH_SHORT).show();
        }
        if(mmeta_age.isEmpty()){
            Toast.makeText(this, " Συμπληρωστε το πεδιο <<Μεταβολικη ηλικια>> ", Toast.LENGTH_SHORT).show();
        }
        if(mfat_level.isEmpty()){
            Toast.makeText(this, " Συμπληρωστε το πεδιο <<επιπεδο σπλαχνικου λιπους>> ", Toast.LENGTH_SHORT).show();
        }

        //create updated person
        ModelClass updatedPerson = new ModelClass(mdate,mkila,mlipos,mygra,mmys,mmeta_age,mfat_level);
        //call dbhelper update
        dbHelper.updateMetrisiRecord(receivedMetrisiId,this,updatedPerson);
        //finally redirect back home
        // NOTE you can implement an sqlite callback then redirect on success delete
        goBackHome();

    }

    private void goBackHome(){
        startActivity(new Intent(this, EisagwgiMetrisewn.class));
    }

}
