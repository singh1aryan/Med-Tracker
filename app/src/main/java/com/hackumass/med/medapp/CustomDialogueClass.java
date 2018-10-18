package com.hackumass.med.medapp;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.hackumass.med.medapp.Database.Contract;
import com.hackumass.med.medapp.Database.MedOpenHelper;
import com.hackumass.med.medapp.Database.User;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aryan Singh on 10/13/2018.
 */

public class CustomDialogueClass extends Dialog implements
        android.view.View.OnClickListener{


    public Activity c;
    public Dialog d;
    public GraphView graph;

    public CustomDialogueClass(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialogue);
        graph = findViewById(R.id.graph);
        GraphView graph = (GraphView) findViewById(R.id.graph);

        List<User> users = new ArrayList<>();

        MedOpenHelper openHelper = MedOpenHelper.getInstance(getContext());
        SQLiteDatabase database = openHelper.getReadableDatabase();
        Cursor cursor = database.query(Contract.User.TABLE_NAME, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            String date = cursor.getString(cursor.getColumnIndex(Contract.User.COLUMN_DATE));
            String symptoms = cursor.getString(cursor.getColumnIndex(Contract.User.COLUMN_SYMPTOMS));
            String medications = cursor.getString(cursor.getColumnIndex(Contract.User.COLUMN_MEDICATIONS));
            int smoke = cursor.getInt(cursor.getColumnIndex(Contract.User.COLUMN_SMOKING));
            int pain = cursor.getInt(cursor.getColumnIndex(Contract.User.COLUMN_PAIN));
            int alcohol = cursor.getInt(cursor.getColumnIndex(Contract.User.COLUMN_ALCOHOL));

            long id1 = cursor.getLong(cursor.getColumnIndex(Contract.User.COLUMN_ID));
            if(id1>-1) {
                User user = new User(symptoms,pain,smoke,alcohol,medications,date);
                users.add(user);
            }
        }
        if(users.size() >=6){
            Toast.makeText(getContext(),"We have it ready for you",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(getContext(),"Please wait for a week...",Toast.LENGTH_LONG).show();
        }


        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6),
                new DataPoint(5, 6)
        });
        graph.addSeries(series);

        GraphView graph1 = (GraphView) findViewById(R.id.graph1);
        LineGraphSeries<DataPoint> series1 = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 5),
                new DataPoint(4, 3),
                new DataPoint(5, 5),
                new DataPoint(6, 3)
        });
        graph1.addSeries(series1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
        }
        dismiss();
    }
}