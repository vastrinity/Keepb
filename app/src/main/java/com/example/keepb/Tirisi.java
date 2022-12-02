package com.example.keepb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;


import com.example.keepb.Imera.ImeraAdapter;
import com.example.keepb.Imera.ImeraHelper;

public class Tirisi extends AppCompatActivity {








    private RecyclerView mRecyclerView;
    private Button merabtn,homeImeres,stats;
    private RecyclerView.LayoutManager mLayoutManager;
    private ImeraHelper dbHelper;
    private ImeraAdapter adapter;
    private String filter = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tirisi);



        merabtn = findViewById(R.id.merabtn);
        merabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMera();
            }
        });













        //RECYCLEVIEW///////////////////////////////!@#$%^&*()_+
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //populate recyclerview
        populaterecyclerView(filter);

        homeImeres = findViewById(R.id.homeImeres);
        homeImeres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Homescreen();
            }
        });

        stats=findViewById(R.id.statbtn);
        stats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openstats();

            }
        });

    }
    private void Homescreen() {
        Intent intent = new Intent(this,menu.class);
        startActivity(intent);

    }
    private void openstats() {
        Intent intent = new Intent(this,statistika.class);
        startActivity(intent);

    }


    private void populaterecyclerView(String filter){
        dbHelper = new ImeraHelper(this);
        adapter = new ImeraAdapter(dbHelper.dayList(filter), this, mRecyclerView);
        mRecyclerView.setAdapter(adapter);



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        MenuItem item = menu.findItem(R.id.filterSpinner);
        Spinner spinner = (Spinner) MenuItemCompat.getActionView(item);

        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.filterOptions, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String filter = parent.getSelectedItem().toString();
                populaterecyclerView(filter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                populaterecyclerView(filter);
            }
        });


        spinner.setAdapter(adapter);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.addMenu:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    /////////show data//me8odos gia na paei sti selida Nea Metrsi






    private void openMera() {
        Intent intent = new Intent(this, imera.class);
        startActivity(intent);
    }


}



