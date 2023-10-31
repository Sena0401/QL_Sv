package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Activity_Add_Student extends AppCompatActivity {
    EditText editTextMASV,editTextMALOP, editTextHOTEN, editTextNGAYSINH, editTextGIOITINH, editTextDANTOC, editTextDIACHI, editTextSODIENTHOAI, editTextTHANHPHO;

    Button buttonAddStudent;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        // Ánh xạ các trường dữ liệu và nút từ layout XML
        editTextMASV = findViewById(R.id.editTextMASV);
        editTextMALOP = findViewById(R.id.editTextMALOP);
        editTextHOTEN = findViewById(R.id.editTextHOTEN);
        editTextNGAYSINH = findViewById(R.id.editTextNGAYSINH);
        editTextGIOITINH = findViewById(R.id.editTextGIOITINH);
        editTextDANTOC = findViewById(R.id.editTextDANTOC);
        editTextDIACHI = findViewById(R.id.editTextDIACHI);
        editTextSODIENTHOAI = findViewById(R.id.editTextSODIENTHOAI);
        editTextTHANHPHO = findViewById(R.id.editTextTHANHPHO);

        buttonAddStudent = findViewById(R.id.btnAddStudent);

        databaseHelper = new DatabaseHelper(this);

        // Xử lý sự kiện khi người dùng nhấn nút để thêm sinh viên mới
        buttonAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO:

                //  Lấy các trường dữ liệu khác tương tự ở đây
                String MASV = editTextMASV.getText().toString();
                String MALOP = editTextMALOP.getText().toString();
                String HOTEN = editTextHOTEN.getText().toString();
                String NGAYSINH = editTextNGAYSINH.getText().toString();
                String GIOITINH = editTextGIOITINH.getText().toString();
                String DIACHI = editTextDIACHI.getText().toString();
                String DANTOC = editTextDANTOC.getText().toString();
                String SODIENTHOAI = editTextSODIENTHOAI.getText().toString();
                String THANHPHO = editTextTHANHPHO.getText().toString();



                // Đặt giá trị cho các trường dữ liệu khác tương tự ở đây

//
                Boolean addStudent = databaseHelper.Add_student(MASV,MALOP, HOTEN, NGAYSINH, GIOITINH, DIACHI, DANTOC, SODIENTHOAI, THANHPHO);


                if (addStudent == true) {
                    // Thêm thành công
                    // Xóa dữ liệu sau khi thêm
                    editTextMASV.setText("");
                    editTextMALOP.setText("");
                    editTextHOTEN.setText("");
                    editTextNGAYSINH.setText("");
                    editTextGIOITINH.setText("");
                    editTextDIACHI.setText("");
                    editTextDANTOC.setText("");
                    editTextSODIENTHOAI.setText("");
                    editTextTHANHPHO.setText("");
                    Toast.makeText(Activity_Add_Student.this, "Thêm Thành Công", Toast.LENGTH_LONG).show();

                } else {
                    // Thêm thất bại
                    Toast.makeText(Activity_Add_Student.this, "Thêm Thất Bại", Toast.LENGTH_LONG).show();
                }

                databaseHelper.close();


            }
        });
    }
}
