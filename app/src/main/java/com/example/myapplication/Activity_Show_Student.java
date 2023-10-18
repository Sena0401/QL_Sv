package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Activity_Show_Student extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase db;
    TextView txtHoTen, txtMaSV, txtNgaySinh, txtGioiTinh, txtDanToc, txtDiaChi, txtSoDienThoai;

    ImageButton btn_back;
    CardView cardView;

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_student);

        txtMaSV = cardView.findViewById(R.id.textViewMasv);
        txtHoTen = cardView.findViewById(R.id.textViewHoten);
        txtNgaySinh = cardView.findViewById(R.id.textViewNgaysinh);
        txtGioiTinh = cardView.findViewById(R.id.textViewGioitinh);
        txtDanToc = cardView.findViewById(R.id.textViewDantoc);
        txtDiaChi = cardView.findViewById(R.id.textViewDiachi);
        txtSoDienThoai = cardView.findViewById(R.id.textViewSdt);

        CardView cardView = findViewById(R.id.cardView);
        btn_back = findViewById(R.id.btn_back);

        databaseHelper = new DatabaseHelper(this);
        db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM SINHVIEN", null);

        if (cursor.moveToFirst()) {
            txtHoTen.setText(cursor.getString(cursor.getColumnIndex("HOTEN")));
            txtMaSV.setText(cursor.getString(cursor.getColumnIndex("MASV")));
            txtNgaySinh.setText(cursor.getString(cursor.getColumnIndex("NGAYSINH")));
            txtGioiTinh.setText(cursor.getString(cursor.getColumnIndex("GIOITINH")));
            txtDanToc.setText(cursor.getString(cursor.getColumnIndex("DANTOC")));
            txtDiaChi.setText(cursor.getString(cursor.getColumnIndex("DIACHI")));
            txtSoDienThoai.setText(cursor.getString(cursor.getColumnIndex("SODIENTHOAI")));
        }

        cursor.close();


        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}