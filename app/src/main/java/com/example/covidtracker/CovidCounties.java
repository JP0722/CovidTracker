package com.example.covidtracker;

public class CovidCounties {

    private String Country;
    private String CountryCode;
    private String Slug;
    private String  NewConfirmed;
    private String TotalConfirmed;
    private String NewDeaths;
    private String TotalDeaths;
    private String NewRecovered;
    private String TotalRecovered;
    private String Date;

    public String getCountry() {
        return Country;
    }

    public String getCountryCode() {
        return CountryCode;
    }

    public String getSlug() {
        return Slug;
    }

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

    public String getDate() {
        return Date;
    }

    public CovidCounties(String country, String countryCode, String slug, String newConfirmed, String totalConfirmed, String newDeaths, String totalDeaths, String newRecovered, String totalRecovered, String date) {
        Country = country;
        CountryCode = countryCode;
        Slug = slug;
        NewConfirmed = newConfirmed;
        TotalConfirmed = totalConfirmed;
        NewDeaths = newDeaths;
        TotalDeaths = totalDeaths;
        NewRecovered = newRecovered;
        TotalRecovered = totalRecovered;
        Date = date;
    }
}
