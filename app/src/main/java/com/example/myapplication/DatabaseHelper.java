package com.example.myapplication;

import android.content.ContentValues;
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
        //MALOP TEXT,
        db.execSQL("CREATE TABLE SINHVIEN (MASV TEXT PRIMARY KEY,  HOTEN TEXT, NGAYSINH TEXT, " +
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
    //
    public  Boolean Add_student(String MASV,String MALOP,  String HOTEN, String NGAYSINH, String GIOITINH, String DIACHI, String DANTOC, String SODIENTHOAI, String THANHPHO ){
         //
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MASV", MASV);
        values.put("MALOP", MALOP);
        values.put("HOTEN", HOTEN);
        values.put("NGAYSINH", NGAYSINH);
        values.put("GIOITINH", GIOITINH);
        values.put("DIACHI", DIACHI);
        values.put("DANTOC", DANTOC);
        values.put("SODIENTHOAI", SODIENTHOAI);
        values.put("THANHPHO", THANHPHO);
        long Kq = db.insert("SINHVIEN", null, values);
        if (Kq == -1) {
            return false;
        } else {
            return true;
        }
    }
    public  Boolean Add_Class(String MALOP, String TENLOP, String MANK ){
        //
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MALOP", MALOP);
        values.put("TENLOP", TENLOP);
        values.put("MANIENKHOA", MANK);

        long Kq = db.insert("LOP", null, values);
        if (Kq == -1) {
            return false;
        } else {
            return true;
        }
    }public  Boolean Add_Subject(String MAMON, String TENMON, String TINCHI ){
        //
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MAMON", MAMON);
        values.put("TENMON", TENMON);
        values.put("TINCHI", TINCHI);

        long Kq = db.insert("MON", null, values);
        if (Kq == -1) {
            return false;
        } else {
            return true;
        }
    }
    public Cursor showClass() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM LOP",null);
        return cursor;
    }
    public Cursor getdataSTU_withID(String id) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM SINHVIEN WHERE MSSV = ?",new String[]{id});
        return cursor;
    }

}

