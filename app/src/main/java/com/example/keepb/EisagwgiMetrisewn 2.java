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


import com.example.keepb.RecyclerViewClasses.DatabaseHelper;
import com.example.keepb.RecyclerViewClasses.MetrsisiAdapter;

public class EisagwgiMetrisewn extends AppCompatActivity {


    private Button neaMetrisiBtn;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private DatabaseHelper dbHelper;
    private MetrsisiAdapter adapter;
    private String filter = "";
    Button HomeMetrisi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eisagwgi_metrisewn);


        //koumpi progress
        neaMetrisiBtn = (Button) findViewById(R.id.neaMetrisiBtn);

       neaMetrisiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openpageneametrisi();
            }


        });


        //RECYCLEVIEW///////////////////////////////!@#$%^&*()_+
        mRecyclerView = (RecyclerView) findViewById(R.id.palies_metriseis_RV);
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //populate recyclerview
        populaterecyclerView(filter);


        HomeMetrisi = findViewById(R.id.homeMetriseis);
        HomeMetrisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Homescreen1();
            }

            ;
        });


    }

    private void Homescreen1() {
        Intent intent = new Intent(this, menu.class);
        startActivity(intent);

    }


    private void populaterecyclerView(String filter) {
        dbHelper = new DatabaseHelper(this);
        adapter = new MetrsisiAdapter(dbHelper.metrsiList(filter), this, mRecyclerView);
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
   public void openpageneametrisi() {
        Intent intent = new Intent(this, NeaMetrisi.class);
        startActivity(intent);
    }
}
