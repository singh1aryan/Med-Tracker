package com.hackumass.med.medapp;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

/**
 * Created by Aryan Singh on 10/13/2018.
 */

public class CustomDialogClass extends Dialog implements
        android.view.View.OnClickListener{


    public Activity c;
    public Dialog d;
    public GraphView graph;

    public CustomDialogClass(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.summary_dialog);
        TextView textView = findViewById(R.id.pthings);
        TextView textView1 = findViewById(R.id.nthings);

        textView.setText(R.string.list);
        textView1.setText(R.string.nlist);

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