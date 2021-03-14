package com.example.noodles_fitnesspal;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class OverweightSnackList extends AppCompatActivity {
    myadapterdiet adapter;
    RecyclerView recview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breakfast_list);
        recview = findViewById(R.id.recviewdiet);
        recview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Snack Options");
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        FirebaseRecyclerOptions<TypeFoodHelper> options =
                new FirebaseRecyclerOptions.Builder<TypeFoodHelper>().setQuery(
                        FirebaseDatabase.getInstance().getReference().child("Diet").child("Bmitype").child("Overweight").child("Snacks"), TypeFoodHelper.class
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