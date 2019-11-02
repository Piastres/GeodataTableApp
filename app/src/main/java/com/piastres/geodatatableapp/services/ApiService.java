package com.piastres.geodatatableapp.services;

import com.piastres.geodatatableapp.models.GeodataResponse;
import com.piastres.geodatatableapp.models.LoginResponse;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    @POST("auth.cgi")
    Observable<LoginResponse> getAuth(@Query("username") String username,
                                      @Query("password") String password);

    @POST("data.cgi")
    Observable<GeodataResponse> getGeodataList(@Query("code") String code,
                                               @Query("p") int page);
}
