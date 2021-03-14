package com.example.noodles_fitnesspal;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BreakfastList extends AppCompatActivity {
    myadapterdiet adapter;
    RecyclerView recview;
    DatabaseReference reference;
    FirebaseUser user;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breakfast_list);
        recview = findViewById(R.id.recviewdiet);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Breakfast Options");
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        recview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        FirebaseRecyclerOptions<TypeFoodHelper> options =
                new FirebaseRecyclerOptions.Builder<TypeFoodHelper>().setQuery(
                        FirebaseDatabase.getInstance().getReference().child("Diet").child("Bmitype").child("Normal").child("Breakfast"), TypeFoodHelper.class
                ).build();
        adapter = new myadapterdiet(options);
        recview.setAdapter(adapter);
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