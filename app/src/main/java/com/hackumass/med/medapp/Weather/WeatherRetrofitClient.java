package com.hackumass.med.medapp.Weather;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Aryan Singh on 7/27/2018.
 */

public class WeatherRetrofitClient {

    public static final String BASE_URL="https://api.darksky.net/forecast/";
    public static Retrofit retrofit = null;

    public static Retrofit getClient(){
        if(retrofit==null){
            retrofit=new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
