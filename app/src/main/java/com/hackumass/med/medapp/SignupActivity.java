package com.hackumass.med.medapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.abdeveloper.library.MultiSelectDialog;
import com.abdeveloper.library.MultiSelectModel;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.hackumass.med.medapp.Database.MedOpenHelper;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class SignupActivity extends AppCompatActivity {

    TextView emailEditText;
    TextView passEditText;
    TextView ageEditText;
    RadioGroup sexGroup;
    Button conditionsButton;
    Button medicationsButton;
    RadioGroup smokeGroup;
    RadioGroup alcoholGroup;
    RadioGroup lifestyleGroup;
    SeekBar painSeekbar;
    TextView painEmoji;
    Button registerButton;
    ArrayList<String> medications = new ArrayList<>();
    ArrayList<String> conditions = new ArrayList<>();
    Pattern emailAddressRegex =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    FirebaseAuth auth;
    FirebaseAnalytics analytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        registerButton=findViewById(R.id.register);
        emailEditText=findViewById(R.id.email);
        passEditText=findViewById(R.id.password);
        sexGroup=findViewById(R.id.select_sex);
        conditionsButton=findViewById(R.id.select_conditions);

        auth = FirebaseAuth.getInstance();
        analytics = FirebaseAnalytics.getInstance(this);

        conditionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectConditions();
            }
        });
        medicationsButton=findViewById(R.id.select_medications);
        medicationsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectMedications();
            }
        });
        smokeGroup=findViewById(R.id.select_smoke);
        alcoholGroup=findViewById(R.id.select_alcohol);
        lifestyleGroup=findViewById(R.id.select_lifestyle);
        painSeekbar=findViewById(R.id.pain_seekbar);
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
        painEmoji=findViewById(R.id.pain_emoji);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a=RegistrationCheck();
                if(!a.isEmpty())
                    Toast.makeText(SignupActivity.this, a,
                            Toast.LENGTH_LONG).show();
                else
                {
                    Intent intent = new Intent(SignupActivity.this, Home3Activity.class);
                    intent.putExtra("email",email);
                    intent.putExtra("password",password);
                    intent.putExtra("age",age);
                    intent.putExtra("sex",sex);
                    intent.putExtra("smoke",smoke);
                    intent.putExtra("alcohol",alcohol);
                    intent.putExtra("lifestyle",lifestyle);
                    intent.putExtra("pain",painLevel);
                    intent.putExtra("from",true);
                    String medicationString="";
                    if (medications.size()>0) {
                        for (String medication : medications)
                            medicationString += medication+",";
                        medicationString = medicationString.substring(0, medicationString.length() - 1);
                    }
                    String conditionString="";
                    if (conditions.size()>0) {
                        for (String condition : conditions)
                            conditionString += condition+",";
                        conditionString = conditionString.substring(0, conditionString.length() - 1);
                    }
                    intent.putExtra("conditions",conditionString);
                    intent.putExtra("medications",medicationString);

                    startActivity(intent);
                }
            }
        });
        ageEditText=findViewById(R.id.age);
    }
    String email;
    String password;
    int age;
    int sex;
    int smoke;
    int alcohol;
    int lifestyle;
    int painLevel;
    public String RegistrationCheck()
    {
        if(emailEditText.getText().toString().length()>0&&isValidEmail(emailEditText.getText().toString()))
            email=emailEditText.getText().toString();
        else
            return "You must enter an email to continue";
        if(passEditText.getText().toString().length()>5)
            password=passEditText.getText().toString();
        else
            return "You must enter a password with 6 characters to continue";
        if(passEditText.getText().toString().length()>5)
            password=passEditText.getText().toString();
        else
            return "You must enter a password with 6 characters to continue";
        if(ageEditText.getText().toString().length()<3) {
            int possibleAge = Integer.valueOf(ageEditText.getText().toString());
            if(possibleAge<120)
                age=possibleAge;
            else
                return "Enter an age under 120 years";
        }
        else
            return "You must an age to continue";
        if (sexGroup.getCheckedRadioButtonId()!=-1) {
            int id=sexGroup.getCheckedRadioButtonId();
            RadioButton a = findViewById(id);
            sex=2;
            if(a.getText().toString().startsWith("M"))
                sex=0;
            else if (a.getText().toString().startsWith("F"))
                sex=1;
        }
        else
            return "You must choose a sex to continue";
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
        if (lifestyleGroup.getCheckedRadioButtonId()!=-1) {
            int id=smokeGroup.getCheckedRadioButtonId();
            RadioButton a = findViewById(id);
            lifestyle=2;
            if(a.getText().toString().startsWith("A"))
                sex=0;
            else if (a.getText().toString().startsWith("M"))
                sex=1;
        }
        else
            return "You must select a lifestyle to continue";
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

    ArrayList<Integer> globalSelectedConditionIds=new ArrayList<>();
    ArrayList<Integer> globalSelectedMedicationIds=new ArrayList<>();

    public void SelectConditions()
    {
        String[] conditionArray = this.getResources().getStringArray(R.array.conditions);
        ArrayList<MultiSelectModel> listOfConditions= new ArrayList<>();
        for (int i=0;i<conditionArray.length;i++)
            listOfConditions.add(new MultiSelectModel(i,conditionArray[i]));

        MultiSelectDialog multiSelectDialog = new MultiSelectDialog()
                .title("Select your conditions")
                .titleSize(25)
                .positiveText("Done")
                .negativeText("Cancel")
                .preSelectIDsList(globalSelectedConditionIds) //List of ids that you need to be selected
                .multiSelectList(listOfConditions) // the multi select model list with ids and name
                .onSubmit(new MultiSelectDialog.SubmitCallbackListener() {
                    @Override
                    public void onSelected(ArrayList<Integer> selectedIds, ArrayList<String> selectedNames, String dataString) {
                        //will return list of selected IDS
                        globalSelectedConditionIds=selectedIds;
                        for (int i = 0; i < selectedIds.size(); i++) {
                            conditions=new ArrayList<>();
                            conditions.add(selectedNames.get(i));
                        }
                    }
                    @Override
                    public void onCancel() {

                    }
                });
        multiSelectDialog.show(getSupportFragmentManager(), "multiSelectDialog");
    }
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
                        for (int i = 0; i < selectedIds.size(); i++) {
                            medications=new ArrayList<>();
                            medications.add(selectedNames.get(i));
                        }
                    }
                    @Override
                    public void onCancel() {

                    }
                });
        multiSelectDialog.show(getSupportFragmentManager(), "multiSelectDialog");
    }

    public boolean isValidEmail(String email) {
        String pattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    public void signInMethod(View view){
        Intent intent = new Intent(SignupActivity.this,MainActivity.class);
        startActivity(intent);
        finish();

    }
}
