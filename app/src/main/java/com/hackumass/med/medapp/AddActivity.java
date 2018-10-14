package com.hackumass.med.medapp;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

public class AddActivity extends  AppCompatActivity {

    EditText dateEditText;
    int month,currentDay,year;
    long epochTime;
    Button add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        add = findViewById(R.id.add);


        dateEditText = findViewById(R.id.date);
        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar newCalendar = Calendar.getInstance();
                month = newCalendar.get(Calendar.MONTH);
                year = newCalendar.get(Calendar.YEAR);
                currentDay=newCalendar.get(Calendar.DAY_OF_MONTH);
                showDatePicker(AddActivity.this, year, month, currentDay);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();

                setResult(8, intent);
                finish();
            }
        });
    }

    private void showDatePicker(Context context, int initialYear, int initialMonth, int initialDay) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int year, int month, int day) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(year, month, day);
                        epochTime = calendar.getTime().getTime();
                        dateEditText.setText(day + "/" + (month + 1) + "/" + year);

                        AddActivity.this.currentDay = day;
                        AddActivity.this.month = month;
                        AddActivity.this.year = year;

                    }
                }, initialYear, initialMonth, initialDay);
        datePickerDialog.setTitle("Select Date");
        datePickerDialog.show();
    }

}
