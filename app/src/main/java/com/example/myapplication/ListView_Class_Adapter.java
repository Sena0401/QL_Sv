package com.example.myapplication;


import android.content.Context;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class ListView_Class_Adapter extends RecyclerView.Adapter<ListView_Class_Adapter.list_class_holder> {


    private Context context;

    private ArrayList<String> malop, tenlop, mank;


    public ListView_Class_Adapter(Context context, ArrayList malop, ArrayList tenlop, ArrayList mank) {
        this.context = context;
        this.malop = malop;
        this.tenlop = tenlop;
        this.mank = mank;

    }

    @NonNull
    @Override
    public list_class_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.listview_class, parent, false);
        return new list_class_holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull list_class_holder holder, int position) {
        holder.malop.setText(String.valueOf(malop.get(position)));
        holder.tenlop.setText(String.valueOf(tenlop.get(position)));
        holder.mank.setText(String.valueOf(mank.get(position)));


        holder.layout_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickToDetail(malop);
            }
        });

    }

    private void onClickToDetail(ArrayList malop ) {
        Intent intent = new Intent(context, Activity_InfoClass.class);
        intent.putExtra("malop", malop);
        context.startActivity(intent);
    }
    public int getItemCount() {
                return malop.size();
    }

    public class list_class_holder extends RecyclerView.ViewHolder {
        CardView layout_item;
        TextView malop, tenlop, mank;

        public list_class_holder(@NonNull View itemView) {
            super(itemView);
            layout_item = itemView.findViewById(R.id.layout_item);
            malop = itemView.findViewById(R.id.cardview_list_class_malop);
            tenlop = itemView.findViewById(R.id.cardview_list_class_tenlop);
            mank = itemView.findViewById(R.id.cardview_list_class_mank);


        }

    }
}
