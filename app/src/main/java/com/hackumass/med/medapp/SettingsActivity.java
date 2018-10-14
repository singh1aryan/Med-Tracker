package com.hackumass.med.medapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {

    TextView user,history;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        user = findViewById(R.id.user);
        history = findViewById(R.id.history);

        Intent i = getIntent();
        String a = i.getStringExtra("username");
        user.setText(a);


        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this,HistoryActivity.class);
                startActivity(intent);
            }
        });
    }
}
