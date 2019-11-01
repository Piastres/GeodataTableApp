package com.piastres.geodatatableapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.piastres.geodatatableapp.R;
import com.piastres.geodatatableapp.controllers.ConnectionController;
import com.piastres.geodatatableapp.errors.CheckError;
import com.piastres.geodatatableapp.fragments.LoginErrorFragment;
import com.piastres.geodatatableapp.fragments.RequestErrorFragment;
import com.piastres.geodatatableapp.models.LoginRequest;
import com.piastres.geodatatableapp.models.LoginResponse;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.loginActivityLogin)
    EditText textLogin;

    @BindView(R.id.loginActivityPassword)
    EditText textPassword;

    @BindView(R.id.loginActivityButton)
    Button buttonLogin;

    @BindView(R.id.loginProgressBar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final String EMPTY_LOGIN_FORM = "Заполните все поля";
        buttonLogin.setOnClickListener(v -> {
            String login = String.valueOf(textLogin.getText());
            String password = String.valueOf(textPassword.getText());
            if (isLoginFromEmpty(login, password)) {
                showErrorDialogFragment(EMPTY_LOGIN_FORM);
            } else {
                getAuth(new LoginRequest(login, password));
            }
        });
    }

    @SuppressLint("CheckResult")
    private void getAuth(LoginRequest loginRequest) {
        ConnectionController.getApi().getAuth(loginRequest)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(__ -> progressBarShow())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::saveData
                        ,
                        error -> {
                            CheckError checkError = new CheckError();
                            showErrorDialogFragment(checkError.checkError(error));
                        }
                );
    }

    private void saveData(LoginResponse loginResponse) {
        if (!loginResponse.getCode().equals("ok")) {
            LoginErrorFragment loginErrorFragment = new LoginErrorFragment();
            loginErrorFragment.show(getSupportFragmentManager(), "fragment_login_error");
            progressBarHide();
        }

        Intent intent = new Intent(this, GeodataListActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("USER_CODE", loginResponse.getCode());
        intent.putExtras( bundle);
        this.startActivity(intent);
    }

    private void progressBarShow(){
        progressBar.setVisibility(View.VISIBLE);
    }

    protected void progressBarHide(){
        progressBar.setVisibility(View.GONE);
    }

    private void showErrorDialogFragment(String error) {
        Bundle bundle = new Bundle();
        bundle.putString("ERROR_TITLE", error);
        RequestErrorFragment requestErrorFragment = new RequestErrorFragment();
        requestErrorFragment.setArguments(bundle);
        requestErrorFragment.show(getSupportFragmentManager(), "fragment_request_error");
    }

    private boolean isLoginFromEmpty(String login, String password) {
        try {
            return login.equals("") || password.equals("");
        } catch (NullPointerException e) {
            return true;
        }
    }
}
