package com.example.clothingregistration;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clothingregistration.adaptor.UserAdapter;
import com.example.clothingregistration.database.AppDatabase;
import com.example.clothingregistration.database.entitas.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Button btnAdd;
    private AppDatabase database;
    private UserAdapter userAdapter;
    private List<User> list = new ArrayList<>();
    private AlertDialog.Builder dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.Recycler_view);
        btnAdd = findViewById(R.id.btn_add);

        database = AppDatabase.getInstance(getApplicationContext());
        list.clear();
        list.addAll(database.userDao().getAll());
        userAdapter = new UserAdapter(getApplicationContext(), list);
        userAdapter.setDialog(new UserAdapter.Dialog() {
            @Override
            public void onClick(int position) {
                final CharSequence[] dialogItem = {"Edit", "Hapus"};
                dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        switch (i) {
                            case 0:
                                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                                intent.putExtra("uid", list.get(position).uid);
                                startActivity(intent);
                                break;
                            case 1:
                                User user = list.get(position);
                                database.userDao().delete(user);
                                onStart();
                                break;
                        }
                    }
                });
                dialog.show();
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(userAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddActivity.class));
            }
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onStart() {
        super.onStart();
        list.clear();
        list.addAll(database.userDao().getAll());
        userAdapter.notifyDataSetChanged();
    }
}