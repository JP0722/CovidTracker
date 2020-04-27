package com.example.covidtracker;

import com.google.gson.annotations.SerializedName;

public class CovidGlobal {


    private String  NewConfirmed;
    private String TotalConfirmed;
    private String NewDeaths;
    private String TotalDeaths;
    private String NewRecovered;
    private String TotalRecovered;

    public String getNewConfirmed() {
        return NewConfirmed;
    }

    public String getTotalConfirmed() {
        return TotalConfirmed;
    }

    public String getNewDeaths() {
        return NewDeaths;
    }

    public String getTotalDeaths() {
        return TotalDeaths;
    }

    public String getNewRecovered() {
        return NewRecovered;
    }

    public String getTotalRecovered() {
        return TotalRecovered;
    }

    public CovidGlobal(String newConfirmed, String totalConfirmed, String newDeaths, String totalDeaths, String newRecovered, String totalRecovered) {
        NewConfirmed = newConfirmed;
        TotalConfirmed = totalConfirmed;
        NewDeaths = newDeaths;
        TotalDeaths = totalDeaths;
        NewRecovered = newRecovered;
        TotalRecovered = totalRecovered;
    }
}
