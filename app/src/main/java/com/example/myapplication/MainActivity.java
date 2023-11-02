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

    Button showSv, showLop, showMonhoc, themsv, themlop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Khởi tạo DatabaseHelper
        themlop = findViewById(R.id.btn_AddLop);
        showSv = findViewById(R.id.btn_showSv);
        showLop = findViewById(R.id.btn_showLop);
        showMonhoc = findViewById(R.id.btn_showMonhoc);
        themsv = findViewById(R.id.btn_addStudent);

        showLop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowLop();
            }
        });
        showSv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowSv();
            }
        });

        themsv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThemSv();
            }
        });
        themlop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThemLop();
            }
        });

    }

    private void ThemLop() {
        Intent intent = new Intent(MainActivity.this, Activity_Add_Class.class);
        startActivity(intent);
    }

    private void ShowLop() {
        Intent intent = new Intent(MainActivity.this, Activity_Show_ListClass.class);
        startActivity(intent);
    }
    private void ShowSv() {
        Intent intent = new Intent(MainActivity.this, Activity_Show_Student.class);
        startActivity(intent);
    }

    private void ThemSv() {
        Intent intent = new Intent(MainActivity.this, Activity_Add_Student.class);
        startActivity(intent);
    }
}
