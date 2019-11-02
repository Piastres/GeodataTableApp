package com.piastres.geodatatableapp.fragments;


import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.piastres.geodatatableapp.R;

import java.util.Objects;

public class RequestErrorFragment extends DialogFragment {

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


        TextView textTitle = view.findViewById(R.id.requestErrorTitle);
        Button buttonClose = view.findViewById(R.id.requestErrorDialogButton);
        if (getArguments() != null) {
            textTitle.setText(getArguments().getString("ERROR_TITLE"));
        }

        buttonClose.setOnClickListener(v ->
                Objects.requireNonNull(getDialog()).dismiss());
        builder.setView(view);

        return builder.create();
    }

}
