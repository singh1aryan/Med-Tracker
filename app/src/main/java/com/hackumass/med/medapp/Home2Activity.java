package com.hackumass.med.medapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import com.hackumass.med.medapp.Weather.WeatherActivity;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Calendar;

public class Home2Activity extends AppCompatActivity {

    SeekBar seekBar;
    TextView name,initial,sex;
    Switch notifswitch;
    String n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);

        Intent i = getIntent();
        n = i.getStringExtra("username");

        name = findViewById(R.id.name);
        initial = findViewById(R.id.initial);
        notifswitch = findViewById(R.id.notifswitch);

        if(n!=null) {
            n = n.split("@")[0];
            initial.setText(n.charAt(0) + "");
            name.setText(n);
        }

        boolean check = notifswitch.isChecked();
        if(!check){
            Intent intent = new Intent(Home2Activity.this, MyReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(Home2Activity.this, 1, intent, 0);
            Calendar calendar = Calendar.getInstance();
            AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
            long time1 = calendar.getTimeInMillis();
            manager.set(AlarmManager.RTC_WAKEUP, time1 + 5000,pendingIntent);
        }
        else{

        }
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
            startActivityForResult(intent,3);
        }
        if(item.getItemId() == R.id.setting){
            Intent intent = new Intent(this,SettingsActivity.class);
            intent.putExtra("username",n);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    public void painGraph(View view){
        CustomDialogueClass cdd=new CustomDialogueClass(this);
        cdd.show();
    }
    public void weather(View v){
        Intent intent = new Intent(Home2Activity.this, WeatherActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == 3){
            if(resultCode == 8){



            }
        }


        super.onActivityResult(requestCode, resultCode, data);
    }
}
