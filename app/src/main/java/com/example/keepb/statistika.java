package com.example.keepb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import com.example.keepb.Imera.ImeraHelper;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import static com.example.keepb.Imera.ImeraHelper.ALCOOL;
import static com.example.keepb.Imera.ImeraHelper.ANAPSIKTIKO;
import static com.example.keepb.Imera.ImeraHelper.APOGTIME;
import static com.example.keepb.Imera.ImeraHelper.APOGWHAT;
import static com.example.keepb.Imera.ImeraHelper.BREAKTIME;
import static com.example.keepb.Imera.ImeraHelper.BREAKWHAT;
import static com.example.keepb.Imera.ImeraHelper.DECTIME;
import static com.example.keepb.Imera.ImeraHelper.DECWHAT;
import static com.example.keepb.Imera.ImeraHelper.GYM;
import static com.example.keepb.Imera.ImeraHelper.LAUNCHTIME;
import static com.example.keepb.Imera.ImeraHelper.LAUNCHWHAT;
import static com.example.keepb.Imera.ImeraHelper.MESITIME;
import static com.example.keepb.Imera.ImeraHelper.MESIWHAT;
import static com.example.keepb.Imera.ImeraHelper.WATER;
import static com.example.keepb.Imera.ImeraHelper.YPNOS;


public class statistika extends AppCompatActivity {
    BarChart barchart;
    Button homestats;
    TextView statbt,statbw,statdt,statdw,statmt,statmw,statat,stataw,statlt,statlw,statwater,statalcool,statcoca,statgym,statypnos,synoloimerwn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistika);

        synoloimerwn=findViewById(R.id.imeressynolo);
        statbt =findViewById(R.id.statbt);
        statbw =findViewById(R.id.statbw);
        statdt=findViewById(R.id.statdt);
        statdw=findViewById(R.id.statdw);
        statmt=findViewById(R.id.statmt);
        statmw=findViewById(R.id.statmw);
        statat=findViewById(R.id.statat);
        stataw=findViewById(R.id.stataw);
        statlt=findViewById(R.id.statlt);
        statlw=findViewById(R.id.statlw);
        statwater=findViewById(R.id.statwater);
        statalcool=findViewById(R.id.statalcool);
        statcoca=findViewById(R.id.statcoca);
        statgym=findViewById(R.id.statgym);
        statypnos=findViewById(R.id.statypnos);

        barchart=findViewById(R.id.bargraph);

        homestats=findViewById(R.id.Homestat);
        homestats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Homescreen();

            }
        });
        stats();




    }
    private void Homescreen() {
        Intent intent = new Intent(this,menu.class);
        startActivity(intent);

    }

    public void stats(){


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
        float qcoca=avg.get_Avg(ANAPSIKTIKO);
        float qypnos=avg.get_Avg(YPNOS);
        float qgym=avg.get_Avg(GYM);

        int days =avg.countDays();




        int statbtv= (int) ((qbt)*100);
        int statbwv= (int) ((qbw)*100);
        int statdtv= (int) ((qdt)*100);
        int statdwv= (int) ((qdw)*100);
        int statmtv= (int) ((qmt)*100);
       int statmwv= (int) ((qmw)*100);
        int statatv= (int) ((qat)*100);
        int statawv= (int) ((qaw)*100);
        int statltv= (int) ((qlt)*100);
        int statlwv= (int) ((qlw)*100);
        float statwaterv= qwater;
        float statalcoolv =(qalcool)*100;
        float statcocav=(qcoca)*100;
        float statgymv=(qgym)*100;
        float statypnosv=qypnos;


        synoloimerwn.setText("Σύνολο ημερών:  "+String.valueOf(days));

        if(days!=0) {


            statbt.setText("Ωρα πρωινού:" + String.valueOf(statbtv) + "%");
            statbw.setText("Διατροφή πρωινού:" + String.valueOf(statbwv) + "%");
            statdt.setText("Ωρα δεκατιανού:" + String.valueOf(statdtv) + "%");
            statdw.setText("Διατροφή δεκατιανού:" + String.valueOf(statdwv) + "%");
            statmt.setText("Ωρα μεσημεριανού:" + String.valueOf(statmtv) + "%");
            statmw.setText("Διατροφή μεσημεριανού:" + String.valueOf(statmwv) + "%");
            statat.setText("Ωρα απογευματινού:" + String.valueOf(statatv) + "%");
            stataw.setText("Διατροφή απογευματινού:" + String.valueOf(statawv) + "%");
            statlt.setText("Ωρα βραδυνού:" + String.valueOf(statltv) + "%");
            statlw.setText("Διατροφή βραδυνού:" + String.valueOf(statlwv) + "%");

            statwater.setText("Λιτρα νερου : " + String.valueOf(statwaterv));
            statalcool.setText("Aλκοολ:" + String.valueOf(statalcoolv)+"%");
            statgym.setText("Γυμναστικη" + String.valueOf(statgymv)+"%");
            statcoca.setText("Aναψυκτικα:" + String.valueOf(statcocav)+"%");
            statypnos.setText(("Ποιοτητα ύπνου" + String.valueOf(statypnosv) + "/5"));
        }
        else{
            statmt.setText("ΑΚΟΜΑ ΔΕ ΞΕΚΙΝΗΣΑΜΕ...");
        }




        ArrayList<BarEntry> posostawra= new ArrayList<>();
        ArrayList<BarEntry> posostadiatrofi=new ArrayList<>();

        posostawra.add(new BarEntry(0f,statbtv));
        posostadiatrofi.add(new BarEntry(0f,statbwv));
        posostawra.add(new BarEntry(2f,statdtv));
        posostadiatrofi.add(new BarEntry(2f,statdwv));
        posostawra.add(new BarEntry(4f,statmtv));
        posostadiatrofi.add(new BarEntry(4f,statmwv));
        posostawra.add(new BarEntry(6f,statatv));
        posostadiatrofi.add(new BarEntry(6f,statawv));
        posostawra.add(new BarEntry(8f,statltv));
        posostadiatrofi.add(new BarEntry(8f,statlwv));

       String[] geymata =new String[]{"Πρωινό","Δεκατιανό","Μεσημεριανό","Απογευματινό","Βραδινό"};




        BarDataSet set1 = new BarDataSet(posostawra, "Σωστή ώρα");
        set1.setColors(Color.BLUE);
        BarDataSet set2 = new BarDataSet(posostadiatrofi, "Σωστή Διατροφή");
        set2.setColors(Color.MAGENTA);


        BarData data =new BarData(set1,set2);
        barchart.setData(data);

        float groupspace = 0.1f;
        float barspace=0.02f;
        float barWidth=0.43f;
        data.setBarWidth(barWidth);








        barchart.setDrawBarShadow(false);
        barchart.setDrawValueAboveBar(false);
        barchart.setMaxVisibleValueCount(100);
        barchart.setPinchZoom(false);
        barchart.setDrawGridBackground(true);
        barchart.invalidate();
        barchart.setTouchEnabled(true);
        barchart.setScaleEnabled(true);
        barchart.groupBars(0,groupspace,barspace);
        barchart.setVisibleXRangeMaximum(4);









        XAxis xAxis=barchart.getXAxis();


        xAxis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);
        xAxis.setGranularity(1);
        xAxis.setCenterAxisLabels(true);
        xAxis.setAxisMinimum(0);


        xAxis.setValueFormatter(new IndexAxisValueFormatter(geymata));

    }


    }







