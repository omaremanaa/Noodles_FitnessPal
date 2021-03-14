package com.example.noodles_fitnesspal;

public class TypeFoodHelper {

    String calorie, name, image;

    public String getCalorie() {
        return calorie;
    }

    public void setCalorie(String calorie) {
        this.calorie = calorie;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public TypeFoodHelper() {
    }

    public TypeFoodHelper(String calorie, String name, String image) {
        this.calorie = calorie;
        this.name = name;
        this.image = image;
    }
}