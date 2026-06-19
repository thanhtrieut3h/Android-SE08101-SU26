package com.example.aistudymentor.activities;

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

import java.io.FileInputStream;

public class LoginActivity extends AppCompatActivity {
    EditText edtUsername, edtPassword;
    Button btnLogin;
    TextView tvSignup;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_layout_login);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin    = findViewById(R.id.btnSubmit);
        tvSignup    = findViewById(R.id.tvSignup);
        tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // chuyen sang trang dang ky tai khoan
                Intent signup = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(signup);
            }
        });
        checkLogin();
    }
    private void checkLogin(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edtUsername.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();
                if (TextUtils.isEmpty(username)){
                    edtUsername.setError("Username is required");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    edtPassword.setError("Password is required");
                    return;
                }
                // xu ly doc du lieu tu trong file
                try {
                    FileInputStream inputStream = openFileInput("account.txt");
                    StringBuilder builder = new StringBuilder();
                    int read = -1;
                    // doc tung ky tu trong file (account.txt)
                    while ((read = inputStream.read()) != -1){
                        builder.append((char) read);
                    }
                    inputStream.close(); // dong file da mo
                    // kiem tra thong tin nguoi dung dang nhap co ton tai trong file account.txt hay khong ?
                    String[] accounts = null;
                    accounts = builder.toString().trim().split("\n");
                    // co 1 mang chua toan bo thong tin tai khoan nguoi dung
                    boolean checkFlag = false;
                    for (int i = 0; i < accounts.length; i++){
                        String user = accounts[i].substring(0, accounts[i].indexOf("|"));
                        String pass = accounts[i].substring(accounts[i].indexOf("|")+1);
                        if (user.equals(username) && pass.equals(password)){
                            // dang nhap thanh cong
                            checkFlag = true;
                            break;
                        }
                    }
                    if (checkFlag){
                        Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                        Intent menuAc = new Intent(LoginActivity.this, MenuActivity.class);
                        startActivity(menuAc);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Account invalid", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
