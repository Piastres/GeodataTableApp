package com.piastres.geodatatableapp.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.piastres.geodatatableapp.R;

import java.util.Objects;

public class LoginErrorFragment extends DialogFragment {

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
                .inflate(R.layout.fragment_login_error, null);

        Button buttonClose = view.findViewById(R.id.loginErrorDialogButton);
        buttonClose.setOnClickListener(v ->
                Objects.requireNonNull(getDialog()).dismiss());
        builder.setView(view);

        return builder.create();
    }
}