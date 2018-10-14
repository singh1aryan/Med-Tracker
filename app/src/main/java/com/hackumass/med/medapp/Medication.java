package com.hackumass.med.medapp;


public class Medication {
    private String brandName;
    private int dosage;
    private int frequency;
    private boolean perDay;
    public Medication(String name, int dose, int freq, boolean day)
    {
        this.brandName=name;
        this.dosage=dose;
        this.frequency=freq;
        this.perDay=day;
    }
    public int getDosage() {
        return dosage;
    }
    public String getBrandName() {
        return brandName;
    }
    public boolean isPerDay() {
        return perDay;
    }
    public int getFrequency() {
        return frequency;
    }
}