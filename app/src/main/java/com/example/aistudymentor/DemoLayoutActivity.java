package com.example.aistudymentor;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DemoLayoutActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_layout_login);
        // tim button submit ngoai giao dien
        Button btnSubmit = findViewById(R.id.btnSubmit);
        // bat su kien khi nguoi dung click vao button
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DemoLayoutActivity.this, "Dang nhap that bai", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
