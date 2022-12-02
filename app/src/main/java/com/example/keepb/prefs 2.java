package com.example.keepb;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class prefs extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener{

    private static final String BREAKALARM = "";
    private static final String DECALARM = "";
    private static final String MESIALARM = "";
    private static final String APOGALARM = "";
    private static final String LAUNCHALARM = "";
    private static final String WATER ="" ;
    TextView wrabreak, wradec, wramesi, wraapog, wralaunch, showprf;
    Button koubibreak, koubidec, koubimesi, koubiapog, koubilaunch, homerythmiseis, saverythmiseis, alarmbtn;
    Calendar cb, cd, cm, ca, cl;
    TimePickerDialog btp, dtp, mtp, atp, ltp;
    CheckBox neronotibtn;


    private int bhour, bminutes;
    public static final String SHARED_PREFS_GEYMATA = "sharedPrefsGeymata";
    public static final String WRAPRWINOU = "wraprwinou";
    public static final String WRADECAT = "wradecat";
    public static final String WRAMESI = "wramesime";
    public static final String WRADAPOGEM = "wraapogem";
    public static final String WRABRADINOU = "wrabradinou";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prefs);

        //home
        homerythmiseis = findViewById(R.id.homerythmiseis);
        homerythmiseis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Homescreen();
            }

            ;
        });


        //Texts finbyid
        wrabreak = findViewById(R.id.wrabreak);
        wradec = findViewById(R.id.wradec);
        wramesi = findViewById(R.id.wramesi);
        wraapog = findViewById(R.id.wraapog);
        wralaunch = findViewById(R.id.wralaunch);


        //buttonid
        koubibreak = findViewById(R.id.koubiprwinou);
        koubidec = findViewById(R.id.koubidec);
        koubimesi = findViewById(R.id.koubimesi);
        koubiapog = findViewById(R.id.koubiapog);
        koubilaunch = findViewById(R.id.koubilaunch);


        //checkbox
        neronotibtn=findViewById(R.id.neronotibtn);


        //timepicker prwinoy
        koubibreak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cb = Calendar.getInstance();
                bhour = cb.get(Calendar.HOUR_OF_DAY);
                bminutes = cb.get(Calendar.MINUTE);


                btp = new TimePickerDialog(prefs.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Calendar cbv = Calendar.getInstance();
                        cbv.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        cbv.set(Calendar.MINUTE, minute-30);
                        cbv.set(Calendar.SECOND, 0);

                        wrabreak.setText("Πρωινο:"+String.format("%02d:%02d", hourOfDay, minute));
                        startAlarm(cbv,1);




                    }
                }, bhour, bminutes, false);
                btp.show();



            }

        });

        //timepicker dec
        koubidec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cd = Calendar.getInstance();
                bhour = cd.get(Calendar.HOUR_OF_DAY);
                bminutes = cd.get(Calendar.MINUTE);


                dtp = new TimePickerDialog(prefs.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Calendar cdv = Calendar.getInstance();
                        cdv.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        cdv.set(Calendar.MINUTE, minute-30);
                        cdv.set(Calendar.SECOND, 0);

                        wradec.setText("Δεκατιανό:"+String.format("%02d:%02d", hourOfDay, minute));
                        startAlarm(cdv,2);




                    }
                }, bhour, bminutes, false);
                dtp.show();



            }

        });

        //timepicker mesi
        koubimesi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cm = Calendar.getInstance();
                bhour = cm.get(Calendar.HOUR_OF_DAY);
                bminutes = cm.get(Calendar.MINUTE);


                mtp = new TimePickerDialog(prefs.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Calendar cmv = Calendar.getInstance();
                        cmv.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        cmv.set(Calendar.MINUTE, minute-30);
                        cmv.set(Calendar.SECOND, 0);

                        wramesi.setText("Μεσημεριανό:"+String.format("%02d:%02d", hourOfDay, minute));
                        startAlarm(cmv,3);




                    }
                }, bhour, bminutes, false);
                mtp.show();



            }

        });

        //timepicker apog
        koubiapog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ca = Calendar.getInstance();
                bhour = ca.get(Calendar.HOUR_OF_DAY);
                bminutes = ca.get(Calendar.MINUTE);


                atp = new TimePickerDialog(prefs.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Calendar cav = Calendar.getInstance();
                        cav.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        cav.set(Calendar.MINUTE, minute-30);
                        cav.set(Calendar.SECOND, 0);

                        wraapog.setText("Απογευματινό:"+String.format("%02d:%02d", hourOfDay, minute));
                        startAlarm(cav,4);




                    }
                }, bhour, bminutes, false);
                atp.show();



            }

        });

        //timepicker bradyno
        koubilaunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cl = Calendar.getInstance();
                bhour = cl.get(Calendar.HOUR_OF_DAY);
                bminutes = cl.get(Calendar.MINUTE);


                ltp = new TimePickerDialog(prefs.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Calendar clv = Calendar.getInstance();
                        clv.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        clv.set(Calendar.MINUTE, minute-30);
                        clv.set(Calendar.SECOND, 0);

                        wralaunch.setText("Βραδυνό:"+String.format("%02d:%02d", hourOfDay, minute));
                        startAlarm(clv,5);




                    }
                }, bhour, bminutes, false);
                ltp.show();



            }

        });


        //save
        saverythmiseis = findViewById(R.id.saverythmiseis);
        saverythmiseis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveprefs();
            }
        });


        ///////nero

        neronotibtn.setOnCheckedChangeListener(this);
        neronotibtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                int neroi =neronotibtn.isChecked()?1:0;
                if(neroi==1){startAlarmNero();}
                else{cancelAlarm();}

            }
        });


        loadData();






    }



    ///////////////////////////////////////////////METHODS/////////////////////////////////////////////////
    private void startAlarm(Calendar c,int i) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiverG.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, i, intent, 0);

        if (c.before(Calendar.getInstance())) {
            c.add(Calendar.DATE, 1);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(),AlarmManager.INTERVAL_DAY, pendingIntent);
        }
    }


    private void startAlarmNero() {


        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiverW.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 7, intent, 0);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 8);
        calendar.set(Calendar.MINUTE, 0);

