package com.example.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.database.Cursor;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button showSv, showLop, showMonhoc;

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Khởi tạo DatabaseHelper
        dbHelper = new DatabaseHelper(this);

        showSv = findViewById(R.id.btn_showSv);
        showLop = findViewById(R.id.btn_showLop);
        showMonhoc = findViewById(R.id.btn_showMonhoc);

        showSv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             ShowSv();
            }
        });

        showLop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        showLop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

    }
    private void ShowSv(){
        Intent intent = new Intent(MainActivity.this, Activity_Show_Student.class);

        startActivity(intent);
    }
}
