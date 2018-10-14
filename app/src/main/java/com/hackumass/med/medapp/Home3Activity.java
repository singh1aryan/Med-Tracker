package com.hackumass.med.medapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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

public class Home3Activity extends AppCompatActivity {

    TextView name,initial;
    Switch notifswitch;
    String n = "";
    String email,conditions,medications;
    int age,sex,smoke,alcohol,pain,lifestyle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent i = getIntent();
        if(i.getBooleanExtra("from",false)){
            // coming from sign up
            email = i.getStringExtra("email");
            conditions = i.getStringExtra("conditions");
            medications = i.getStringExtra("medications");
            age = i.getIntExtra("age",0);
            sex = i.getIntExtra("sex",0);
            smoke = i.getIntExtra("smoke",0);
            alcohol = i.getIntExtra("alcohol",0);
            pain = i.getIntExtra("pain",0);
            lifestyle = i.getIntExtra("lifestyle",0);
            n = email;

        }
        else{//coming from sign in
            n = i.getStringExtra("username");
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home3Activity.this,AddActivity.class);
                startActivityForResult(intent,3);
            }
        });

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
            Intent intent = new Intent(Home3Activity.this, MyReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(Home3Activity.this, 1, intent, 0);
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
        Intent intent = new Intent(Home3Activity.this, WeatherActivity.class);
        startActivity(intent);
    }

    public void smoking(View view){
        Intent intent = new Intent(Home3Activity.this,SmokingActivity.class);
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

    public void history(View view){
        Intent intent = new Intent(Home3Activity.this,HistoryActivity.class);
        startActivity(intent);
    }
}




