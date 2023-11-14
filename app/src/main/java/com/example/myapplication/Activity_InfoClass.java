package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class Activity_InfoClass extends AppCompatActivity {
    ArrayList malop, tenlop, mank;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_class);
        TextView textView = findViewById(R.id.Text);
        db = new DatabaseHelper(this);
        Intent intent = getIntent();

        String id = intent.getStringExtra("malop");


    }
}
