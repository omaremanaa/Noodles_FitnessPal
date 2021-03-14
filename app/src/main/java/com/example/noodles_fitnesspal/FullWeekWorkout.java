package com.example.noodles_fitnesspal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ExpandableListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FullWeekWorkout extends AppCompatActivity {
    ExpandableListView expandableListView;
    List<String> listGroup;
    HashMap<String, List<String>> listItem;
    MainAdapter adapter;
    FirebaseUser user;
    String userID;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_week_workout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Full Week Workout");
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        expandableListView = findViewById(R.id.WorkoutList);
        listGroup = new ArrayList<>();
        listItem = new HashMap<>();
        adapter = new MainAdapter(this, listGroup, listItem);
        expandableListView.setAdapter(adapter);
        user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();
        reference = FirebaseDatabase.getInstance().getReference("Workout");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String bmizzz = String.valueOf(snapshot.child(userID).child("bmitype").getValue());
                if (bmizzz.equals("Underweight")) {
                    UnderweightData();
                } else if (bmizzz.equals("Normal")) {
                    NormalData();
                } else if (bmizzz.equals("Overweight")) {
                    OverweightData();
                } else if (bmizzz.equals("Obese")) {
                    ObeseData();
                } else {
                    return;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void UnderweightData() {
        listGroup.add(getString(R.string.day1));
        listGroup.add(getString(R.string.day2));
        listGroup.add(getString(R.string.day3));
        listGroup.add(getString(R.string.day4));
        listGroup.add(getString(R.string.day5));
        String[] array;

        List<String> list1 = new ArrayList<>();
        array = getResources().getStringArray(R.array.day1Underweight);
        for (String item : array) {
            list1.add(item);
        }
        List<String> list2 = new ArrayList<>();
        array = getResources().getStringArray(R.array.day2Underweight);
        for (String item : array) {
            list2.add(item);
        }
        List<String> list3 = new ArrayList<>();
        array = getResources().getStringArray(R.array.day3Underweight);
        for (String item : array) {
            list3.add(item);
        }
        List<String> list4 = new ArrayList<>();
        array = getResources().getStringArray(R.array.day4Underweight);
        for (String item : array) {
            list4.add(item);
        }

        List<String> list5 = new ArrayList<>();
        array = getResources().getStringArray(R.array.day5Underweight);
        for (String item : array) {
            list5.add(item);
        }
        listItem.put(listGroup.get(0), list1);
        listItem.put(listGroup.get(1), list2);
        listItem.put(listGroup.get(2), list3);
        listItem.put(listGroup.get(3), list4);
        listItem.put(listGroup.get(4), list5);
        adapter.notifyDataSetChanged();


    }

    private void NormalData() {
        listGroup.add(getString(R.string.day1));
        listGroup.add(getString(R.string.day2));
        listGroup.add(getString(R.string.day3));
        listGroup.add(getString(R.string.day4));
        listGroup.add(getString(R.string.day5));
        String[] array;

        List<String> normal1 = new ArrayList<>();
        array = getResources().getStringArray(R.array.day1Normal);
        for (String item : array) {
            normal1.add(item);
        }
        List<String> normal2 = new ArrayList<>();
        array = getResources().getStringArray(R.array.day2Normal);
        for (String item : array) {
            normal2.add(item);
        }
        List<String> normal3 = new ArrayList<>();
        array = getResources().getStringArray(R.array.day3Normal);
        for (String item : array) {
            normal3.add(item);
        }
        List<String> normal4 = new ArrayList<>();
        array = getResources().getStringArray(R.array.day4Normal);
        for (String item : array) {
            normal4.add(item);
        }

        List<String> normal5 = new ArrayList<>();
        array = getResources().getStringArray(R.array.day5Normal);
        for (String item : array) {
            normal5.add(item);
        }
        listItem.put(listGroup.get(0), normal1);
        listItem.put(listGroup.get(1), normal2);
        listItem.put(listGroup.get(2), normal3);
        listItem.put(listGroup.get(3), normal4);
        listItem.put(listGroup.get(4), normal5);
        adapter.notifyDataSetChanged();


    }

    private void OverweightData() {
        listGroup.add(getString(R.string.day1));
        listGroup.add(getString(R.string.day2));
        listGroup.add(getString(R.string.day3));
        listGroup.add(getString(R.string.day4));
        listGroup.add(getString(R.string.day5));
        String[] array;

        List<String> list1 = new ArrayList<>();
        array = getResources().getStringArray(R.array.day1Overweight);
        for (String item : array) {
            list1.add(item);
        }
        List<String> list2 = new ArrayList<>();
        array = getResources().getStringArray(R.array.day2Overweight);
        for (String item : array) {
            list2.add(item);
        }
        List<String> list3 = new ArrayList<>();
        array = getResources().getStringArray(R.array.day3Overweight);
        for (String item : array) {
            list3.add(item);
        }
        List<String> list4 = new ArrayList<>();
        array = getResources().getStringArray(R.array.day4Overweight);
        for (String item : array) {
            list4.add(item);
        }

        List<String> list5 = new ArrayList<>();
        array = getResources().getStringArray(R.array.day5Overweight);
        for (String item : array) {
            list5.add(item);
        }
        listItem.put(listGroup.get(0), list1);
        listItem.put(listGroup.get(1), list2);
        listItem.put(listGroup.get(2), list3);
        listItem.put(listGroup.get(3), list4);
        listItem.put(listGroup.get(4), list5);
        adapter.notifyDataSetChanged();


    }

    private void ObeseData() {
        listGroup.add(getString(R.string.day1));
        listGroup.add(getString(R.string.day2));
        listGroup.add(getString(R.string.day3));
        listGroup.add(getString(R.string.day4));
        listGroup.add(getString(R.string.day5));
        String[] array;

        List<String> list1 = new ArrayList<>();
        array = getResources().getStringArray(R.array.day1Obese);
        for (String item : array) {
            list1.add(item);
        }
        List<String> list2 = new ArrayList<>();
        array = getResources().getStringArray(R.array.day2Obese);
        for (String item : array) {
            list2.add(item);
        }
        List<String> list3 = new ArrayList<>();
        array = getResources().getStringArray(R.array.day3Obese);
        for (String item : array) {
            list3.add(item);
        }
        List<String> list4 = new ArrayList<>();
        array = getResources().getStringArray(R.array.day4Obese);
        for (String item : array) {
            list4.add(item);
        }

        List<String> list5 = new ArrayList<>();
        array = getResources().getStringArray(R.array.day5Obese);
        for (String item : array) {
            list5.add(item);
        }
        listItem.put(listGroup.get(0), list1);
        listItem.put(listGroup.get(1), list2);
        listItem.put(listGroup.get(2), list3);
        listItem.put(listGroup.get(3), list4);
        listItem.put(listGroup.get(4), list5);
        adapter.notifyDataSetChanged();


    }
}