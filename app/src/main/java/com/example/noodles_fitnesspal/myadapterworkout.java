package com.example.noodles_fitnesspal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class myadapterworkout extends FirebaseRecyclerAdapter<TodayWorkoutClass, myadapterworkout.myviewholder> {
    public myadapterworkout(@NonNull FirebaseRecyclerOptions<TodayWorkoutClass> options) {
        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull TodayWorkoutClass model) {
        holder.namez.setText(model.getName());
        holder.reps.setText(model.getReps());

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowworkout, parent, false);
        return new myviewholder(view);

    }

    class myviewholder extends RecyclerView.ViewHolder {
        TextView namez,reps;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            namez = itemView.findViewById(R.id.WorkoutText);
            reps = itemView.findViewById(R.id.workoutTimes);

        }

    }
}
