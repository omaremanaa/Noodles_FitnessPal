package com.example.noodles_fitnesspal;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class ExampleDialog extends AppCompatDialogFragment {
    private TextView workouts;
    private ExampleDialogListener listener;
    DatabaseReference reference;
    FirebaseUser user;
    String userID;
    String message;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);
        reference = FirebaseDatabase.getInstance().getReference("Workout");
        user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String bmizzz = String.valueOf(snapshot.child(userID).child("bmitype").getValue());
                switch (day) {
                    case Calendar.WEDNESDAY:
                        if (bmizzz.equals("Normal")) {
                            String value = String.valueOf(snapshot.child("Normal").child("Day 1").getValue());
                            message = value;
                        } else if (bmizzz.equals("Underweight")) {
                            String value = String.valueOf(snapshot.child("Underweight").child("Day 1").getValue());
                            message = value;
                        } else if (bmizzz.equals("Overweight")) {
                            String value = String.valueOf(snapshot.child("Overweight").child("Day 1").getValue());
                            message = value;
                        } else if (bmizzz.equals("Obese")) {
                            String value = String.valueOf(snapshot.child("Obese").child("Day 1").getValue());
                            message = value;
                        }

                }
                builder.setView(view).setTitle("Today's Workouts").setMessage("message").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return builder.create();
    }


    public interface ExampleDialogListener {
        void applyTexts(String height, String weight);
    }
}
