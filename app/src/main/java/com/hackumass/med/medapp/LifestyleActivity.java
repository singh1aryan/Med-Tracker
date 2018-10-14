package com.hackumass.med.medapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class LifestyleActivity extends AppCompatActivity {

    EditText weightEditText;
    EditText heighEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifestyle);
    }
}
