package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;


public class Activity_Show_ListClass extends AppCompatActivity {
    ArrayList<String> malop, tenlop, mank;
    ListView_Class_Adapter adapter;

    RecyclerView recyclerView;
    DatabaseHelper db;

    ImageButton back;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list_class);

        db = new DatabaseHelper(this);
        malop = new ArrayList<>();
        tenlop = new ArrayList<>();
        mank = new ArrayList<>();

        recyclerView = findViewById(R.id.listview_Showclass);
        adapter = new ListView_Class_Adapter(this,malop, tenlop, mank);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displayClass();
        Cursor cursor = db.showClass();
        if(cursor.getCount()==0) {

            Toast.makeText(Activity_Show_ListClass.this, "Không có dữ liệu hiển thị", Toast.LENGTH_LONG).show();
            return;
        }
        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // xử lý lấy mã sv qua intent khác để sử dụng
            }
        });
        back = findViewById(R.id.btn_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
    private void displayClass() {
        Cursor cursor = db.showClass();
        while (cursor.moveToNext()) {
            malop.add(cursor.getString(0));
            tenlop.add(cursor.getString(1));
            mank.add(cursor.getString(2));

        }
    }
}