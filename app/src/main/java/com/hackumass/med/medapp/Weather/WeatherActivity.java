package com.hackumass.med.medapp.Weather;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hackumass.med.medapp.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class WeatherActivity extends AppCompatActivity {

    List<Datum> hourDetailsList;
    WeatherAdaptor weatherAdaptor;
    RecyclerView hourlyRecyclerView;

    float latitude = 42.3732f;
    float longitude = 72.5199f;

    TextView temperature_current, range, feel, datetime,
            current_summary, wind, uv, visible, humidity;

    Retrofit retrofit = WeatherRetrofitClient.getClient();
    WeatherInterface weatherInterface = retrofit.create(WeatherInterface.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        getSupportActionBar().setTitle("Weather");

        hourlyRecyclerView = findViewById(R.id.hourlyRecyclerView);
        current_summary = findViewById(R.id.summary);

    hourDetailsList =new ArrayList<>();
    weatherAdaptor =new

    WeatherAdaptor(this,hourDetailsList, new WeatherAdaptor.MoviesClickListener() {
        @Override
        public void onMovieClick (View view,int position){

        }
    });
        hourlyRecyclerView.setAdapter(weatherAdaptor);
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        hourlyRecyclerView.setLayoutManager(linearLayoutManager);

        invokeCall();
}

    public void invokeCall(){

    retrofit2.Call<WeatherDetails> getHourly = weatherInterface.getWeatherDetails(WeatherConstants.WEATHER_API, latitude, longitude);
    getHourly.enqueue(new Callback<WeatherDetails>() {
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void onResponse(Call<WeatherDetails> call, Response<WeatherDetails> response) {
            WeatherDetails weatherDetails = response.body();
            Hourly hourly = weatherDetails.getHourly();

            String su = hourly.getSummary();
            current_summary.setText(su);
            List<Datum> list = hourly.getData();

            hourDetailsList.clear();
            hourDetailsList.addAll(list);
            weatherAdaptor.notifyDataSetChanged();

        }

        @Override
        public void onFailure(Call<WeatherDetails> call, Throwable t) {

        }
    });

}


}
