package com.hackumass.med.medapp;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import com.abdeveloper.library.MultiSelectDialog;
import com.abdeveloper.library.MultiSelectModel;
import com.hackumass.med.medapp.Database.Contract;
import com.hackumass.med.medapp.Database.MedOpenHelper;
import com.hackumass.med.medapp.Database.User;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class AddActivity extends AppCompatActivity {
    Button symptomsButton;
    Button medicationButton;
    RadioGroup smokeGroup;
    RadioGroup alcoholGroup;
    SeekBar painSeekbar;
    TextView painEmoji;
    Button addButton;

    ArrayList<String> medications = new ArrayList<>();
    ArrayList<String> symptoms = new ArrayList<>();

    EditText dateEditText;
    int month,currentDay,year;
    long epochTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        smokeGroup=findViewById(R.id.select_smoke);
        alcoholGroup=findViewById(R.id.select_alcohol);
        painSeekbar=findViewById(R.id.pain_seekbar);
        addButton = findViewById(R.id.add);

        symptomsButton = findViewById(R.id.select_symptoms);
        medicationButton = findViewById(R.id.select_medications);

        painSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                SelectEmoji(progress);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        symptomsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectSymptoms();
            }
        });
        medicationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectMedications();
            }
        });
        painEmoji=findViewById(R.id.pain_emoji);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a=AddCheck();
                if(!a.isEmpty())
                    Toast.makeText(AddActivity.this, a,
                            Toast.LENGTH_LONG).show();
                else
                {
                    Intent intent = new Intent();
                    intent.putExtra("smoke",smoke);
                    intent.putExtra("alcohol",alcohol);
                    intent.putExtra("pain",painLevel);
                    intent.putExtra("date", date);
                    String symptomString="";
                    if (symptoms.size()>0) {
                        for (String symptom : symptoms)
                            symptomString += symptom+",";
                        symptomString = symptomString.substring(0, symptomString.length() - 1);
                    }
                    String medicationString="";
                    if (medications.size()>0) {
                        for (String medication : medications)
                            medicationString += medication+",";
                        medicationString = medicationString.substring(0, medicationString.length() - 1);
                    }
                    intent.putExtra("symptoms",symptomString);
                    intent.putExtra("medications",medicationString);

                    MedOpenHelper openHelper = MedOpenHelper.getInstance(getApplicationContext());
                    SQLiteDatabase database = openHelper.getWritableDatabase();

                    ContentValues contentValues = new ContentValues();
                    contentValues.put(Contract.User.COLUMN_MEDICATIONS, medicationString);
                    contentValues.put(Contract.User.COLUMN_SYMPTOMS, symptomString);
                    contentValues.put(Contract.User.COLUMN_PAIN, painLevel);
                    contentValues.put(Contract.User.COLUMN_ALCOHOL, alcohol);
                    contentValues.put(Contract.User.COLUMN_DATE, date);
                    contentValues.put(Contract.User.COLUMN_SMOKING, smoke);

                    long id = database.insert(Contract.User.TABLE_NAME, null, contentValues);
                    User user = new User(symptomString,painLevel,smoke,alcohol,medicationString,date);

                    setResult(8, intent);
                    finish();
                }
            }
        });
        dateEditText = findViewById(R.id.date);
        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar newCalendar = Calendar.getInstance();
                month = newCalendar.get(Calendar.MONTH);
                year = newCalendar.get(Calendar.YEAR);
                currentDay=newCalendar.get(Calendar.DAY_OF_MONTH);
                showDatePicker(AddActivity.this, year, month, currentDay);
            }
        });
    }
    private void showDatePicker(Context context, int initialYear, int initialMonth, int initialDay) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int year, int month, int day) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(year, month, day);
                        epochTime = calendar.getTime().getTime();
                        dateEditText.setText(day + "/" + (month + 1) + "/" + year);
                        AddActivity.this.currentDay = day;
                        AddActivity.this.month = month;
                        AddActivity.this.year = year;
                    }
                }, initialYear, initialMonth, initialDay);
        datePickerDialog.setTitle("Select Date");
        datePickerDialog.show();
    }
    int smoke;
    int alcohol;
    int painLevel;
    String date;
    public String AddCheck()
    {
        if (smokeGroup.getCheckedRadioButtonId()!=-1) {
            int id=smokeGroup.getCheckedRadioButtonId();
            RadioButton a = findViewById(id);
            smoke=0;
            if(a.getText().toString().equals("Yes"));
            smoke=1;
        }
        else
            return "You must say if you smoke to continue";
        if (alcoholGroup.getCheckedRadioButtonId()!=-1) {
            int id=alcoholGroup.getCheckedRadioButtonId();
            RadioButton a = findViewById(id);
            alcohol=0;
            if(a.getText().toString().equals("Yes"));
            alcohol=1;
        }
        else
            return "You must say if you drink alcohol to continue";
        if (dateEditText.getText().toString().length()>0)
            date=dateEditText.getText().toString();
        else
            return "You must select a date before continuing";
        painLevel=painSeekbar.getProgress();
        return "";
    }
    public void SelectEmoji(int progress)
    {
        if(progress==0)
            painEmoji.setText("\uD83D\uDE03");
        if(progress==1)
            painEmoji.setText("\uD83D\uDE42");
        if(progress==2)
            painEmoji.setText("\uD83D\uDE10");
        if(progress==3)
            painEmoji.setText("\uD83D\uDE15");
        if(progress==4)
            painEmoji.setText("☹️");
    }
    ArrayList<Integer> globalSelectedSymptomIds=new ArrayList<>();
    public void SelectSymptoms()
    {
        String[] symptomArray = this.getResources().getStringArray(R.array.symptoms);
        ArrayList<MultiSelectModel> listOfSymptoms= new ArrayList<>();
        for (int i=0;i<symptomArray.length;i++)
            listOfSymptoms.add(new MultiSelectModel(i, symptomArray[i]));
        MultiSelectDialog multiSelectDialog = new MultiSelectDialog()
                .title("Select your symptoms")
                .titleSize(25)
                .positiveText("Done")
                .negativeText("Cancel")
                .preSelectIDsList(globalSelectedSymptomIds) //List of ids that you need to be selected
                .multiSelectList(listOfSymptoms) // the multi select model list with ids and name
                .onSubmit(new MultiSelectDialog.SubmitCallbackListener() {
                    @Override
                    public void onSelected(ArrayList<Integer> selectedIds, ArrayList<String> selectedNames, String dataString) {
                        //will return list of selected IDS
                        globalSelectedSymptomIds=selectedIds;
                        symptoms=new ArrayList<>();
                        for (int i = 0; i < selectedIds.size(); i++) {
                            symptoms.add(selectedNames.get(i));
                        }
                    }
                    @Override
                    public void onCancel() {
                    }
                });
        multiSelectDialog.show(getSupportFragmentManager(), "multiSelectDialog");
    }
    ArrayList<Integer> globalSelectedMedicationIds=new ArrayList<>();
    public void SelectMedications()
    {
        String[] medicationArray = this.getResources().getStringArray(R.array.drugs);
        ArrayList<MultiSelectModel> listOfMedications= new ArrayList<>();
        for (int i=0;i<medicationArray.length;i++)
            listOfMedications.add(new MultiSelectModel(i,medicationArray[i]));

        MultiSelectDialog multiSelectDialog = new MultiSelectDialog()
                .title("Select your medications")
                .titleSize(25)
                .positiveText("Done")
                .negativeText("Cancel")
                .preSelectIDsList(globalSelectedMedicationIds) //List of ids that you need to be selected
                .multiSelectList(listOfMedications) // the multi select model list with ids and name
                .onSubmit(new MultiSelectDialog.SubmitCallbackListener() {
                    @Override
                    public void onSelected(ArrayList<Integer> selectedIds, ArrayList<String> selectedNames, String dataString) {
                        //will return list of selected IDS
                        globalSelectedMedicationIds=selectedIds;
                        medications=new ArrayList<>();
                        for (int i = 0; i < selectedIds.size(); i++) {
                            medications.add(selectedNames.get(i));
                        }
                    }
                    @Override
                    public void onCancel() {

                    }
                });
        multiSelectDialog.show(getSupportFragmentManager(), "multiSelectDialog");
    }
}
/*

    public void save(View view){
        String symptoms="";
        String date = "";
        String medications = "";
        int smoking=0;
        int alcohol = 0;
        int pain = 0;

        MedOpenHelper openHelper = MedOpenHelper.getInstance(getApplicationContext());
        SQLiteDatabase database = openHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Contract.User.COLUMN_SYMPTOMS, symptoms);
        contentValues.put(Contract.User.COLUMN_ALCOHOL, alcohol);
        contentValues.put(Contract.User.COLUMN_DATE, date);
        contentValues.put(Contract.User.COLUMN_MEDICATIONS, medications);
        contentValues.put(Contract.User.COLUMN_PAIN, pain);
        contentValues.put(Contract.User.COLUMN_SMOKING, smoking);

        long id = database.insert(Contract.User.TABLE_NAME, null, contentValues);


    }
}
*/