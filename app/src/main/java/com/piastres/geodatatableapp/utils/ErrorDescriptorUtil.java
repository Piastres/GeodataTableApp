package com.piastres.geodatatableapp.utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.Objects;

import retrofit2.HttpException;

public class ErrorDescriptorUtil {
    public static String checkError(Throwable error) {
        String description = "Попробуйте позже";

        try {
            if(error instanceof HttpException) {
                HttpException e = (HttpException) error;
                try {
                    String errorBody = Objects.requireNonNull(e.response().errorBody()).string();
                    JSONObject jsonObject = new JSONObject(errorBody);
                    description = jsonObject.getString("message");
                } catch (IOException ex) {
                    return description;
                }
            }
            if (error instanceof SocketTimeoutException) {
                description = "Неполадки на сервере. Попробуйте позже";
            }
            if (error instanceof ConnectException) {
                description = "Отсутствует соединение";
            }

            return description;
        } catch (ClassCastException n) {
            return "Неполадки на сервере. Попробуйте позже";
        }
        catch (JSONException e){
            return description;
        }
    }
}
