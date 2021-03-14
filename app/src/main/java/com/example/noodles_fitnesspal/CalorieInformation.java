package com.example.noodles_fitnesspal;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CalorieInformation extends AppCompatActivity {
    DatabaseReference reference;
    myadapter adapter;
    RecyclerView recview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_information);
        //Toolbar with Up enable and Title of Activity
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Calorie Information");
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        recview = findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));
        reference = FirebaseDatabase.getInstance().getReference("CalorieCounter");
        FirebaseRecyclerOptions<CalorieHelper> options =
                new FirebaseRecyclerOptions.Builder<CalorieHelper>().setQuery(
                        FirebaseDatabase.getInstance().getReference().child("CalorieCounter"), CalorieHelper.class
                ).build();
        adapter = new myadapter(options);
        recview.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_video,menu);
        MenuItem item = menu.findItem(R.id.search_firebase);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                processSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                processSearch(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
    private void processSearch(String searchtext){
        FirebaseRecyclerOptions<CalorieHelper> options = new FirebaseRecyclerOptions.Builder<CalorieHelper>().setQuery(
                FirebaseDatabase.getInstance().getReference().child("CalorieCounter").orderByChild("search")
                        .startAt(searchtext).endAt(searchtext+"\uf8ff"),
                CalorieHelper.class).build();

        adapter = new myadapter(options);
        adapter.startListening();
        recview.setAdapter(adapter);
    }

}