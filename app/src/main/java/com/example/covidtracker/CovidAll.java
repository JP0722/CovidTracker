package com.example.covidtracker;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CovidAll {

    @SerializedName("Global")
    private CovidGlobal covidGlobal;

    @SerializedName("Countries")
    private List<CovidCounties> covidCountiesList;

    public CovidGlobal getCovidGlobal() {
        return covidGlobal;
    }

    public List<CovidCounties> getCovidCountiesList() {
        return covidCountiesList;
    }
}
