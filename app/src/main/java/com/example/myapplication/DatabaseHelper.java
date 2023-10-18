package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "UniversityDatabase.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo bảng cho từng thực thể
        db.execSQL("CREATE TABLE LOP (MALOP TEXT PRIMARY KEY, MANIENKHOA TEXT, TENLOP TEXT, " +
                "FOREIGN KEY (MANIENKHOA) REFERENCES NIENKHOA(MANIENKHOA))");
        db.execSQL("CREATE TABLE NIENKHOA (MANIENKHOA TEXT PRIMARY KEY, TENNIENKHOA TEXT)");
        db.execSQL("CREATE TABLE SINHVIEN (MASV TEXT PRIMARY KEY, MALOP TEXT, HOTEN TEXT, NGAYSINH TEXT, " +
                "GIOITINH TEXT, DANTOC TEXT, DIACHI TEXT, SODIENTHOAI TEXT, THANHPHO TEXT, FOREIGN KEY (MALOP) REFERENCES LOP(MALOP))");
        db.execSQL("CREATE TABLE MONHOC (MAMON TEXT PRIMARY KEY, TENMON TEXT, TINCHI INTEGER)");
        db.execSQL("CREATE TABLE DIEM (MASV TEXT, MAKHNH TEXT, MAMON TEXT, DIEMLAN1 REAL, " +
                "DIEMLAN2 REAL, DIEM REAL, FOREIGN KEY (MASV) REFERENCES SINHVIEN(MASV), FOREIGN KEY (MAKHNH) " +
                "REFERENCES HOCKYNAMHOC(MAHKNH), FOREIGN KEY (MAMON) REFERENCES MONHOC(MAMON))");
        db.execSQL("CREATE TABLE HOCKYNAMHOC (MAHKNH TEXT PRIMARY KEY, TENHKNH TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Xử lý nâng cấp cơ sở dữ liệu (nếu cần)
        db.execSQL("DROP TABLE IF EXISTS LOP");
        db.execSQL("DROP TABLE IF EXISTS NIENKHOA");
        db.execSQL("DROP TABLE IF EXISTS SINHVIEN");
        db.execSQL("DROP TABLE IF EXISTS MONHOC");
        db.execSQL("DROP TABLE IF EXISTS DIEM");
        db.execSQL("DROP TABLE IF EXISTS HOCKYNAMHOC");
        onCreate(db);
    }
    public Cursor getAllStudents() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM SINHVIEN";
        return db.rawQuery(query, null);
    }
    public Cursor getAllClass() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM LOP";
        return db.rawQuery(query, null);
    }
    public Cursor getAllSubject() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM MON";
        return db.rawQuery(query, null);
    }



}

