package com.example.noodles_fitnesspal;

public class BmiHelper {

    String height;
    String weight;
    String bmi;
    String bmitype;

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getBmi() {
        return bmi;
    }

    public void setBmi(String bmi) {
        this.bmi = bmi;
    }

    public String getBmitype() {
        return bmitype;
    }

    public void setBmitype(String bmitype) {
        this.bmitype = bmitype;
    }

    public BmiHelper() {
    }

    public BmiHelper(String height, String weight, String bmi, String bmitype) {
        this.height = height;
        this.weight = weight;
        this.bmi = bmi;
        this.bmitype = bmitype;
    }
}