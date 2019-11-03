package com.piastres.geodatatableapp.models;

import java.util.List;

public class SavedData {
    private int page;
    private List<Datum> datumList;
    private static SavedData instance;

    private SavedData() {}

    public static SavedData getInstance() {
        if (instance == null) {
            instance = new SavedData();
        }

        return instance;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Datum> getDatumList() {
        return datumList;
    }

    public void setDatumList(List<Datum> datumList) {
        this.datumList = datumList;
    }

    public void onDestroy() {
        if (datumList != null) {
            datumList = null;
        }
    }
}
