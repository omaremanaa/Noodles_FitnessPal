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

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class  DietFragment extends Fragment {
    Button breakfast, lunch, dinner, snacks;
    DatabaseReference reference;
    FirebaseUser user;
    String userID;
    TextView rqCalIntake, rqProteinIntake, rqCarbIntake;


    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Diet");
        View v = inflater.inflate(R.layout.fragment_diet, container, false);
        breakfast = v.findViewById(R.id.breakFastbtn);
        lunch = v.findViewById(R.id.lunchbtn);
        snacks = v.findViewById(R.id.snackbtn);
        dinner = v.findViewById(R.id.dinnerbtn);
        rqCalIntake = v.findViewById(R.id.rqCalIntake);
        rqProteinIntake = v.findViewById(R.id.rqProteinIntake);
        rqCarbIntake = v.findViewById(R.id.rqCarbIntake);
        user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();

        reference = FirebaseDatabase.getInstance().getReference("Diet");
        //Button Breakfast
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String bmizzz = String.valueOf(snapshot.child(userID).child("bmitype").getValue());
                String proteinvalue = String.valueOf(snapshot.child(userID).child("protein").getValue());
                rqProteinIntake.setText(proteinvalue + " gm");
                if (bmizzz.equals("Normal")) {
                    String calorieIntake = "2500";
                    double carbsSum = (Double.parseDouble(calorieIntake.toString().trim())*0.45)/4;
                    String carbsData = String.format("%.2f",carbsSum);
                    HashMap hashMap = new HashMap();
                    hashMap.put("calorieIntake", calorieIntake);
                    hashMap.put("carbs", carbsData);
                    reference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                        @Override
                        public void onSuccess(Object o) {
                            rqCalIntake.setText(calorieIntake);
                            rqCarbIntake.setText(carbsData+ " gm");

                        }
                    });
                    dinner.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String listType = "Dinner";
                            HashMap hashMap = new HashMap();
                            hashMap.put("foodtime", listType);
                            reference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                                @Override
                                public void onSuccess(Object o) {
                                    Intent intent = new Intent(getContext(), DinnerList.class);
                                    startActivity(intent);
                                }
                            });
                        }
                    });
                    snacks.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String listType = "Snacks";
                            HashMap hashMap = new HashMap();
                            hashMap.put("foodtime", listType);
                            reference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                                @Override
                                public void onSuccess(Object o) {
                                    Intent intent = new Intent(getContext(), SnackList.class);
                                    startActivity(intent);
                                }
                            });
                        }
                    });
                    lunch.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String listType = "Lunch";
                            HashMap hashMap = new HashMap();
                            hashMap.put("foodtime", listType);
                            reference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                                @Override
                                public void onSuccess(Object o) {
                                    Intent intent = new Intent(getContext(), LunchList.class);
                                    startActivity(intent);
                                }
                            });
                        }
                    });
                    breakfast.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String listType = "Breakfast";
                            HashMap hashMap = new HashMap();
                            hashMap.put("foodtime", listType);
                            reference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                                @Override
                                public void onSuccess(Object o) {
                                    Intent intent = new Intent(getContext(), BreakfastList.class);
                                    startActivity(intent);
                                }
                            });

                        }
                    });
                } else if (bmizzz.equals("Overweight")) {
                    String calorieIntake = "2300";
                    double carbsSum = (Double.parseDouble(calorieIntake.toString().trim())*0.45)/4;
                    String carbsData = String.format("%.2f",carbsSum);
                    HashMap hashMap = new HashMap();
                    hashMap.put("carbs", carbsData);
                    hashMap.put("calorieIntake", calorieIntake);
                    reference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                        @Override
                        public void onSuccess(Object o) {
                            rqCalIntake.setText(calorieIntake);
                            rqCarbIntake.setText(carbsData+ " gm");
                        }
                    });
                    dinner.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String listType = "Dinner";
                            HashMap hashMap = new HashMap();
                            hashMap.put("foodtime", listType);
                            reference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                                @Override
                                public void onSuccess(Object o) {
                                    Intent intent = new Intent(getContext(), OverweightDinnerList.class);
                                    startActivity(intent);
                                }
                            });
                        }
                    });
                    snacks.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String listType = "Snacks";
                            HashMap hashMap = new HashMap();
                            hashMap.put("foodtime", listType);
                            reference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                                @Override
                                public void onSuccess(Object o) {
                                    Intent intent = new Intent(getContext(), OverweightSnackList.class);
                                    startActivity(intent);
                                }
                            });
                        }
                    });
                    lunch.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String listType = "Lunch";
                            HashMap hashMap = new HashMap();
                            hashMap.put("foodtime", listType);
                            reference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                                @Override
                                public void onSuccess(Object o) {
                                    Intent intent = new Intent(getContext(), OverweightLunchList.class);
                                    startActivity(intent);
                                }
                            });
                        }
                    });
                    breakfast.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String listType = "Breakfast";
                            HashMap hashMap = new HashMap();
                            hashMap.put("foodtime", listType);
                            reference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                                @Override
                                public void onSuccess(Object o) {
                                    Intent intent = new Intent(getContext(), OverweightBreakfastList.class);
                                    startActivity(intent);
                                }
                            });

                        }
                    });
                } else if (bmizzz.equals("Obese")) {
                    String calorieIntake = "2100";
                    double carbsSum = (Double.parseDouble(calorieIntake.toString().trim())*0.45)/4;
                    String carbsData = String.format("%.2f",carbsSum);
                    HashMap hashMap = new HashMap();
                    hashMap.put("carbs", carbsData);
                    hashMap.put("calorieIntake", calorieIntake);
                    reference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                        @Override
                        public void onSuccess(Object o) {
                            rqCalIntake.setText(calorieIntake);
                            rqCarbIntake.setText(carbsData+ " gm");
                        }
                    });
                    dinner.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String listType = "Dinner";
                            HashMap hashMap = new HashMap();
                            hashMap.put("foodtime", listType);
                            reference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                                @Override
                                public void onSuccess(Object o) {
                                    Intent intent = new Intent(getContext(), ObeseDinnerList.class);
                                    startActivity(intent);
                                }
                            });
                        }
                    });
                    snacks.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String listType = "Snacks";
                            HashMap hashMap = new HashMap();
                            hashMap.put("foodtime", listType);
                            reference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                                @Override
                                public void onSuccess(Object o) {
                                    Intent intent = new Intent(getContext(), ObeseSnackList.class);
                                    startActivity(intent);
                                }
                            });
                        }
                    });
                    lunch.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String listType = "Lunch";
                            HashMap hashMap = new HashMap();
                            hashMap.put("foodtime", listType);
                            reference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                                @Override
                                public void onSuccess(Object o) {
                                    Intent intent = new Intent(getContext(), ObeseLunchList.class);
                                    startActivity(intent);
                                }
                            });
                        }
                    });
                    breakfast.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String listType = "Breakfast";
                            HashMap hashMap = new HashMap();
                            hashMap.put("foodtime", listType);
                            reference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                                @Override
                                public void onSuccess(Object o) {
                                    Intent intent = new Intent(getContext(), ObeseBreakfastList.class);
                                    startActivity(intent);
                                }
                            });

                        }
                    });
                } else if (bmizzz.equals("Underweight")) {
                    String calorieIntake = "2700";
                    double carbsSum = (Double.parseDouble(calorieIntake.toString().trim())*0.45)/4;
                    String carbsData = String.format("%.2f",carbsSum);
                    HashMap hashMap = new HashMap();
                    hashMap.put("carbs", carbsData);
                    hashMap.put("calorieIntake", calorieIntake);
                    reference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                        @Override
                        public void onSuccess(Object o) {
                            rqCalIntake.setText(calorieIntake);
                            rqCarbIntake.setText(carbsData+ " gm");
                        }
                    });
                    dinner.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String listType = "Dinner";
                            HashMap hashMap = new HashMap();
                            hashMap.put("foodtime", listType);
                            reference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                                @Override
                                public void onSuccess(Object o) {
                                    Intent intent = new Intent(getContext(), UnderweightDinnerList.class);
                                    startActivity(intent);
                                }
                            });
                        }
                    });
                    snacks.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String listType = "Snacks";
                            HashMap hashMap = new HashMap();
                            hashMap.put("foodtime", listType);
                            reference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                                @Override
                                public void onSuccess(Object o) {
                                    Intent intent = new Intent(getContext(), UnderweightSnackList.class);
                                    startActivity(intent);
                                }
                            });
                        }
                    });
                    lunch.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String listType = "Lunch";
                            HashMap hashMap = new HashMap();
                            hashMap.put("foodtime", listType);
                            reference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                                @Override
                                public void onSuccess(Object o) {
                                    Intent intent = new Intent(getContext(), UnderweightLunchList.class);
                                    startActivity(intent);
                                }
                            });
                        }
                    });
                    breakfast.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String listType = "Breakfast";
                            HashMap hashMap = new HashMap();
                            hashMap.put("foodtime", listType);
                            reference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                                @Override
                                public void onSuccess(Object o) {
                                    Intent intent = new Intent(getContext(), UnderweightBreakfastList.class);
                                    startActivity(intent);
                                }
                            });

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return v;
    }
}
