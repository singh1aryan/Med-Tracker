package com.hackumass.med.medapp;
import java.util.ArrayList;
public class History {
    private String date;
    //yyyy-mm-dd
    private int painLevel;
    private ArrayList<String> symptoms;
    private ArrayList<Medication> medications;
    public History(String date, int pain, ArrayList<String> symptoms, ArrayList<Medication> medications)
    {
        this.date=date;
        this.painLevel=pain;
        this.symptoms=symptoms;
        this.medications=medications;
    }
    public ArrayList<Medication> getMedications() {
        return medications;
    }
    public ArrayList<String> getSymptoms() {
        return symptoms;
    }
    public int getPainLevel() {
        return painLevel;
    }
    public String getDate() {
        return date;
    }
}
