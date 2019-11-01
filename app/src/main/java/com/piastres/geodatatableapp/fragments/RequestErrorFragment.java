package com.piastres.geodatatableapp.fragments;


import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.piastres.geodatatableapp.R;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RequestErrorFragment extends DialogFragment {

    @BindView(R.id.requestErrorTitle)
    TextView textTitle;

    @BindView(R.id.requestErrorDialogButton)
    Button buttonClose;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Objects
                .requireNonNull(getActivity()));
        View view = getActivity().getLayoutInflater()
                .inflate(R.layout.fragment_request_error, null);

        if (getArguments() != null) {
            textTitle.setText(getArguments().getString("ERROR_TITLE"));
        }

        buttonClose.setOnClickListener(v ->
                Objects.requireNonNull(getDialog()).dismiss());
        builder.setView(view);

        return builder.create();
    }

}
