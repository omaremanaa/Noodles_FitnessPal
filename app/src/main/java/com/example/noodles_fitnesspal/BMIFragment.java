package com.example.noodles_fitnesspal;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.HashMap;

public class BMIFragment extends Fragment {
    EditText heighted, weighted;
    TextView result, current, bmitype;
    Button calculate, clear, saveattributes;
    Activity mainmenu;
    FirebaseAuth firebaseAuth;
    DatabaseReference reference, referencework, referencediet;
    FirebaseUser user;
    String userID;
    FirebaseUser firebaseUser;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("BMI");
        View v = inflater.inflate(R.layout.fragment_bmi, container, false);
        firebaseAuth = FirebaseAuth.getInstance();

        heighted = (EditText) v.findViewById(R.id.HeightCalc);
        weighted = (EditText) v.findViewById(R.id.WeightCalc);
        calculate = (Button) v.findViewById(R.id.CalculateButton);
        result = (TextView) v.findViewById(R.id.TempResult);
        bmitype = (TextView) v.findViewById(R.id.BMItype);
        saveattributes = v.findViewById(R.id.calculateButton2);
        current = v.findViewById(R.id.CurrentBMI);
        clear = v.findViewById(R.id.clearButton);
        reference = FirebaseDatabase.getInstance().getReference("BmiHelper");
        referencework = FirebaseDatabase.getInstance().getReference("Workout");
        referencediet = FirebaseDatabase.getInstance().getReference("Diet");
        user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();
        DecimalFormat df2 = new DecimalFormat("#.##");
        //If user clicked on Calculate and so its a the result would be for a temp time.
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                double num1 = Double.parseDouble(heighted.getText().toString());
                double num2 = Double.parseDouble(weighted.getText().toString());
                double sum = num2 / Math.pow((num1 * 0.01), 2);

                result.setText(df2.format(sum) + " kg/m\u00B2");
            }

        });

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                BmiHelper bmiHelper = snapshot.getValue(BmiHelper.class);
                //Reads Data
                if (bmiHelper != null) {
                    //Shows saved BMI if there is any under "Your Current BMI"
                    String bmiNumber = bmiHelper.bmi;
                    String bmiType = bmiHelper.bmitype;
                    current.setText(bmiNumber);
                    bmitype.setText(bmiType);
                    //Sets the background Color for the background type
                    if (bmiType.equals("Obese")) {
                        bmitype.setBackgroundResource(R.drawable.red_rounded_corner);
                    } else if (bmiType.equals("Underweight")) {
                        bmitype.setBackgroundResource(R.drawable.yellow_rounded_corner);
                    } else if (bmiType.equals("Overweight")) {
                        bmitype.setBackgroundResource(R.drawable.lightred_rounded_corner);
                    } else {
                        bmitype.setBackgroundResource(R.drawable.green_rounded_corner);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        saveattributes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double num1 = Double.parseDouble(heighted.getText().toString());
                double num2 = Double.parseDouble(weighted.getText().toString());
                double sum = num2 / Math.pow((num1 * 0.01), 2);
                double proteinSum = num2*0.8;
                String heightData = heighted.getText().toString().trim();
                String weightData = weighted.getText().toString().trim();
                current.setText(df2.format(sum) + " kg/m\u00B2");
                if (sum < 18.5) {
                    bmitype.setText("Underweight");
                    bmitype.setBackgroundResource(R.drawable.yellow_rounded_corner);
                } else if (sum >= 18.5 && sum < 25) {
                    bmitype.setText("Normal");
                    bmitype.setBackgroundResource(R.drawable.green_rounded_corner);
                } else if (sum >= 25 && sum < 30) {
                    bmitype.setText("Overweight");
                    bmitype.setBackgroundResource(R.drawable.lightred_rounded_corner);
                } else {
                    bmitype.setText("Obese");
                    bmitype.setBackgroundResource(R.drawable.red_rounded_corner);
                }
                String bmityped = bmitype.getText().toString().trim();
                String BmiData = current.getText().toString().trim();
                BmiHelper bmiHelper = new BmiHelper(heightData, weightData, BmiData, bmityped);
                WorkoutHelper workoutHelper = new WorkoutHelper(heightData, weightData, bmityped);
                DietHelper dietHelper = new DietHelper(heightData, weightData, bmityped, null);
                String proteinData = String.format("%.1f",proteinSum);

//                Updates Data
                if (dietHelper != null) {
                    HashMap hashMap = new HashMap();

                    hashMap.put("bmitype", bmityped);
                    hashMap.put("height", heightData);
                    hashMap.put("weight", weightData);
                    hashMap.put("protein", proteinData);

                    referencediet.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                        @Override
                        public void onSuccess(Object o) {
                            Toast.makeText(getContext(), "Data is Updated.", Toast.LENGTH_LONG).show();
                        }
                    });

                }
                //                Updates Data
                if (workoutHelper != null) {
                    HashMap hashMap = new HashMap();

                    hashMap.put("bmitype", bmityped);
                    hashMap.put("height", heightData);
                    hashMap.put("weight", weightData);

                    referencework.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                        @Override
                        public void onSuccess(Object o) {
                            Toast.makeText(getContext(), "Data is Updated.", Toast.LENGTH_LONG).show();
                        }
                    });

                }
                // Writes Data
                if (workoutHelper == null) {
                    FirebaseDatabase.getInstance().getReference("Workout").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(bmiHelper).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(getContext(), "Data is stored.", Toast.LENGTH_LONG).show();
                        }
                    });
                }
//                Updates Data
                if (bmiHelper != null) {
                    HashMap hashMap = new HashMap();

                    hashMap.put("bmi", BmiData);
                    hashMap.put("bmitype", bmityped);
                    hashMap.put("height", heightData);
                    hashMap.put("weight", weightData);

                    reference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                        @Override
                        public void onSuccess(Object o) {
                            Toast.makeText(getContext(), "Data is Updated.", Toast.LENGTH_LONG).show();
                        }
                    });

                }

//                Writes Data
                if (bmiHelper == null) {
                    FirebaseDatabase.getInstance().getReference("BmiHelper").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(bmiHelper).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(getContext(), "Data is stored.", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearData(v);
            }

            private void clearData(View v) {
                DatabaseReference drclear = FirebaseDatabase.getInstance().getReference("BmiHelper").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                drclear.removeValue();
                Toast.makeText(getContext(), "Data is Deleted.", Toast.LENGTH_LONG).show();
            }
        });

        return v;
    }
}
