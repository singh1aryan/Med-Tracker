package com.hackumass.med.medapp.Database;

/**
 * Created by Aryan Singh on 10/13/2018.
 */

public class User {

    public String symptoms,medications,date;
    public int pain;
    public int smoking, alcohol;

    public User(String symptoms, int pain, int smoking, int alcohol, String medications,String date) {
        this.symptoms = symptoms;
        this.pain = pain;
        this.smoking = smoking;
        this.alcohol = alcohol;
        this.medications = medications;
        this.date = date;
    }
}
