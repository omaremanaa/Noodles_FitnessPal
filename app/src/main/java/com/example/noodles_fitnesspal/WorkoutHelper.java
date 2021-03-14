package com.example.noodles_fitnesspal;

public class WorkoutHelper {

    String height, weight, bmitype;

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

    public String getBmitype() {
        return bmitype;
    }

    public void setBmitype(String bmitype) {
        this.bmitype = bmitype;
    }

    public WorkoutHelper() {
    }

    public WorkoutHelper(String height, String weight, String bmitype) {
        this.height = height;
        this.weight = weight;
        this.bmitype = bmitype;
    }
}