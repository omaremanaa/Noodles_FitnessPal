package com.example.noodles_fitnesspal;

public class TodayWorkoutClass {

    String name, reps;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReps() {
        return reps;
    }

    public void setReps(String reps) {
        this.reps = reps;
    }

    public TodayWorkoutClass() {
    }

    public TodayWorkoutClass(String name, String reps) {
        this.name = name;
        this.reps = reps;
    }
}
