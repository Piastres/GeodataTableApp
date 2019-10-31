package com.piastres.geodatatableapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.piastres.geodatatableapp.R;
import com.piastres.geodatatableapp.adapters.GeodataListAdapter;

public class GeodataListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geodata_list);

        RecyclerView recyclerView = findViewById(R.id.geodataList);
        GeodataListAdapter adapter = new GeodataListAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
