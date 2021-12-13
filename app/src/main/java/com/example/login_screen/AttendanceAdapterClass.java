package com.example.login_screen;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class AttendanceAdapterClass extends RecyclerView.Adapter<AttendanceViewHolderClass> {

    ArrayList<AttendanceModel> data;
    Context context;

    public AttendanceAdapterClass(ArrayList<AttendanceModel> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public AttendanceViewHolderClass onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.attendancerow, parent, false);
        return new AttendanceViewHolderClass(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AttendanceViewHolderClass holder, int position) {
        final AttendanceModel temp = data.get(position);

        holder.tv1.setText(data.get(position).getDate());
        holder.tv2.setText(data.get(position).getPickup());
        holder.tv3.setText(data.get(position).getDropoff());



    }

    @Override
    public int getItemCount() {
        return data.size();
    }


}
