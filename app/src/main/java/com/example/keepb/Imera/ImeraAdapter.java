package com.example.keepb.Imera;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.keepb.R;
import com.example.keepb.UpImera;

import java.util.List;

public class ImeraAdapter extends RecyclerView.Adapter<ImeraAdapter.ImeraViewHolder> {

    private Context daycontext;
    private RecyclerView dayRecycleV;
    private List<ImeraClass> mDayList;



    public class ImeraViewHolder extends RecyclerView.ViewHolder{
        public TextView merav,btv,bwv,dtv,dwv,mtv,mwv,atv,awv,ltv,lwv,waterv,alcoolv,anapsiktikov,gymv,ypnosv,epipleonv;


        public View daylayout;


        public ImeraViewHolder( View v) {
            super(v);

            daylayout =v;


            btv= itemView.findViewById(R.id.breakTimev);
            bwv= itemView.findViewById(R.id.breakwhatv);
            dtv= itemView.findViewById(R.id.decTimev);
            dwv= itemView.findViewById(R.id.decwhatv);
            mtv= itemView.findViewById(R.id.mesiTimev);
            mwv= itemView.findViewById(R.id.mesiwhatv);
            atv= itemView.findViewById(R.id.apogTimev);
            awv= itemView.findViewById(R.id.apogwhatv);
            ltv= itemView.findViewById(R.id.launchTimev);
            lwv= itemView.findViewById(R.id.launchwhatv);
            waterv= itemView.findViewById(R.id.Nerobtnv);
            alcoolv= itemView.findViewById(R.id.alcoolbtnv);
            gymv= itemView.findViewById(R.id.gymbtnv);
            anapsiktikov= itemView.findViewById(R.id.cocav);
            ypnosv= itemView.findViewById(R.id.ratingBarYpnosv);
            epipleonv= itemView.findViewById(R.id.epipleonv);
            merav=itemView.findViewById(R.id.merav);



        }
    }


    public void addDay(int positionday,ImeraClass day) {
        mDayList.add(positionday, day);
        notifyItemInserted(positionday);
    }


    public void removeDay(int positionday) {
        mDayList.remove(positionday);
        notifyItemRemoved(positionday);
    }



    public ImeraAdapter(List<ImeraClass> mdayDataset ,Context dcontext, RecyclerView dayrecycleView){
        daycontext=dcontext;
        dayRecycleV = dayrecycleView;
        mDayList =mdayDataset;




    }





    @Override
    public ImeraAdapter.ImeraViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {



        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.mia_mera, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ImeraAdapter.ImeraViewHolder vhday = new ImeraAdapter.ImeraViewHolder(v);

        return vhday;
    }

    @Override
    public void onBindViewHolder( ImeraAdapter.ImeraViewHolder holder, final int positionday) {


        final ImeraClass day =mDayList.get(positionday);


        holder.merav.setText("HΜΕΡΑ:"+day.getImera());

        if(day.getBreaktime().equals("1")) {
            holder.btv.setText("NAI");
        }
        else{holder.btv.setText("OXI");}
        if(day.getBreakwhat().equals("1")){
            holder.bwv.setText("NAI");}
        else{holder.bwv.setText("OXI");}


        if(day.getDectime().equals("1")) {
            holder.dtv.setText("NAI");
        }
        else{holder.dtv.setText("OXI");}
        if(day.getDecwhat().equals("1")){
            holder.dwv.setText("NAI"); }
        else{holder.dwv.setText("OXI"); }

        if(day.getMesitime().equals("1")) {
            holder.mtv.setText("NAI");
        }
        else{holder.mtv.setText("OXI");}
        if(day.getMesiwhat().equals("1")){
            holder.mwv.setText("NAI"); }
        else{holder.mwv.setText("OXI"); }

        if(day.getApotime().equals("1")) {
            holder.atv.setText("NAI");
        }
        else{holder.atv.setText("OXΙ");}
        if(day.getApowhat().equals("1")){
            holder.awv.setText("NAI"); }
        else{holder.awv.setText("OXI"); }


        if(day.getLaunchtime().equals("1")) {
            holder.ltv.setText("NAI");
        }
        else{holder.ltv.setText("OXI");}
        if(day.getLaunchwhat().equals("1")){
            holder.lwv.setText("NAI"); }
        else{holder.lwv.setText("OXI"); }







        holder.waterv.setText("Νερο:"+day.getWater()+"λιτρα");



        if(day.getAlcool().equals("0")){
            holder.alcoolv.setText("ΔΕΝ ΗΠΙΑ ΑΚΟΟΛ");}
        else {
            holder.alcoolv.setText(" ΗΠΙΑ ΑΚΟΟΛ");}



        String gym =day.getGym();
        if(gym.equals("0")){
            holder.gymv.setText("ΔΕΝ ΕΚΑΝΑ ΓΥΜΝΑΣΤΙΚΗ:");}
        else{ holder.gymv.setText(" ΕΚΑΝΑ ΓΥΜΝΑΣΤΙΚΗ:");}


        String coca =day.getAnapsiktiko();
        if(coca.equals("0")){
            holder.anapsiktikov.setText("ΔΕΝ ΗΠΙΑ ΑΝΑΨΥΚΤΙΚΟ");}
        else{ holder.anapsiktikov.setText("ΗΠΙΑ ΑΝΑΨΥΚΤΙΚΟ");}

        String night = day.getYpnos();

        switch (night){
            case "5": holder.ypnosv.setText("ΚΟΙΜΗΘΗΚΑ ΤΕΛΕΙΑ");
                break ;
            case "4": holder.ypnosv.setText("ΚΟΙΜΗΘΗΚΑ ΚΑΛΑ");
                break ;
            case "3": holder.ypnosv.setText("ΚΟΙΜΗΘΗΚΑ ΜΕΤΡΙΑ");
                break ;
            case "2": holder.ypnosv.setText("ΚΟΙΜΗΘΗΚΑ ΟΧΙ ΚΑΛΑ");
                break ;
            case "1": holder.ypnosv.setText("ΚΟΙΜΗΘΗΚΑ ΧΑΛΙΑ");
                break ;
            case "0" :   holder.ypnosv.setText("Δε ξερω πως κοιμηθηκα");

        }


        holder.epipleonv.setText("ΕΦΑΓΑ ΕΠΙΠΛΕΟΝ:"+day.getEpileon());



        holder.daylayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(daycontext);
                builder.setTitle("Choose option");
                builder.setMessage("Update or delete day?");
                builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //go to update activity
                        goToUpdateActivity(day.getIdday());

                    }
                });
                builder.setNeutralButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogday, int whichday) {
                        ImeraHelper dbdayHelper = new ImeraHelper(daycontext);
                        dbdayHelper.deleteDayRecord(day.getIdday(), daycontext);

                        mDayList.remove(positionday);
                        dayRecycleV.removeViewAt(positionday);
                        notifyItemRemoved(positionday);
                        notifyItemRangeChanged(positionday, mDayList.size());
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }
        });

    }

    private void goToUpdateActivity(long imeraid){
        Intent goToUpdateimera = new Intent(daycontext, UpImera.class);
        goToUpdateimera.putExtra("IDDAY", imeraid);
        daycontext.startActivity(goToUpdateimera);
    }

    @Override
    public int getItemCount() {
        return mDayList.size();

    }
}
