package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Activity_Show_Student extends AppCompatActivity {

    ArrayList<String> masv, tensv;
    DatabaseHelper db;
    ImageButton btn_back;
    RecyclerView recyclerView;
    ListView_Student_Adapter adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_student);

        db = new DatabaseHelper(this);
        masv = new ArrayList<>();
        tensv = new ArrayList<>();

        recyclerView = findViewById(R.id.listview_ShowStudent);
        adapter = new ListView_Student_Adapter(this, masv, tensv);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displayStudent();
        Cursor cursor = db.showStudent();
        if (cursor.getCount() == 0) {
            Toast.makeText(Activity_Show_Student.this, "Không có dữ liệu hiển thị", Toast.LENGTH_LONG).show();
            return;
        }
        btn_back = findViewById(R.id.btn_back);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void displayStudent() {
        Cursor cursor = db.showStudent();
        while (cursor.moveToNext()) {
            masv.add(cursor.getString(0));
            tensv.add(cursor.getString(2));


        }
    }
}