package com.piastres.geodatatableapp.services;

import com.piastres.geodatatableapp.models.LoginRequest;
import com.piastres.geodatatableapp.models.LoginResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    @POST("auth.cgi")
    Observable<LoginResponse> getAuth(@Query("username") String username,
                                      @Query("password") String password);
//    Observable<LoginResponse> getAuth(@Body LoginRequest body);

    @POST("data.cgi")
    Observable<LoginResponse> getGeodataList(@Body LoginRequest body);
}
