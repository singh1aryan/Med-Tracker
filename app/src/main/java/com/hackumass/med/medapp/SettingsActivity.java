package com.hackumass.med.medapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {

    TextView user,initial1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportActionBar().setTitle("Settings");
//        getActionBar().setTitle("Settings");

        user = findViewById(R.id.user);
        initial1 = findViewById(R.id.initial1);

        Intent i = getIntent();

        String a = i.getStringExtra("username");
        user.setText(a);

        initial1.setText(a.charAt(0) + "");

    }

    public void edit(View view){

    }
}
