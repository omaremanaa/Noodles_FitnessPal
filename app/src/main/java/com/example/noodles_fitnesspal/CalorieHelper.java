package com.example.noodles_fitnesspal;

public class CalorieHelper {

    String Calorie, Carbs, Protein, foodname, image;

    public String getCalorie() {
        return Calorie;
    }

    public void setCalorie(String calorie) {
        Calorie = calorie;
    }

    public String getCarbs() {
        return Carbs;
    }

    public void setCarbs(String carbs) {
        Carbs = carbs;
    }

    public String getProtein() {
        return Protein;
    }

    public void setProtein(String protein) {
        Protein = protein;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public CalorieHelper() {
    }

    public CalorieHelper(String calorie, String carbs, String protein, String foodname, String image) {
        Calorie = calorie;
        Carbs = carbs;
        Protein = protein;
        this.foodname = foodname;
        this.image = image;
    }
}