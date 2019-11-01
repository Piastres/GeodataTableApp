package com.piastres.geodatatableapp.errors;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.HttpException;

public class CheckError {
    public String checkError(Throwable error) throws IOException, JSONException {
        String str = "Попробуйте позже";

        try {
            if(error instanceof HttpException) {
                HttpException e = (HttpException) error;
                String errorBody = e.response().errorBody().string();
                JSONObject jsonObject = new JSONObject(errorBody);
                str = jsonObject.getString("message");
            }
            if (error instanceof SocketTimeoutException) {
                str = "Неполадки на сервере. Попробуйте позже";
            }
            if (error instanceof ConnectException) {
                str = "Отсутствует соединение";
            }

            return str;
        } catch (ClassCastException n) {
            str = "Неполадки на сервере. Попробуйте позже";
            return str;
        }
        catch (JSONException e){
            return str;
        }
    }
    public String checkHttpError(String errorBody) throws JSONException {
        String str = "Попробуйте позже";
        try {
            JSONObject jsonObject = new JSONObject(errorBody);
            str = jsonObject.getString("message");
            return str;

        } catch (ClassCastException n) {
            return str;
        }
    }
}
