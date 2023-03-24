package com.example.clothingregistration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clothingregistration.database.AppDatabase;
import com.example.clothingregistration.database.entitas.User;

public class AddActivity extends AppCompatActivity {
    private EditText editName, editBrand, editYear, editPrice;
    private Button btnSave;
    private AppDatabase database;
    private int uid = 0;
    private boolean isEdit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        editName = findViewById(R.id.name);
        editBrand = findViewById(R.id.brand);
        editYear = findViewById(R.id.year);
        editPrice = findViewById(R.id.price);
        btnSave = findViewById(R.id.btn_save);

        database = AppDatabase.getInstance(getApplicationContext());

        Intent intent = getIntent();
        uid = intent.getIntExtra("uid", 0);
        if (uid>0) {
            isEdit = true;
            User user =  database.userDao().get(uid);
            editName.setText(user.name);
            editBrand.setText(user.brand);
            editYear.setText(user.year);
            editPrice.setText(user.price);
        }else {
            isEdit = false;
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEdit) {
                    database.userDao().update(uid, editName.getText().toString(), editBrand.getText().toString(), editYear.getText().toString(), editPrice.getText().toString());
                }else {
                    database.userDao().insertAll(editName.getText().toString(), editBrand.getText().toString(), editYear.getText().toString(), editPrice.getText().toString());
                }
                finish();
            }
        });
    }
}