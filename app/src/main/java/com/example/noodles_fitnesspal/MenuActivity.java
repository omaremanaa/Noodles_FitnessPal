package com.example.noodles_fitnesspal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Fitness Pal");
        BottomNavigationView bottomnav = findViewById(R.id.bottom_nav);
        bottomnav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();

    }


    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_faq_account, menu);
        return true;
    }

    //Tool Bar for profile and faq as navigation bar cant handle more than 5 items
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_profile:
                Intent intent = new Intent(this, ProfileActivity.class);
                this.startActivity(intent);
                break;
            case R.id.nav_faq:
                Intent intent2 = new Intent(this, faqActivity.class);
                this.startActivity(intent2);
                break;
            case R.id.nav_signout:
                FirebaseAuth.getInstance().signOut();
                Intent intent3 = new Intent(this, MainActivity.class);
                Toast.makeText(getBaseContext(), "You have been logged out", Toast.LENGTH_LONG).show();
                this.startActivity(intent3);
                break;
            case R.id.nav_videos:
                Intent intent4 = new Intent(this, VideoActivity.class);
                this.startActivity(intent4);
                break;
            case R.id.nav_calorieInfo:
                Intent intent5 = new Intent(this, CalorieInformation.class);
                this.startActivity(intent5);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

// BottomNavigationView is to change from home fragment(default) to any other fragment given in the nav bar

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectedFragment = null;
            switch (menuItem.getItemId()) {
                case R.id.nav_home:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.nav_diet:
                    selectedFragment = new DietFragment();
                    break;
                case R.id.nav_workout:
                    selectedFragment = new WorkoutFragment();
                    break;
                case R.id.nav_bmi:
                    selectedFragment = new BMIFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

            return true;

        }
    };
}

