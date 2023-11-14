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
        db.execSQL("CREATE TABLE SINHVIEN (" +
                "MASV TEXT PRIMARY KEY," +
                "HOTEN TEXT, " +
                "NGAYSINH TEXT, " +
                "GIOITINH TEXT, " +
                "DANTOC TEXT, " +
                "DIACHI TEXT, " +
                "SODIENTHOAI TEXT, " +
                "THANHPHO TEXT, " +
                "FOREIGN KEY (MALOP) REFERENCES LOP(MALOP))");
        db.execSQL("CREATE TABLE MONHOC (MAMON TEXT PRIMARY KEY, TENMON TEXT, TINCHI INTEGER)");
        db.execSQL("CREATE TABLE DIEM (MASV TEXT, MAKHNH TEXT, MAMON TEXT, DIEMLAN1 REAL, " +
                "DIEMLAN2 REAL, DIEM REAL, FOREIGN KEY (MASV) REFERENCES SINHVIEN(MASV), FOREIGN KEY (MAKHNH) " +
                "REFERENCES HOCKYNAMHOC(MAHKNH), FOREIGN KEY (MAMON) REFERENCES MONHOC(MAMON))");
        db.execSQL("CREATE TABLE HOCKYNAMHOC (MAHKNH TEXT PRIMARY KEY, TENHKNH TEXT)");
        db.execSQL("CREATE TABLE GIANGVIEN (MAGV TEXT PRIMARY KEY, TENGV TEXT)");
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
        db.execSQL("DROP TABLE IF EXISTS GIANGVIEN");
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
    public Boolean Add_student(String MASV, String MALOP, String HOTEN, String NGAYSINH, String GIOITINH, String DIACHI, String DANTOC, String SODIENTHOAI, String THANHPHO) {
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

    public Boolean Add_Class(String MALOP, String TENLOP, String MANK) {
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
    }

    public Boolean updateClass(String MALOP, String TENLOP, String MANK) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MALOP", MALOP);
        values.put("TENLOP", TENLOP);
        values.put("MANIENKHOA", MANK);
        Cursor cursor = DB.rawQuery("SELECT * FROM LOP WHERE MALOP = ?", new String[]{MALOP});
        if (cursor.getCount() > 0) {
            long kq = DB.update("LOP", values, "MALOP = ?", new String[]{MALOP});
            if (kq == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public void Add_GIANGVIEN_COSAN() {
        //
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String MAGV, TENGV;
        values.put("MAGV", "thien");// ten dnag nhap
        values.put("TENGV", "0000");// password

        db.insert("GIANGVIEN", null, values);

    }

    public Boolean Add_Subject(String MAMON, String TENMON, String TINCHI) {
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

    public Boolean updateSubject(String MAMON, String TENMON, String TINCHI) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MAMON", MAMON);
        values.put("TENMON", TENMON);
        values.put("TINCHI", TINCHI);
        Cursor cursor = DB.rawQuery("SELECT * FROM MON WHERE MAMON = ?", new String[]{MAMON});
        if (cursor.getCount() > 0) {
            long kq = DB.update("MON", values, "MAMON = ?", new String[]{MAMON});
            if (kq == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Cursor showClass() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM LOP", null);
        return cursor;
    }


    public Cursor showStudent() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM SINHVIEN", null);
        return cursor;
    }

    // xoa sv theo msv
    public Boolean deleteStudent(String masv) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM SINHVIEN WHERE MASV = ?", new String[]{masv});
        if (cursor.getCount() > 0) {
            long kq = DB.delete("SINHVIEN", "MASV = ?", new String[]{masv});
            if (kq == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Boolean deleteClass(String malop) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM LOP WHERE MALOP = ?", new String[]{malop});
        if (cursor.getCount() > 0) {
            long kq = DB.delete("LOP", "MALOP = ?", new String[]{malop});
            if (kq == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Boolean deleteAllClass() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM LOP", null);
        long kq = DB.delete("LOP", null, null);
        if (kq == -1) {
            return false;
        } else {
            return true;
        }

    }

    public Boolean deleteAllStudent() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM SINHVIEN", null);
        long kq = DB.delete("SINHVIEN", null, null);
        if (kq == -1) {
            return false;
        } else {
            return true;
        }

    }

    public Boolean updateStudent(String masv, String tensv, String ngaysinh, String gioitinh, String dantoc, String diachi, String sodienthoai, String thanhpho)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MASV", masv);
        values.put("HOTEN", tensv);
        values.put("NGAYSINH", ngaysinh);
        values.put("GIOITINH", gioitinh);
        values.put("DANTOC", dantoc);
        values.put("DIACHI", diachi);
        values.put("SODIENTHOAI", sodienthoai);
        values.put("THANHPHO", thanhpho);

        Cursor cursor = DB.rawQuery("SELECT * FROM SINHVIEN WHERE MASV = ?", new String[]{masv});
        if (cursor.getCount() > 0) {
            long kq = DB.update("SINHVIEN", values, "MASV = ?", new String[]{masv});
            if (kq == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
    public Cursor search_student_byid(String id) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM SINHVIEN WHERE MSSV = ?", new String[]{id});
        return cursor;
    }
}

