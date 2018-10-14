package com.hackumass.med.medapp;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.hackumass.med.medapp.Database.Contract;
import com.hackumass.med.medapp.Database.MedOpenHelper;

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


    public void save(View view){
        String symptoms="";
        String date = "";
        String medications = "";
        int smoking=0;
        int alcohol = 0;
        int pain = 0;

        MedOpenHelper openHelper = MedOpenHelper.getInstance(getApplicationContext());
        SQLiteDatabase database = openHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Contract.User.COLUMN_SYMPTOMS, symptoms);
        contentValues.put(Contract.User.COLUMN_ALCOHOL, alcohol);
        contentValues.put(Contract.User.COLUMN_DATE, date);
        contentValues.put(Contract.User.COLUMN_MEDICATIONS, medications);
        contentValues.put(Contract.User.COLUMN_PAIN, pain);
        contentValues.put(Contract.User.COLUMN_SMOKING, smoking);

        long id = database.insert(Contract.User.TABLE_NAME, null, contentValues);


    }
}
