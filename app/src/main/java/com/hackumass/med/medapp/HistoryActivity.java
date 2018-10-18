package com.hackumass.med.medapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hackumass.med.medapp.Database.Contract;
import com.hackumass.med.medapp.Database.MedOpenHelper;
import com.hackumass.med.medapp.Database.User;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    RecyclerView rv;
    List<User> users;
    RVAdapter rvAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        getSupportActionBar().setTitle("History");

        rv= findViewById(R.id.history_rv);
        users = new ArrayList<>();


        MedOpenHelper openHelper = MedOpenHelper.getInstance(this);
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

        rvAdapter = new RVAdapter(this, users, new RVAdapter.ToDoClickListener() {
            @Override
            public void onToDoClick(View view, int position) {

            }

            @Override
            public void onToDoLongClick(View view, int position) {

            }

            @Override
            public void buttonClick(View view, int position) {

            }
        });
        rv.setAdapter(rvAdapter);
        rv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
    }
}