// setRepeating() lets you specify a precise custom interval--in this case,
// 20 minutes.
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                1000 * 60 * 30, pendingIntent);
        Toast.makeText(this, "Ειδοποιηση για νερο καθε μισή ωρα", Toast.LENGTH_SHORT).show();


    }



    private void cancelAlarm() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiverW.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 7, intent, 0);

        alarmManager.cancel(pendingIntent);
        Toast.makeText(this, "Άκυρο το νερό", Toast.LENGTH_SHORT).show();

    }
    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS_GEYMATA, MODE_PRIVATE);
        if (sharedPreferences.contains(WRAPRWINOU)) {
            wrabreak.setText(sharedPreferences.getString(WRAPRWINOU, ""));
            wradec.setText(sharedPreferences.getString(WRADECAT, ""));
            wramesi.setText(sharedPreferences.getString(WRAMESI, ""));
            wraapog.setText(sharedPreferences.getString(WRADAPOGEM, ""));
            wralaunch.setText(sharedPreferences.getString(WRABRADINOU, ""));

            if( sharedPreferences.getString(WATER,"").equals("0")){
                neronotibtn.setChecked(false);
            }
            else{neronotibtn.setChecked(true);}


        }


    }
    public void saveprefs() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS_GEYMATA, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(WRAPRWINOU, wrabreak.getText().toString());
        editor.putString(WRADECAT, wradec.getText().toString());
        editor.putString(WRAMESI, wramesi.getText().toString());
        editor.putString(WRADAPOGEM, wraapog.getText().toString());
        editor.putString(WRABRADINOU, wralaunch.getText().toString());
        editor.putString(WATER,String.valueOf(neronotibtn.isChecked()?1:0));



        editor.apply();


        Toast.makeText(this, "O.K το χουμε", Toast.LENGTH_SHORT).show();

    }

    private void Homescreen() {
        Intent intent = new Intent(this, menu.class);
        startActivity(intent);

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }
}