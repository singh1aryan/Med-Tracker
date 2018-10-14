package com.hackumass.med.medapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {
    RecyclerView rv;
    LinearLayoutManager llm;
    ArrayList<History> days=new ArrayList<>();
    RVAdapter rvAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        rv= findViewById(R.id.history_rv);
        llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        ArrayList<String> symptoms=new ArrayList<>();
        symptoms.add("Cough, cold, fever");

        ArrayList<Medication> medications=new ArrayList<>();
        medications.add(new Medication("Metoprolol",1,1,true));
        medications.add(new Medication("Synthroid",1,1,true));
        medications.add(new Medication("Lipitor",1,1,true));

        days.add(new History("Day 1",1,symptoms,medications));
        days.add(new History("Day 2",2,symptoms,medications));
        days.add(new History("Day 3",3,symptoms,medications));
        days.add(new History("Day 4",1,symptoms,medications));
        days.add(new History("Day 5",4,symptoms,medications));
        days.add(new History("Day 6",3,symptoms,medications));

        rvAdapter=new RVAdapter(days);
        rv.setAdapter(rvAdapter);
    }
}
