package com.hackumass.med.medapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {

    TextView user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        user = findViewById(R.id.user);

        Intent i = getIntent();
        String a = i.getStringExtra("username");
        user.setText(a);

    }

    public void edit(View view){

    }
}
