package com.hackumass.med.medapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class LifestyleActivity extends AppCompatActivity {

    EditText weightEditText;
    EditText heighEditText;
    RadioGroup select_sex;

    TextView bmi,ans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifestyle);

        bmi = findViewById(R.id.bmi);
        ans = findViewById(R.id.ans);
        weightEditText = findViewById(R.id.weight);
        heighEditText = findViewById(R.id.height);
        select_sex = findViewById(R.id.select_sex);

        int i = select_sex.getCheckedRadioButtonId();
        if(i == R.id.female){
            bmi.setText("You are super overweight");
        }



    }
    public void calc(View view){
        double a =  Double.parseDouble(weightEditText.getText().toString());
        double b =  Double.parseDouble(heighEditText.getText().toString());
        b = b/100;
        double answer = a/(b*b);
        ans.setText(answer + "");
    }
}
