package com.hackumass.med.medapp.Weather;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Aryan Singh on 7/27/2018.
 */

public interface WeatherInterface {

    @GET("{api}/{latitude},{longitude}")
    Call<WeatherDetails> getWeatherDetails(@Path("api")String api,@Path("latitude")float latitude, @Path("longitude")float longitude);
}

