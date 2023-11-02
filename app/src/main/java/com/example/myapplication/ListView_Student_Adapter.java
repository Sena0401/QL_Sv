package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListView_Student_Adapter extends RecyclerView.Adapter<ListView_Student_Adapter.list_Student_holder> {
    Context context;
    private ArrayList masv, tensv;


    public ListView_Student_Adapter(Context context, ArrayList masv, ArrayList tensv) {
        this.context = context;
        this.masv  = masv;
        this.tensv = tensv;


    }



    @NonNull
    @Override
    public ListView_Student_Adapter.list_Student_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.listview_student, parent, false);
        return new list_Student_holder(v);
        }



    @Override
    public void onBindViewHolder(@NonNull list_Student_holder holder, int position) {
        holder.masv.setText(String.valueOf(masv.get(position)));
        holder.tensv.setText(String.valueOf(tensv.get(position)));


    }

    public int getItemCount() {
        return masv.size();
    }

    public class list_Student_holder extends RecyclerView.ViewHolder {
        TextView masv, tensv;

        public list_Student_holder(@NonNull View itemView) {
            super(itemView);
            masv = itemView.findViewById(R.id.textViewMasv);
            tensv = itemView.findViewById(R.id.textViewHoten);
        }

    }

}
