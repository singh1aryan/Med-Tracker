package com.hackumass.med.medapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class Home2Activity extends AppCompatActivity {

    SeekBar seekBar;
    TextView name,age,sex;
    LinearLayout painLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);

        seekBar = findViewById(R.id.seekBar);

        // get the data from the database
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.add){
            Intent intent = new Intent(this,AddActivity.class);
            startActivity(intent);
        }
        if(item.getItemId() == R.id.setting){
            Intent intent = new Intent(this,SettingsActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    public void painGraph(View view){
        CustomDialogueClass cdd=new CustomDialogueClass(this);
        cdd.show();
    }
}
