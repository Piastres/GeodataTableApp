package com.piastres.geodatatableapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.piastres.geodatatableapp.R;
import com.piastres.geodatatableapp.adapters.GeodataListAdapter;
import com.piastres.geodatatableapp.controllers.ConnectionController;
import com.piastres.geodatatableapp.errors.ErrorDescriptor;
import com.piastres.geodatatableapp.models.Datum;
import com.piastres.geodatatableapp.models.GeodataResponse;
import com.piastres.geodatatableapp.models.SavedData;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class GeodataListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geodata_list);

        SavedData savedData = SavedData.getInstance();
        savedData.setPage(1);
        savedData.setDatumList(new ArrayList<>());

        String code = "";
        if (getIntent().getExtras() != null) {
            code = getIntent().getExtras().getString("USER_CODE");
        }

        NestedScrollView scroller = findViewById(R.id.geodataListScroller);
        RecyclerView recyclerView = findViewById(R.id.geodataList);
        recyclerView.setNestedScrollingEnabled(false);
        GeodataListAdapter adapter = new GeodataListAdapter(this, savedData.getDatumList(), position -> {
            Intent intent = new Intent(this, GeodataActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("GEODATA_ITEM", savedData.getDatumList().get(position));
            intent.putExtras(bundle);
            this.startActivity(intent);
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getGeodataList(code, savedData.getPage(), adapter);

        String finalCode = code;
        scroller.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                int page = savedData.getPage();
                if (page != -1) {
                    page += 1;
                    savedData.setPage(page);
                    getGeodataList(finalCode, page, adapter);
                }
            }
        });
    }

    @SuppressLint("CheckResult")
    private void getGeodataList(String code, int page, GeodataListAdapter adapter) {
        ConnectionController.getApi().getGeodataList(code, page)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(__ -> setProgressBarVisible(true))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(geodataResponse -> {
                    updateListAdapter(geodataResponse, adapter);
                }
                        ,
                        error -> {
                            /**
                             * TODO: show errorDialogFragment
                             */
                            setPageDisable();
                            setProgressBarVisible(false);
//                            ErrorDescriptor errorDescriptor = new ErrorDescriptor();
//                            showErrorDialogFragment(errorDescriptor.checkError(error));
                        }
                );
    }

    private void setPageDisable() {
        SavedData savedData = SavedData.getInstance();
        savedData.setPage(-1);
    }

    private void setProgressBarVisible(boolean isVisible) {
        ProgressBar progressBar = findViewById(R.id.geodataListProgressBar);
        if (isVisible) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

    private void updateListAdapter(GeodataResponse geodataResponse, GeodataListAdapter adapter) {
        SavedData savedData = SavedData.getInstance();
        if (geodataResponse.getStatus().equals("ok")) {
            List<Datum> datumList = savedData.getDatumList();
            datumList.addAll(datumList.size(), geodataResponse.getData());
            List<Datum> list = new ArrayList<>(datumList);
            savedData.setDatumList(list);
            adapter.dataChanged(list);
        } else {
            setPageDisable();
        }
        setProgressBarVisible(false);
    }
}
