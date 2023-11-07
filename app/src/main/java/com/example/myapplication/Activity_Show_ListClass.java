package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;


public class Activity_Show_ListClass extends AppCompatActivity {
    ArrayList<String> malop, tenlop, mank;
    ListView_Class_Adapter adapter;

    RecyclerView recyclerView;
    DatabaseHelper db;

    ImageButton back;
    Button btn_del, btn_update;

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
        adapter = new ListView_Class_Adapter(this, malop, tenlop, mank);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displayClass();
        Cursor cursor = db.showClass();
        if (cursor.getCount() == 0) {

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
        btn_update = findViewById(R.id.btn_update);
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getID_ToUpdate();
            }
        });

        btn_del =  findViewById(R.id.btn_delete);
        btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteClass();
            }
        });


    }

    private void displayClass() {
        Cursor cursor = db.showClass();
        while (cursor.moveToNext()) {
            malop.add(cursor.getString(0));
            mank.add(cursor.getString(1));
            tenlop.add(cursor.getString(2));

        }
    }

    private void deleteClass() {
        // anh xa
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.delete_class);
        dialog.setCanceledOnTouchOutside(false);
        DatabaseHelper db = new DatabaseHelper(this);

        Button confirm = dialog.findViewById(R.id.confirm_btn_yes);
        Button cancel = dialog.findViewById(R.id.confirm_btn_no);

        EditText idToDEL = dialog.findViewById(R.id.Edttxt_input_malop);


        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = idToDEL.getText().toString();
                boolean kq = db.deleteClass(id);
                if (kq == true) {
                    Toast.makeText(getApplicationContext(), "Đã xóa Lớp", Toast.LENGTH_LONG).show();
                }
                finish();
            }
        });
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

        EditText malop = dialog.findViewById(R.id.id_malop);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = malop.getText().toString();
                updateClass(id);
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

    private void updateClass(String malop) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_update_class);
        dialog.setCanceledOnTouchOutside(false);
        DatabaseHelper db = new DatabaseHelper(this);


        Button adding = dialog.findViewById(R.id.confirm_btn_yes);
        Button canceling = dialog.findViewById(R.id.confirm_btn_no);

        EditText mahk = dialog.findViewById(R.id.edtext_update_mank);
        EditText tenlop = dialog.findViewById(R.id.edtext_update_tenlop);

        adding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String TENLOP = tenlop.getText().toString();
                String MANK = mahk.getText().toString();
                Boolean kq = db.updateClass(malop, TENLOP, MANK);
                if (kq == true) {
                    Toast.makeText(getApplicationContext(), "Chỉnh sửa lớp thành công", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(getApplicationContext(), Activity_Show_ListClass.class);
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