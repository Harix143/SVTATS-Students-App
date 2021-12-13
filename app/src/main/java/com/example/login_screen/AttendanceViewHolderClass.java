package com.example.login_screen;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class AttendanceViewHolderClass extends RecyclerView.ViewHolder {

    TextView tv1, tv2,tv3;
    CardView cardView;


    public AttendanceViewHolderClass(@NonNull @NotNull View itemView) {
        super(itemView);
        tv1 = (TextView) itemView.findViewById(R.id.date);
        tv2 = (TextView) itemView.findViewById(R.id.pickup);
        tv3 = (TextView) itemView.findViewById(R.id.dropoff);
        cardView = (CardView) itemView.findViewById(R.id.acardView);

//        itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Handle yout event here;
//                Intent i = new Intent(view.getContext(), DashBoard.class);
//                i.putExtra("title", getAdapterPosition());
//                view.getContext().startActivity(i);
////                itemView.getContext().startActivity(new Intent(itemView.getContext(), DashBoard.class));
//            }
//        });
    }


}

