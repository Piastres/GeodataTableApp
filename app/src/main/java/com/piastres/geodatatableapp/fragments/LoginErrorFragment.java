package com.piastres.geodatatableapp.fragments;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.piastres.geodatatableapp.R;

import java.util.Objects;

import butterknife.BindView;

public class LoginErrorFragment extends DialogFragment {

    @BindView(R.id.loginErrorDialogButton)
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
                .inflate(R.layout.fragment_login_error, null);
        buttonClose.setOnClickListener(v ->
                Objects.requireNonNull(getDialog()).dismiss());
        builder.setView(view);

        return builder.create();
    }
}