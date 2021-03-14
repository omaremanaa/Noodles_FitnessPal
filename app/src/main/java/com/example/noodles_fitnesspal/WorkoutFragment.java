package com.example.noodles_fitnesspal;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class WorkoutFragment extends Fragment {
    TextView DayWorkout;
    TextView workouts, firstTrain, secondTrain, cardio;
    Button showfull;
    FirebaseUser user;
    DatabaseReference reference;
    String userID, data;
    private static final String TAG = "MyActivity";

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Workout");
        View v = inflater.inflate(R.layout.fragment_workout, container, false);
        SimpleDateFormat sdf = new SimpleDateFormat("E");
        String currentDay = sdf.format(new Date());

        DayWorkout = v.findViewById(R.id.ClickTextView);

        TextView tv = (TextView) v.findViewById(R.id.setDateWorkout);
        workouts = v.findViewById(R.id.textViewimpossible);
        firstTrain = v.findViewById(R.id.firstTrain);
        secondTrain = v.findViewById(R.id.SecondTrain);
        cardio = v.findViewById(R.id.CardioWorkout);
        showfull = v.findViewById(R.id.fullweekbutton);

        tv.setText("Today's Workout (" + currentDay + "):");
        user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        reference = FirebaseDatabase.getInstance().getReference("Workout");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String bmizzz = String.valueOf(snapshot.child(userID).child("bmitype").getValue());

                switch (day) {
                    case Calendar.MONDAY:
                        if (bmizzz.equals("Normal")) {
                            String value = String.valueOf(snapshot.child("Normal").child("Day 1").getChildrenCount() + " ");
                            workouts.setText(value + " Workouts Today");

                            String value1 = String.valueOf(snapshot.child("Normal").child("Day 1").child("01").child("name").getValue());
                            String valuereps = String.valueOf(snapshot.child("Normal").child("Day 1").child("01").child("reps").getValue());
                            firstTrain.setText(valuereps + " " + value1);
                            String value2 = String.valueOf(snapshot.child("Normal").child("Day 1").child("02").child("name").getValue());
                            String valuereps2 = String.valueOf(snapshot.child("Normal").child("Day 1").child("02").child("reps").getValue());
                            secondTrain.setText(valuereps2 + " " + value2);
                            String value3 = String.valueOf(snapshot.child("Normal").child("Day 1").child("06").child("reps").getValue());
                            cardio.setText(value3 + " minutes");
                            if (firstTrain==null){
                                firstTrain.setText("No Workout");
                            }
                            if (secondTrain==null){
                                secondTrain.setText("Stay Healthy");
                            }
                        } else if (bmizzz.equals("Underweight")) {
                            String value = String.valueOf(snapshot.child("Underweight").child("Day 1").getChildrenCount() + " ");
                            workouts.setText(value + " Workouts Today");
                            String value1 = String.valueOf(snapshot.child("Underweight").child("Day 1").child("01").child("name").getValue());
                            String valuereps = String.valueOf(snapshot.child("Underweight").child("Day 1").child("01").child("reps").getValue());
                            firstTrain.setText(valuereps + " " + value1);
                            String value2 = String.valueOf(snapshot.child("Underweight").child("Day 1").child("02").child("name").getValue());
                            String valuereps2 = String.valueOf(snapshot.child("Underweight").child("Day 1").child("02").child("reps").getValue());
                            secondTrain.setText(valuereps2 + " " + value2);
                            String value3 = String.valueOf(snapshot.child("Underweight").child("Day 1").child("06").child("reps").getValue());
                            cardio.setText(value3 + " minutes");
                            if (firstTrain==null){
                                firstTrain.setText("No Workout");
                            }
                            if (secondTrain==null){
                                secondTrain.setText("Stay Healthy");
                            }
                        } else if (bmizzz.equals("Overweight")) {
                            String value = String.valueOf(snapshot.child("Overweight").child("Day 1").getChildrenCount() + " ");
                            workouts.setText(value + " Workouts Today");
                            String value1 = String.valueOf(snapshot.child("Overweight").child("Day 1").child("01").child("name").getValue());
                            String valuereps = String.valueOf(snapshot.child("Overweight").child("Day 1").child("01").child("reps").getValue());
                            firstTrain.setText(valuereps + " " + value1);
                            String value2 = String.valueOf(snapshot.child("Overweight").child("Day 1").child("02").child("name").getValue());
                            String valuereps2 = String.valueOf(snapshot.child("Overweight").child("Day 1").child("02").child("reps").getValue());
                            secondTrain.setText(valuereps2 + " " + value2);
                            String value3 = String.valueOf(snapshot.child("Overweight").child("Day 1").child("06").child("reps").getValue());
                            cardio.setText(value3 + " minutes");
                            if (firstTrain==null){
                                firstTrain.setText("No Workout");
                            }
                            if (secondTrain==null){
                                secondTrain.setText("Stay Healthy");
                            }
                        } else if (bmizzz.equals("Obese")) {
                            String value = String.valueOf(snapshot.child("Obese").child("Day 1").getChildrenCount() + " ");
                            workouts.setText(value + " Workouts Today");
                            String value1 = String.valueOf(snapshot.child("Obese").child("Day 1").child("01").child("name").getValue());
                            String valuereps = String.valueOf(snapshot.child("Obese").child("Day 1").child("01").child("reps").getValue());
                            firstTrain.setText(valuereps + " " + value1);
                            String value2 = String.valueOf(snapshot.child("Obese").child("Day 1").child("02").child("name").getValue());
                            String valuereps2 = String.valueOf(snapshot.child("Obese").child("Day 1").child("02").child("reps").getValue());
                            secondTrain.setText(valuereps2 + " " + value2);
                            String value3 = String.valueOf(snapshot.child("Obese").child("Day 1").child("06").child("reps").getValue());
                            cardio.setText(value3 + " minutes");
                            if (firstTrain==null){
                                firstTrain.setText("No Workout");
                            }
                            if (secondTrain==null){
                                secondTrain.setText("Stay Healthy");
                            }
                        }
                        break;
                    case Calendar.TUESDAY:
                        if (bmizzz.equals("Normal")) {
                            String value = String.valueOf(snapshot.child("Normal").child("Day 2").getChildrenCount() + " ");
                            workouts.setText(value + " Workouts Today");
                            String value1 = String.valueOf(snapshot.child("Normal").child("Day 2").child("01").child("name").getValue());
                            String valuereps = String.valueOf(snapshot.child("Normal").child("Day 2").child("01").child("reps").getValue());
                            firstTrain.setText(valuereps + " " + value1);
                            String value2 = String.valueOf(snapshot.child("Normal").child("Day 2").child("02").child("name").getValue());
                            String valuereps2 = String.valueOf(snapshot.child("Normal").child("Day 2").child("02").child("reps").getValue());
                            secondTrain.setText(valuereps2 + " " + value2);
                            String value3 = String.valueOf(snapshot.child("Normal").child("Day 2").child("06").child("reps").getValue());
                            cardio.setText(value3 + " minutes");
                            if (firstTrain==null){
                                firstTrain.setText("No Workout");
                            }
                            if (secondTrain==null){
                                secondTrain.setText("Stay Healthy");
                            }
                        } else if (bmizzz.equals("Underweight")) {
                            String value = String.valueOf(snapshot.child("Underweight").child("Day 2").getChildrenCount() + " ");
                            workouts.setText(value + " Workouts Today");
                            String value1 = String.valueOf(snapshot.child("Underweight").child("Day 2").child("01").child("name").getValue());
                            String valuereps = String.valueOf(snapshot.child("Underweight").child("Day 2").child("01").child("reps").getValue());
                            firstTrain.setText(valuereps + " " + value1);
                            String value2 = String.valueOf(snapshot.child("Underweight").child("Day 2").child("02").child("name").getValue());
                            String valuereps2 = String.valueOf(snapshot.child("Underweight").child("Day 2").child("02").child("reps").getValue());
                            secondTrain.setText(valuereps2 + " " + value2);
                            String value3 = String.valueOf(snapshot.child("Underweight").child("Day 2").child("06").child("reps").getValue());
                            cardio.setText(value3 + " minutes");
                            if (firstTrain==null){
                                firstTrain.setText("No Workout");
                            }
                            if (secondTrain==null){
                                secondTrain.setText("Stay Healthy");
                            }
                        } else if (bmizzz.equals("Overweight")) {
                            String value = String.valueOf(snapshot.child("Overweight").child("Day 2").getChildrenCount() + " ");
                            workouts.setText(value + " Workouts Today");
                            String value1 = String.valueOf(snapshot.child("Overweight").child("Day 2").child("01").child("name").getValue());
                            String valuereps = String.valueOf(snapshot.child("Overweight").child("Day 2").child("01").child("reps").getValue());
                            firstTrain.setText(valuereps + " " + value1);
                            String value2 = String.valueOf(snapshot.child("Overweight").child("Day 2").child("02").child("name").getValue());
                            String valuereps2 = String.valueOf(snapshot.child("Overweight").child("Day 2").child("02").child("reps").getValue());
                            secondTrain.setText(valuereps2 + " " + value2);
                            String value3 = String.valueOf(snapshot.child("Overweight").child("Day 2").child("06").child("reps").getValue());
                            cardio.setText(value3 + " minutes");
                            if (firstTrain==null){
                                firstTrain.setText("No Workout");
                            }
                            if (secondTrain==null){
                                secondTrain.setText("Stay Healthy");
                            }
                        } else if (bmizzz.equals("Obese")) {
                            String value = String.valueOf(snapshot.child("Obese").child("Day 2").getChildrenCount() + " ");
                            workouts.setText(value + " Workouts Today");
                            String value1 = String.valueOf(snapshot.child("Obese").child("Day 2").child("01").child("name").getValue());
                            String valuereps = String.valueOf(snapshot.child("Obese").child("Day 2").child("01").child("reps").getValue());
                            firstTrain.setText(valuereps + " " + value1);
                            String value2 = String.valueOf(snapshot.child("Obese").child("Day 2").child("02").child("name").getValue());
                            String valuereps2 = String.valueOf(snapshot.child("Obese").child("Day 2").child("02").child("reps").getValue());
                            secondTrain.setText(valuereps2 + " " + value2);
                            String value3 = String.valueOf(snapshot.child("Obese").child("Day 2").child("06").child("reps").getValue());
                            cardio.setText(value3 + " minutes");
                            if (firstTrain==null){
                                firstTrain.setText("No Workout");
                            }
                            if (secondTrain==null){
                                secondTrain.setText("Stay Healthy");
                            }
                        }
                        break;
                    case Calendar.WEDNESDAY:
                        if (bmizzz.equals("Normal")) {
                            String value = String.valueOf(snapshot.child("Normal").child("Day 3").getChildrenCount() + " ");
                            workouts.setText(value + " Workouts Today");
                            String value1 = String.valueOf(snapshot.child("Normal").child("Day 3").child("01").child("name").getValue());
                            String valuereps = String.valueOf(snapshot.child("Normal").child("Day 3").child("01").child("reps").getValue());
                            firstTrain.setText(valuereps + " " + value1);
                            String value2 = String.valueOf(snapshot.child("Normal").child("Day 3").child("02").child("name").getValue());
                            String valuereps2 = String.valueOf(snapshot.child("Normal").child("Day 3").child("02").child("reps").getValue());
                            secondTrain.setText(valuereps2 + " " + value2);
                            String value3 = String.valueOf(snapshot.child("Normal").child("Day 3").child("06").child("reps").getValue());
                            cardio.setText(value3 + " minutes");
                            if (firstTrain==null){
                                firstTrain.setText("No Workout");
                            }
                            if (secondTrain==null){
                                secondTrain.setText("Stay Healthy");
                            }
                        } else if (bmizzz.equals("Underweight")) {
                            String value = String.valueOf(snapshot.child("Underweight").child("Day 3").getChildrenCount() + " ");
                            workouts.setText(value + " Workouts Today");
                            String value1 = String.valueOf(snapshot.child("Underweight").child("Day 3").child("01").child("name").getValue());
                            String valuereps = String.valueOf(snapshot.child("Underweight").child("Day 3").child("01").child("reps").getValue());
                            firstTrain.setText(valuereps + " " + value1);
                            String value2 = String.valueOf(snapshot.child("Underweight").child("Day 3").child("02").child("name").getValue());
                            String valuereps2 = String.valueOf(snapshot.child("Underweight").child("Day 3").child("02").child("reps").getValue());
                            secondTrain.setText(valuereps2 + " " + value2);
                            String value3 = String.valueOf(snapshot.child("Underweight").child("Day 3").child("06").child("reps").getValue());
                            cardio.setText(value3 + " minutes");
                            if (firstTrain==null){
                                firstTrain.setText("No Workout");
                            }
                            if (secondTrain==null){
                                secondTrain.setText("Stay Healthy");
                            }
                        } else if (bmizzz.equals("Overweight")) {
                            String value = String.valueOf(snapshot.child("Overweight").child("Day 3").getChildrenCount() + " ");
                            workouts.setText(value + " Workouts Today");
                            String value1 = String.valueOf(snapshot.child("Overweight").child("Day 3").child("01").child("name").getValue());
                            String valuereps = String.valueOf(snapshot.child("Overweight").child("Day 3").child("01").child("reps").getValue());
                            firstTrain.setText(valuereps + " " + value1);
                            String value2 = String.valueOf(snapshot.child("Overweight").child("Day 3").child("02").child("name").getValue());
                            String valuereps2 = String.valueOf(snapshot.child("Overweight").child("Day 3").child("02").child("reps").getValue());
                            secondTrain.setText(valuereps2 + " " + value2);
                            String value3 = String.valueOf(snapshot.child("Overweight").child("Day 3").child("06").child("reps").getValue());
                            cardio.setText(value3 + " minutes");
                            if (firstTrain==null){
                                firstTrain.setText("No Workout");
                            }
                            if (secondTrain==null){
                                secondTrain.setText("Stay Healthy");
                            }
                        } else if (bmizzz.equals("Obese")) {
                            String value = String.valueOf(snapshot.child("Obese").child("Day 3").getChildrenCount() + " ");
                            workouts.setText(value + " Workouts Today");
                            String value1 = String.valueOf(snapshot.child("Obese").child("Day 3").child("01").child("name").getValue());
                            String valuereps = String.valueOf(snapshot.child("Obese").child("Day 3").child("01").child("reps").getValue());
                            firstTrain.setText(valuereps + " " + value1);
                            String value2 = String.valueOf(snapshot.child("Obese").child("Day 3").child("02").child("name").getValue());
                            String valuereps2 = String.valueOf(snapshot.child("Obese").child("Day 3").child("02").child("reps").getValue());
                            secondTrain.setText(valuereps2 + " " + value2);
                            String value3 = String.valueOf(snapshot.child("Obese").child("Day 3").child("06").child("reps").getValue());
                            cardio.setText(value3 + " minutes");
                            if (firstTrain==null){
                                firstTrain.setText("No Workout");
                            }
                            if (secondTrain==null){
                                secondTrain.setText("Stay Healthy");
                            }
                        }
                        break;
                    case Calendar.THURSDAY:
                        if (bmizzz.equals("Normal")) {
                            String value = String.valueOf(snapshot.child("Normal").child("Day 4").getChildrenCount() + " ");
                            workouts.setText(value + " Workouts Today");
                            String value1 = String.valueOf(snapshot.child("Normal").child("Day 4").child("01").child("name").getValue());
                            String valuereps = String.valueOf(snapshot.child("Normal").child("Day 4").child("01").child("reps").getValue());
                            firstTrain.setText(valuereps + " " + value1);
                            String value2 = String.valueOf(snapshot.child("Normal").child("Day 4").child("02").child("name").getValue());
                            String valuereps2 = String.valueOf(snapshot.child("Normal").child("Day 4").child("02").child("reps").getValue());
                            secondTrain.setText(valuereps2 + " " + value2);
                            String value3 = String.valueOf(snapshot.child("Normal").child("Day 4").child("06").child("reps").getValue());
                            cardio.setText(value3 + " minutes");
                            if (firstTrain==null){
                                firstTrain.setText("No Workout");
                            }
                            if (secondTrain==null){
                                secondTrain.setText("Stay Healthy");
                            }
                        } else if (bmizzz.equals("Underweight")) {
                            String value = String.valueOf(snapshot.child("Underweight").child("Day 4").getChildrenCount() + " ");
                            workouts.setText(value + " Workouts Today");
                            String value1 = String.valueOf(snapshot.child("Underweight").child("Day 4").child("01").child("name").getValue());
                            String valuereps = String.valueOf(snapshot.child("Underweight").child("Day 4").child("01").child("reps").getValue());
                            firstTrain.setText(valuereps + " " + value1);
                            String value2 = String.valueOf(snapshot.child("Underweight").child("Day 4").child("02").child("name").getValue());
                            String valuereps2 = String.valueOf(snapshot.child("Underweight").child("Day 4").child("02").child("reps").getValue());
                            secondTrain.setText(valuereps2 + " " + value2);
                            String value3 = String.valueOf(snapshot.child("Underweight").child("Day 4").child("06").child("reps").getValue());
                            cardio.setText(value3 + " minutes");
                            if (firstTrain==null){
                                firstTrain.setText("No Workout");
                            }
                            if (secondTrain==null){
                                secondTrain.setText("Stay Healthy");
                            }
                        } else if (bmizzz.equals("Overweight")) {
                            String value = String.valueOf(snapshot.child("Overweight").child("Day 4").getChildrenCount() + " ");
                            workouts.setText(value + " Workouts Today");
                            String value1 = String.valueOf(snapshot.child("Overweight").child("Day 4").child("01").child("name").getValue());
                            String valuereps = String.valueOf(snapshot.child("Overweight").child("Day 4").child("01").child("reps").getValue());
                            firstTrain.setText(valuereps + " " + value1);
                            String value2 = String.valueOf(snapshot.child("Overweight").child("Day 4").child("02").child("name").getValue());
                            String valuereps2 = String.valueOf(snapshot.child("Overweight").child("Day 4").child("02").child("reps").getValue());
                            secondTrain.setText(valuereps2 + " " + value2);
                            String value3 = String.valueOf(snapshot.child("Overweight").child("Day 4").child("06").child("reps").getValue());
                            cardio.setText(value3 + " minutes");
                            if (firstTrain==null){
                                firstTrain.setText("No Workout");
                            }
                            if (secondTrain==null){
                                secondTrain.setText("Stay Healthy");
                            }
                        } else if (bmizzz.equals("Obese")) {
                            String value = String.valueOf(snapshot.child("Obese").child("Day 4").getChildrenCount() + " ");
                            workouts.setText(value + " Workouts Today");
                            String value1 = String.valueOf(snapshot.child("Obese").child("Day 4").child("01").child("name").getValue());
                            String valuereps = String.valueOf(snapshot.child("Obese").child("Day 4").child("01").child("reps").getValue());
                            firstTrain.setText(valuereps + " " + value1);
                            String value2 = String.valueOf(snapshot.child("Obese").child("Day 4").child("02").child("name").getValue());
                            String valuereps2 = String.valueOf(snapshot.child("Obese").child("Day 4").child("02").child("reps").getValue());
                            secondTrain.setText(valuereps2 + " " + value2);
                            String value3 = String.valueOf(snapshot.child("Obese").child("Day 4").child("06").child("reps").getValue());
                            cardio.setText(value3 + " minutes");
                            if (firstTrain==null){
                                firstTrain.setText("No Workout");
                            }
                            if (secondTrain==null){
                                secondTrain.setText("Stay Healthy");
                            }
                        }
                        break;
                    case Calendar.FRIDAY:
                        if (bmizzz.equals("Normal")) {
                            String value = String.valueOf(snapshot.child("Normal").child("Day 5").getChildrenCount() + " ");
                            workouts.setText(value + " Workouts Today");
                            String value1 = String.valueOf(snapshot.child("Normal").child("Day 5").child("01").child("name").getValue());
                            String valuereps = String.valueOf(snapshot.child("Normal").child("Day 5").child("01").child("reps").getValue());
                            firstTrain.setText(valuereps + " " + value1);
                            String value2 = String.valueOf(snapshot.child("Normal").child("Day 5").child("02").child("name").getValue());
                            String valuereps2 = String.valueOf(snapshot.child("Normal").child("Day 5").child("02").child("reps").getValue());
                            secondTrain.setText(valuereps2 + " " + value2);
                            String value3 = String.valueOf(snapshot.child("Normal").child("Day 5").child("06").child("reps").getValue());
                            cardio.setText(value3 + " minutes");
                            if (firstTrain==null){
                                firstTrain.setText("No Workout");
                            }
                            if (secondTrain==null){
                                secondTrain.setText("Stay Healthy");
                            }
                        } else if (bmizzz.equals("Underweight")) {
                            String value = String.valueOf(snapshot.child("Underweight").child("Day 5").getChildrenCount() + " ");
                            workouts.setText(value + " Workouts Today");
                            String value1 = String.valueOf(snapshot.child("Underweight").child("Day 5").child("01").child("name").getValue());
                            String valuereps = String.valueOf(snapshot.child("Underweight").child("Day 5").child("01").child("reps").getValue());
                            firstTrain.setText(valuereps + " " + value1);
                            String value2 = String.valueOf(snapshot.child("Underweight").child("Day 5").child("02").child("name").getValue());
                            String valuereps2 = String.valueOf(snapshot.child("Underweight").child("Day 5").child("02").child("reps").getValue());
                            secondTrain.setText(valuereps2 + " " + value2);
                            String value3 = String.valueOf(snapshot.child("Underweight").child("Day 5").child("06").child("reps").getValue());
                            cardio.setText(value3 + " minutes");
                            if (firstTrain==null){
                                firstTrain.setText("No Workout");
                            }
                            if (secondTrain==null){
                                secondTrain.setText("Stay Healthy");
                            }
                        } else if (bmizzz.equals("Overweight")) {
                            String value = String.valueOf(snapshot.child("Overweight").child("Day 5").getChildrenCount() + " ");
                            workouts.setText(value + " Workouts Today");
                            String value1 = String.valueOf(snapshot.child("Overweight").child("Day 5").child("01").child("name").getValue());
                            String valuereps = String.valueOf(snapshot.child("Overweight").child("Day 5").child("01").child("reps").getValue());
                            firstTrain.setText(valuereps + " " + value1);
                            String value2 = String.valueOf(snapshot.child("Overweight").child("Day 5").child("02").child("name").getValue());
                            String valuereps2 = String.valueOf(snapshot.child("Overweight").child("Day 5").child("02").child("reps").getValue());
                            secondTrain.setText(valuereps2 + " " + value2);
                            String value3 = String.valueOf(snapshot.child("Overweight").child("Day 5").child("06").child("reps").getValue());
                            cardio.setText(value3 + " minutes");
                            if (firstTrain==null){
                                firstTrain.setText("No Workout");
                            }
                            if (secondTrain==null){
                                secondTrain.setText("Stay Healthy");
                            }
                        } else if (bmizzz.equals("Obese")) {
                            String value = String.valueOf(snapshot.child("Obese").child("Day 5").getChildrenCount() + " ");
                            workouts.setText(value + " Workouts Today");
                            String value1 = String.valueOf(snapshot.child("Obese").child("Day 5").child("01").child("name").getValue());
                            String valuereps = String.valueOf(snapshot.child("Obese").child("Day 5").child("01").child("reps").getValue());
                            firstTrain.setText(valuereps + " " + value1);
                            String value2 = String.valueOf(snapshot.child("Obese").child("Day 5").child("02").child("name").getValue());
                            String valuereps2 = String.valueOf(snapshot.child("Obese").child("Day 5").child("02").child("reps").getValue());
                            secondTrain.setText(valuereps2 + " " + value2);
                            String value3 = String.valueOf(snapshot.child("Obese").child("Day 5").child("06").child("reps").getValue());
                            cardio.setText(value3 + " minutes");
                            if (firstTrain==null){
                                firstTrain.setText("No Workout");
                            }
                            if (secondTrain==null){
                                secondTrain.setText("Stay Healthy");
                            }
                        }
                        break;
                    default:
                        workouts.setText("YAY! Free Day");
                        firstTrain.setText("No Workout");
                        secondTrain.setText("Stay Healthy");
                        cardio.setText("Have A Nice Weekend");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String bmizzz = String.valueOf(snapshot.child(userID).child("bmitype").getValue());
                if (bmizzz.equals("Normal")) {
                    DayWorkout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(getActivity(), NormalWorkoutActivity.class));
                        }
                    });
                }
                else if (bmizzz.equals("Underweight")){
                        DayWorkout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(getActivity(), UnderweightWorkoutActivity.class));
                            }
                        });
                    }
                else if (bmizzz.equals("Overweight")){
                    DayWorkout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(getActivity(), OverweightWorkoutActivity.class));
                        }
                    });
                }
                else if (bmizzz.equals("Obese")){
                    DayWorkout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(getActivity(), ObeseWorkoutActivity.class));
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        showfull.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), FullWeekWorkout.class));
            }
        });

        return v;
    }
}
