package com.hackumass.med.medapp.Database;

/**
 * Created by Aryan Singh on 10/13/2018.
 */

public class User {

    String name,symptoms;
    int feeling,age;
    boolean smoking, drinking;

    public User(String name, String symptoms, int feeling, boolean smoking, boolean drinking,int age) {
        this.name = name;
        this.symptoms = symptoms;
        this.feeling = feeling;
        this.smoking = smoking;
        this.drinking = drinking;
        this.age = age;
    }
}
