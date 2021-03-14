package com.example.noodles_fitnesspal;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";
    private ArrayList<String> mFoodName = new ArrayList<>();
    private ArrayList<String> imageUrls = new ArrayList<>();
    private ArrayList<String> mCalories = new ArrayList<>();
    private Context context;
    ArrayList<CalorieHelper> list;

    public RecyclerViewAdapter(ArrayList<CalorieHelper> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.foodname.setText(list.get(position).getFoodname());
        holder.Calorie.setText(list.get(position).getCalorie());
        Log.d(TAG, "onBindViewHolder: called");
        Glide.with(context).asBitmap().load(imageUrls.get(position)).into(holder.image);
        ;
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on image: " + mFoodName.get(position));
                Toast.makeText(context, mFoodName.get(position), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView image;
        TextView foodname, Calorie;

        public ViewHolder(View itemView) {
            super(itemView);
//            image = itemView.findViewById(R.id.image_view);
            foodname = itemView.findViewById(R.id.foodname);
            Calorie = itemView.findViewById(R.id.foodcalorie);

        }

    }
}
