package com.piastres.geodatatableapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.piastres.geodatatableapp.R;
import com.piastres.geodatatableapp.models.Datum;

public class GeodataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geodata);

        if (getIntent().getExtras() != null) {
            Datum datum = (Datum) getIntent().getExtras().getSerializable("GEODATA_ITEM");
            System.out.println(datum.getName());
        }
    }
}
