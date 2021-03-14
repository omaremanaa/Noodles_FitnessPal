package com.example.noodles_fitnesspal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class myadapterdiet extends FirebaseRecyclerAdapter<TypeFoodHelper, myadapterdiet.myviewholder> {
    public myadapterdiet(@NonNull FirebaseRecyclerOptions<TypeFoodHelper> options) {
        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull TypeFoodHelper model) {
        holder.namez.setText(model.getName());
        holder.cal.setText(model.getCalorie() + " cal");
        Glide.with(holder.img.getContext()).load(model.getImage()).into(holder.img);

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowdiet, parent, false);
        return new myviewholder(view);

    }

    class myviewholder extends RecyclerView.ViewHolder {
        CircleImageView img;
        TextView namez, cal;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imagefood);
            namez = itemView.findViewById(R.id.foodtext);
            cal = itemView.findViewById(R.id.foodCals);

        }

    }
}
