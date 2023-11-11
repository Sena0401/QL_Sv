package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Activity_Show_Student extends AppCompatActivity {

    ArrayList<String> masv, tensv;
    DatabaseHelper db;
    ImageButton btn_back;
    Button btn_updateStudent, btn_addStudent, btn_deleteStudent;
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

        btn_deleteStudent =  findViewById(R.id.btn_deleteStudent);
        btn_deleteStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteStudent();


            }
        });
        btn_addStudent = findViewById(R.id.btn_addStudent);
        btn_addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themSv();
            }
        });

        btn_updateStudent = findViewById(R.id.btn_updateStudent);
        btn_updateStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            getID_ToUpdate();
            }
        });
    }

    private void themSv() {
        Intent i = new Intent(Activity_Show_Student.this, Activity_Add_Student.class);
        startActivity(i);
    }

    private void displayStudent() {
        Cursor cursor = db.showStudent();
        while (cursor.moveToNext()) {
            masv.add(cursor.getString(0));
            tensv.add(cursor.getString(2));
        }
    }

    private void deleteStudent() {
        // anh xa
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.delete_student);
        dialog.setCanceledOnTouchOutside(false);
        DatabaseHelper db = new DatabaseHelper(this);

        Button confirm = dialog.findViewById(R.id.confirm_btn_yes);
        Button cancel = dialog.findViewById(R.id.confirm_btn_no);

        EditText idToDEL = dialog.findViewById(R.id.Edttxt_input_masv);


        // xoa
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = idToDEL.getText().toString();
                boolean kq = db.deleteStudent(id);
                if (kq == true) {
                    Toast.makeText(getApplicationContext(),"Đã xóa SV",Toast.LENGTH_LONG).show();
                }
                finish();
            }
        });
        dialog.show();
        //cancel
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // hienthi dialog
        dialog.show();
    }
    private void getID_ToUpdate() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_getid_toupdate);
        dialog.setCanceledOnTouchOutside(false);

        Button cancel = dialog.findViewById(R.id.dialog_button_no);
        Button ok = dialog.findViewById(R.id.dialog_button_yes);

        EditText masv = dialog.findViewById(R.id.id);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = masv.getText().toString();
                updateStudent(id);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        dialog.show();

    }

    //Xử lý sửa
    private void updateStudent(String masv) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_update_student);
        dialog.setCanceledOnTouchOutside(false);
        DatabaseHelper db = new DatabaseHelper(this);


        Button adding = dialog.findViewById(R.id.confirm_btn_yes);
        Button canceling = dialog.findViewById(R.id.confirm_btn_no);

        EditText tensv = dialog.findViewById(R.id.edtext_update_tensv);
        EditText ngaysinh = dialog.findViewById(R.id.edtext_update_ngaysinh);
        EditText gioitinh = dialog.findViewById(R.id.edtext_update_gioitinh);
        EditText dantoc = dialog.findViewById(R.id.edtext_update_dantoc);
        EditText diachi = dialog.findViewById(R.id.edtext_update_diachi);
        EditText sodienthoai = dialog.findViewById(R.id.edtext_update_sdt);
        EditText thanhpho = dialog.findViewById(R.id.edtext_update_tp);


        adding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String TENSV = tensv.getText().toString();
                String NGAYSINH = ngaysinh.getText().toString();
                String GIOITINH = gioitinh.getText().toString();
                String DANTOC = dantoc.getText().toString();
                String DIACHI = diachi.getText().toString();
                String SODIENTHOAI = sodienthoai.getText().toString();
                String THANHPHO = thanhpho.getText().toString();

                Boolean kq = db.updateStudent(masv, TENSV, NGAYSINH, GIOITINH,DANTOC, DIACHI,  SODIENTHOAI,THANHPHO);
                if (kq == true) {
                    Toast.makeText(getApplicationContext(), "Chỉnh sửa thành công", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(getApplicationContext(), Activity_Show_Student.class);
                    startActivity(i);
                } else {
                    Toast.makeText(getApplicationContext(), "Đã có lỗi xảy ra, Vui long thử lại", Toast.LENGTH_LONG).show();
                }
            }
        });

        canceling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        dialog.show();
    }

}