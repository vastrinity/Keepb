package com.example.keepb.RecyclerViewClasses;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.keepb.R;
import com.example.keepb.UpMetrisi;

import java.util.List;

public class MetrsisiAdapter extends RecyclerView.Adapter<MetrsisiAdapter.MetrisiViewHolder> {
    private Context mcontext;
    private RecyclerView mRecycleV;
    private List<ModelClass> mMetrisiList;




    public class MetrisiViewHolder extends RecyclerView.ViewHolder{
        public TextView dateTv;
        public TextView kilaTv;
        public TextView liposTv;
        public TextView ygraTv;
        public TextView mysTv;
        public TextView metTv;
        public TextView fatlevelTv;


        public View layout;


        public MetrisiViewHolder( View v) {
            super(v);

            layout =v;

            dateTv = itemView.findViewById(R.id.dateTv);
            kilaTv = itemView.findViewById(R.id.kilaTv);
            liposTv = itemView.findViewById(R.id.liposTv);
            ygraTv = itemView.findViewById(R.id.ygraTv);
            mysTv = itemView.findViewById(R.id.mysTv);
            metTv = itemView.findViewById(R.id.metaTv);
            fatlevelTv = itemView.findViewById(R.id.fatlevelTv);
        }
    }


    public void add(int position, ModelClass metrisi) {
        mMetrisiList.add(position, metrisi);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        mMetrisiList.remove(position);
        notifyItemRemoved(position);
    }

    public MetrsisiAdapter(List<ModelClass> mDataset ,Context context, RecyclerView recycleView){
        mcontext=context;
        mRecycleV =recycleView;
        mMetrisiList=mDataset;


    }



    @Override
    public  MetrsisiAdapter.MetrisiViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.mia_metrisi, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MetrisiViewHolder vh = new MetrisiViewHolder(v);
        return vh;



    }

    @Override
    public void onBindViewHolder( MetrisiViewHolder holder, final int position) {

        final ModelClass metrisi =mMetrisiList.get(position);



        holder.dateTv.setText(" "+metrisi.getDate());
        holder.kilaTv.setText("ΚΙΛΑ: "+metrisi.getKila()+"");
        holder.liposTv.setText("ΛΙΠΟΣ: "+metrisi.getLipos()+"");
        holder.ygraTv.setText("ΥΓΡΑ: "+metrisi.getYgra()+"");
        holder.mysTv.setText("ΜΥΙΚΟΣ ΙΣΤΟΣ: "+metrisi.getMys()+"");
        holder.metTv.setText("ΜΕΤΑΒΟΛΙΚΗ ΗΛΙΚΙΑ: "+metrisi.getMeta_age());
        holder.fatlevelTv.setText("ΣΠΛΑΧΝΙΚΟ ΛΙΠΟΣ: "+metrisi.getFat_level());


        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mcontext);
                builder.setTitle("Choose option");
                builder.setMessage("Update or delete μετρηση?");
                builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //go to update activity
                        goToUpdateActivity(metrisi.getId());

                    }
                });
                builder.setNeutralButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseHelper dbHelper = new DatabaseHelper(mcontext);
                        dbHelper.deleteMetrisiRecord(metrisi.getId(), mcontext);

                        mMetrisiList.remove(position);
                        mRecycleV.removeViewAt(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, mMetrisiList.size());
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
    private void goToUpdateActivity(long metrisiId){
        Intent goToUpdate = new Intent(mcontext, UpMetrisi.class);
        goToUpdate.putExtra("ID", metrisiId);
        mcontext.startActivity(goToUpdate);
    }


    @Override
    public int getItemCount() {
        return mMetrisiList.size();
    }
}
