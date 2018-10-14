package com.hackumass.med.medapp.Weather;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hackumass.med.medapp.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Aryan Singh on 7/27/2018.
 */

public class WeatherAdaptor extends RecyclerView.Adapter<WeatherAdaptor.WeatherViewHolder>{

    public Context context;
    public List<Datum> list;
    public MoviesClickListener listener;
    Calendar calendar = Calendar.getInstance();
    //long i=11;

    public WeatherAdaptor(Context context, List<Datum> list, MoviesClickListener listener){
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.hour_row_layout,parent,false);
        return new WeatherViewHolder(itemView,listener);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder holder, int position) {
//
        Datum datum = list.get(position);


        if(datum.getTime()!=null){
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(datum.getTime());

            Date date = new Date(datum.getTime()*1000L);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a", Locale.getDefault());
            holder.time.setText(simpleDateFormat.format(date));
        }
        if(datum.getApparentTemperature()!=null){
            holder.temperature.setText(convertToC(datum.getApparentTemperature()) + " \u2103");
        }
        if(datum.getHumidity()!=null){
            Double d = datum.getHumidity()*100;
            int i = d.intValue();
            holder.humidity.setText(i + "%");
        }
        //Picasso.with(context).load(R.drawable.sun).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface MoviesClickListener {
        void onMovieClick(View view, int position);
    }

    public static class WeatherViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView time,temperature,humidity;
        MoviesClickListener moviesClickListener;

        public WeatherViewHolder(View itemView, MoviesClickListener listener) {
            super(itemView);
            moviesClickListener = listener;
            humidity = itemView.findViewById( R.id.humidity);
            time = itemView.findViewById(R.id.time);
            temperature = itemView.findViewById(R.id.temperature);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            moviesClickListener.onMovieClick(view,getAdapterPosition());
        }
    }

    public String convertToC(double temperature){
        temperature = ((temperature - 32)*5)/9;
        String s = temperature + "";
        s = s.substring(0,2);
        return s;
    }


}
