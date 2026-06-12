package com.example.aistudymentor.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aistudymentor.R;

import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

public class SignUpActivity extends AppCompatActivity {
    EditText edtUsername, edtPassword;
    Button btnSignUp;
    TextView tvLogin;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnSignUp   = findViewById(R.id.btnSubmit);
        tvLogin     = findViewById(R.id.tvLogin);
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Quay ve lai trang dang nhap
                Intent login = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(login);
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // luu tru thong tin tai khoan vao file (su dung file .txt)
                String user = edtUsername.getText().toString().trim();
                String pass = edtPassword.getText().toString().trim();
                if (TextUtils.isEmpty(user)){
                    edtUsername.setError("Username is required");
                    return;
                }
                if (TextUtils.isEmpty(pass)){
                    edtPassword.setError("Password is required");
                    return;
                }
                // xu ly luu du lieu nguoi dung vao file
                try {
                    FileOutputStream outputStream = null;
                    user = user + "|";
                    outputStream = openFileOutput("account.txt", Context.MODE_APPEND);
                    outputStream.write(user.getBytes(StandardCharsets.UTF_8));
                    outputStream.write(pass.getBytes(StandardCharsets.UTF_8));
                    outputStream.write('\n'); // xuong dong
                    edtUsername.setText("");
                    edtPassword.setText("");
                    outputStream.close();
                    Toast.makeText(SignUpActivity.this, "Register successful", Toast.LENGTH_SHORT).show();
                    // quay ve trang dang nhap
                    Intent login = new Intent(SignUpActivity.this, LoginActivity.class);
                    startActivity(login);

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
