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

        String code = "";
        if (getIntent().getExtras() != null) {
            getIntent().getExtras().getString("USER_CODE");
        }

        RecyclerView recyclerView = findViewById(R.id.geodataList);
        GeodataListAdapter adapter = new GeodataListAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                //nothing to do
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                if (newState == RecyclerView.SCROLL_STATE_IDLE) {

                }
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
    }
}
