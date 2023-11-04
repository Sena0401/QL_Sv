package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Activity_Add_Class extends AppCompatActivity {
    EditText MALOP, TENLOP, MANIENKHOA;

    Button buttonAddClass;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);

        // Ánh xạ các trường dữ liệu và nút từ layout XML
        MALOP = findViewById(R.id.editTextMALOP);
        TENLOP = findViewById(R.id.editTextTENLOP);
        MANIENKHOA = findViewById(R.id.editTextMANIENKHOA);


        buttonAddClass = findViewById(R.id.btnAddClass);

        databaseHelper = new DatabaseHelper(this);

        // Xử lý sự kiện khi người dùng nhấn nút để thêm sinh viên mới
        buttonAddClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Lấy các trường dữ liệu khác tương tự ở đây
                String malop = MALOP.getText().toString();
                String tenlop = TENLOP.getText().toString();
                String mank = MANIENKHOA.getText().toString();
                // Đặt giá trị cho các trường dữ liệu khác tương tự ở đây//
                Boolean addStudent = databaseHelper.Add_Class(malop, tenlop, mank);
                if (addStudent == true) {
                    // Thêm thành công
                    // Xóa dữ liệu sau khi thêm
                    TENLOP.setText("");
                    MALOP.setText("");
                    MANIENKHOA.setText("");
                    Toast.makeText(Activity_Add_Class.this, "Thêm Thành Công", Toast.LENGTH_LONG).show();
                } else {
                    // Thêm thất bại
                    Toast.makeText(Activity_Add_Class.this, "Thêm Thất Bại", Toast.LENGTH_LONG).show();
                }

                databaseHelper.close();


            }
        });
    }
}
