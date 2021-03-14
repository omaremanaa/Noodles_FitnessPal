package com.example.noodles_fitnesspal;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UnderweightWorkoutActivity extends AppCompatActivity {
    myadapterworkout adapter;
    RecyclerView recview;
    DatabaseReference reference;
    FirebaseUser user;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_workout);

        SimpleDateFormat sdf = new SimpleDateFormat("EEE");
        String currentDateandTime = sdf.format(new Date());
        //Toolbar with Up enable and Title of Activity
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Today's (" + currentDateandTime + ") Workout");
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        recview = findViewById(R.id.recviewworkout);
        recview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        switch (day){

            case Calendar.MONDAY:
        FirebaseRecyclerOptions<TodayWorkoutClass> options =
                new FirebaseRecyclerOptions.Builder<TodayWorkoutClass>().setQuery(
                        FirebaseDatabase.getInstance().getReference().child("Workout").child("Underweight").child("Day 1"), TodayWorkoutClass.class
                ).build();
                adapter = new myadapterworkout(options);
                recview.setAdapter(adapter);
        break;
            case Calendar.TUESDAY:
                options =
                        new FirebaseRecyclerOptions.Builder<TodayWorkoutClass>().setQuery(
                                FirebaseDatabase.getInstance().getReference().child("Workout").child("Underweight").child("Day 2"), TodayWorkoutClass.class
                        ).build();
                adapter = new myadapterworkout(options);
                recview.setAdapter(adapter);
                break;
            case Calendar.WEDNESDAY:
                options =
                        new FirebaseRecyclerOptions.Builder<TodayWorkoutClass>().setQuery(
                                FirebaseDatabase.getInstance().getReference().child("Workout").child("Underweight").child("Day 3"), TodayWorkoutClass.class
                        ).build();
                adapter = new myadapterworkout(options);
                recview.setAdapter(adapter);
                break;
            case Calendar.THURSDAY:
                options =
                        new FirebaseRecyclerOptions.Builder<TodayWorkoutClass>().setQuery(
                                FirebaseDatabase.getInstance().getReference().child("Workout").child("Underweight").child("Day 4"), TodayWorkoutClass.class
                        ).build();
                adapter = new myadapterworkout(options);
                recview.setAdapter(adapter);
                break;
            case Calendar.FRIDAY:
                options =
                        new FirebaseRecyclerOptions.Builder<TodayWorkoutClass>().setQuery(
                                FirebaseDatabase.getInstance().getReference().child("Workout").child("Underweight").child("Day 5"), TodayWorkoutClass.class
                        ).build();
                adapter = new myadapterworkout(options);
                recview.setAdapter(adapter);
                break;
            default:
                options =
                        new FirebaseRecyclerOptions.Builder<TodayWorkoutClass>().setQuery(
                                FirebaseDatabase.getInstance().getReference().child("Workout").child("Underweight").child("Day 6"), TodayWorkoutClass.class
                        ).build();
                adapter = new myadapterworkout(options);
                recview.setAdapter(adapter);



        }
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
