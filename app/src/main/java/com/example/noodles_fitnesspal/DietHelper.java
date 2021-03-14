package com.example.noodles_fitnesspal;

public class DietHelper {

    String height, weight, bmitype, foodtime;


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

    public String getFoodtime() {
        return foodtime;
    }

    public void setFoodtime(String foodtime) {
        this.foodtime = foodtime;
    }

    public DietHelper() {
    }

    public DietHelper(String height, String weight, String bmitype, String foodtime) {
        this.height = height;
        this.weight = weight;
        this.bmitype = bmitype;
        this.foodtime = foodtime;
    }
}