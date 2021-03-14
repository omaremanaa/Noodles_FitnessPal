package com.example.noodles_fitnesspal;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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

public class HomeFragment extends Fragment {
    TextView accountname, workouthome, caloriehome;
    FirebaseUser user;
    DatabaseReference reference, referencediet, referencework;
    String userID;
    Activity mainmenu;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Home Menu");
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM d, yyyy");
        String currentDateandTime = sdf.format(new Date());
        reference = FirebaseDatabase.getInstance().getReference("Users");
        referencediet = FirebaseDatabase.getInstance().getReference("Diet");
        referencework = FirebaseDatabase.getInstance().getReference("Workout");
        user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();
        mainmenu = getActivity();
        workouthome = v.findViewById(R.id.textViewimpossible2);
        caloriehome = v.findViewById(R.id.textViewimpossible3);
        TextView tv = (TextView) v.findViewById(R.id.setDate);
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        tv.setText(currentDateandTime);

        referencediet.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String calorieIntake = String.valueOf(snapshot.child(userID).child("calorieIntake").getValue());
                if (calorieIntake != null) {
                    caloriehome.setText(calorieIntake +"\nMaximum Calorie Intake");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        referencework.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String workoutbmi = String.valueOf(snapshot.child(userID).child("bmitype").getValue());
                switch (day) {
                    case Calendar.MONDAY:
                        if (workoutbmi.equals("Normal")) {
                            String value = String.valueOf(snapshot.child("Normal").child("Day 1").getChildrenCount());
                            workouthome.setText(value + " Workout Today");
                        } else if (workoutbmi.equals("Underweight")) {
                            String value = String.valueOf(snapshot.child("Underweight").child("Day 1").getChildrenCount());
                            workouthome.setText(value + " Workout Today");
                        } else if (workoutbmi.equals("Overweight")) {
                            String value = String.valueOf(snapshot.child("Overweight").child("Day 1").getChildrenCount());
                            workouthome.setText(value + " Workout Today");
                        } else if (workoutbmi.equals("Obese")) {
                            String value = String.valueOf(snapshot.child("Obese").child("Day 1").getChildrenCount() + " ");
                            workouthome.setText(value + " Workout Today");
                        }
                        break;
                    case Calendar.TUESDAY:
                        if (workoutbmi.equals("Normal")) {
                            String value = String.valueOf(snapshot.child("Normal").child("Day 2").getChildrenCount());
                            workouthome.setText(value + " Workout Today");
                        } else if (workoutbmi.equals("Underweight")) {
                            String value = String.valueOf(snapshot.child("Underweight").child("Day 2").getChildrenCount());
                            workouthome.setText(value + " Workout Today");
                        } else if (workoutbmi.equals("Overweight")) {
                            String value = String.valueOf(snapshot.child("Overweight").child("Day 2").getChildrenCount());
                            workouthome.setText(value + " Workout Today");
                        } else if (workoutbmi.equals("Obese")) {
                            String value = String.valueOf(snapshot.child("Obese").child("Day 2").getChildrenCount());
                            workouthome.setText(value + " Workout Today");
                        }
                        break;
                    case Calendar.WEDNESDAY:
                        if (workoutbmi.equals("Normal")) {
                            String value = String.valueOf(snapshot.child("Normal").child("Day 3").getChildrenCount());
                            workouthome.setText(value + " Workout Today");
                        } else if (workoutbmi.equals("Underweight")) {
                            String value = String.valueOf(snapshot.child("Underweight").child("Day 3").getChildrenCount());
                            workouthome.setText(value + " Workout Today");
                        } else if (workoutbmi.equals("Overweight")) {
                            String value = String.valueOf(snapshot.child("Overweight").child("Day 3").getChildrenCount());
                            workouthome.setText(value + " Workout Today");
                        } else if (workoutbmi.equals("Obese")) {
                            String value = String.valueOf(snapshot.child("Obese").child("Day 3").getChildrenCount());
                            workouthome.setText(value + " Workout Today");
                        }
                        break;
                    case Calendar.THURSDAY:
                        if (workoutbmi.equals("Normal")) {
                            String value = String.valueOf(snapshot.child("Normal").child("Day 4").getChildrenCount());
                            workouthome.setText(value + " Workout Today");
                        } else if (workoutbmi.equals("Underweight")) {
                            String value = String.valueOf(snapshot.child("Underweight").child("Day 4").getChildrenCount());
                            workouthome.setText(value + " Workout Today");
                        } else if (workoutbmi.equals("Overweight")) {
                            String value = String.valueOf(snapshot.child("Overweight").child("Day 4").getChildrenCount());
                            workouthome.setText(value + " Workout Today");
                        } else if (workoutbmi.equals("Obese")) {
                            String value = String.valueOf(snapshot.child("Obese").child("Day 4").getChildrenCount());
                            workouthome.setText(value + " Workout Today");
                        }
                        break;
                    case Calendar.FRIDAY:
                        if (workoutbmi.equals("Normal")) {
                            String value = String.valueOf(snapshot.child("Normal").child("Day 5").getChildrenCount());
                            workouthome.setText(value + " Workout Today");
                        } else if (workoutbmi.equals("Underweight")) {
                            String value = String.valueOf(snapshot.child("Underweight").child("Day 5").getChildrenCount());
                            workouthome.setText(value + " Workout Today");
                        } else if (workoutbmi.equals("Overweight")) {
                            String value = String.valueOf(snapshot.child("Overweight").child("Day 5").getChildrenCount());
                            workouthome.setText(value + " Workout Today");
                        } else if (workoutbmi.equals("Obese")) {
                            String value = String.valueOf(snapshot.child("Obese").child("Day 5").getChildrenCount());
                            workouthome.setText(value + " Workout Today");
                        }
                        break;
                    default:
                        workouthome.setText("No Workouts Today");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        accountname = (TextView) v.findViewById(R.id.AccountNameHome);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserHelper userHelper = snapshot.getValue(UserHelper.class);
                if (userHelper != null) {
                    String name = userHelper.name;
                    accountname.setText("Welcome, " + name + "!");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(mainmenu, "Something wrong happened", Toast.LENGTH_LONG).show();
            }
        });
        return v;
    }
}
