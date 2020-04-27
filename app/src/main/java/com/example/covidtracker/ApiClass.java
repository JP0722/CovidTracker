package com.example.covidtracker;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiClass {

    @GET("/world/total")
    Call<CovidObj> gettotaldetails();

    @GET("/summary")
    Call<CovidAll> getCountriesdetails();

}
