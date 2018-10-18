package com.hackumass.med.medapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    EditText email;
    EditText password;

    String email1;
    Button login,share,learn;
    FirebaseAuth auth;
    FirebaseAnalytics analytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        email1 = email.getText().toString();
        login = findViewById(R.id.login);
        share = findViewById(R.id.share);
        learn = findViewById(R.id.learn);


        auth = FirebaseAuth.getInstance();
        analytics = FirebaseAnalytics.getInstance(this);

//        FirebaseUser user = auth.getCurrentUser();
//        if( user != null){
//            afterSignin();
//        }

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,
                        "Hey check out my app at: https://play.google.com/store/apps/details?id=com.google.android.apps.plus");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });

        learn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Login Please", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login.setEnabled(false);
                String emailString = email.getEditableText().toString();
                String pass = password.getEditableText().toString();

                analytics.logEvent("signin_clicked",null);

                auth.signInWithEmailAndPassword(emailString,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser user = auth.getCurrentUser();
                            Toast.makeText(MainActivity.this,user.getEmail(),Toast.LENGTH_LONG).show();
                            afterSignin();
                        }else {
                            login.setEnabled(true);
                            Log.e("LoginActivity",task.getException().getMessage());
                            Toast.makeText(MainActivity.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }

    private void afterSignin(){
        Intent intent = new Intent(this,Home3Activity.class);
        intent.putExtra("username", email1);
        startActivity(intent);
        finish();
    }

    public void SignupAct(View view){
        Intent intent = new Intent(this,SignupActivity.class);
        startActivity(intent);
        finish();
    }
}
