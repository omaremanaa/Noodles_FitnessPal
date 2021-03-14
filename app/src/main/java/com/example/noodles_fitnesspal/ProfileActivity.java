package com.example.noodles_fitnesspal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {
    TextView accountname, accountEmail, accountBmi, accountBmiType, accountAge, accountHeight, accountWeight;
    FirebaseUser user;
    DatabaseReference reference, bmireference;
    String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Profile");
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        accountname = findViewById(R.id.HelloName);
        accountEmail = findViewById(R.id.Emailuser);
        accountBmi = findViewById(R.id.bmiProfile);
        accountBmiType = findViewById(R.id.bmiTypeProfile);
        accountAge = findViewById(R.id.AgeProfile);
        accountWeight = findViewById(R.id.WeightProfile);
        accountHeight = findViewById(R.id.HeightProfile);
        reference = FirebaseDatabase.getInstance().getReference("Users");
        bmireference = FirebaseDatabase.getInstance().getReference("BmiHelper");
        user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();
        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserHelper userHelper = snapshot.getValue(UserHelper.class);
                if (userHelper != null) {
                    String name = userHelper.name;
                    String email = userHelper.email;
                    String age = userHelper.age;
                    accountname.setText("Welcome, " + name + "!");
                    accountEmail.setText("Email: " + email);
                    accountAge.setText("Age: " + age);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProfileActivity.this, "Something wrong happened", Toast.LENGTH_LONG).show();
                return;

            }
        });
        bmireference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                BmiHelper bmiHelper = snapshot.getValue(BmiHelper.class);
                if (bmiHelper != null) {
                    String height = bmiHelper.height;
                    String weight = bmiHelper.weight;
                    String bmiNumber = bmiHelper.bmi;
                    String bmiType = bmiHelper.bmitype;
                    accountBmi.setText(bmiNumber);
                    accountWeight.setText("Weight: " + weight);
                    accountHeight.setText("Height: " + height);
                    accountBmiType.setText(bmiType);
                    if (bmiType.equals("Obese")) {
                        accountBmiType.setTextColor(getResources().getColor(R.color.redObese));
                    } else if (bmiType.equals("Underweight")) {
                        accountBmiType.setTextColor(getResources().getColor(R.color.yellowUnderweight));
                    } else if (bmiType.equals("Overweight")) {
                        accountBmiType.setTextColor(getResources().getColor(R.color.redOverweight));
                    } else {
                        accountBmiType.setTextColor(getResources().getColor(R.color.greenNormal));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProfileActivity.this, "Something wrong happened", Toast.LENGTH_LONG).show();
                return;

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_edit:
                Intent intent = new Intent(this, EditActivity.class);
                this.startActivity(intent);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }
}