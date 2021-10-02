package com.example.login_screen;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;

public class ChildAdapterClass extends RecyclerView.Adapter<ChildViewHolderJava> {

    ArrayList<ChildModel> data;
    Context context;

    public ChildAdapterClass(ArrayList<ChildModel> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public ChildViewHolderJava onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.singlerow, parent, false);
        return new ChildViewHolderJava(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ChildViewHolderJava holder, int position) {
        final ChildModel temp = data.get(position);

        holder.tv1.setText(data.get(position).getHeader());
        holder.tv2.setText(data.get(position).getDesc());
        holder.img.setImageResource(data.get(position).getImg());


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DashBoard.class);
                intent.putExtra("Name", temp.getHeader());
                intent.putExtra("ind", "fromChildClass");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


}
