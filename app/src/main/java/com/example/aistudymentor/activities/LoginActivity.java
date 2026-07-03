package com.example.aistudymentor.activities;

import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.aistudymentor.models.UserModel;
import com.example.aistudymentor.repository.UserRepository;

import java.io.FileInputStream;

public class LoginActivity extends AppCompatActivity {
    EditText edtUsername, edtPassword;
    Button btnLogin;
    TextView tvSignup;
    UserRepository userRepository;
    SharedPreferences sharedPrf;
    private String account = "";
    private int idUser = 0;

    @Override
    protected void onStart() {
        super.onStart();
        if (idUser > 0 && !TextUtils.isEmpty(account)){
            Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_layout_login);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin    = findViewById(R.id.btnSubmit);
        tvSignup    = findViewById(R.id.tvSignup);
        userRepository = new UserRepository(LoginActivity.this);
        sharedPrf = getSharedPreferences("USER_INFO", MODE_PRIVATE);
        if (sharedPrf != null) {
            account = sharedPrf.getString("USERNAME_USER","");
            idUser  = sharedPrf.getInt("ID_USER", 0);
        }

        tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // chuyen sang trang dang ky tai khoan
                Intent signup = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(signup);
            }
        });
        checkLoginWithDatabase();
    }
    private void checkLoginWithDatabase(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = edtUsername.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();
                if (TextUtils.isEmpty(user)){
                    edtUsername.setError("Username is required");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    edtPassword.setError("Password is required");
                    return;
                }
                // kiem tra tai khoan co ton tai trong database hay ko?
                UserModel infoUser = userRepository.loginUser(user, password);
                assert infoUser != null;
                if (infoUser.getId() > 0 && !TextUtils.isEmpty(infoUser.getUsername())){
                    // dang nhap thanh cong
                    // luu thong tin nguoi dung vao SharePreference de biet ai la nguoi da dang nhap cho chuc khac sau nay
                    SharedPreferences sharePrf = getSharedPreferences("USER_INFO",MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharePrf.edit();
                    editor.putInt("ID_USER", infoUser.getId());
                    editor.putString("USERNAME_USER", infoUser.getUsername());
                    editor.putString("EMAIL_USER", infoUser.getEmail());
                    editor.putInt("ROLE_USER", infoUser.getRole());
                    editor.apply();
                    // cho vao trang Menu
                    Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    // dang nhap that bai
                    Toast.makeText(LoginActivity.this, "Username or Password invalid", Toast.LENGTH_SHORT).show();
                }
            }
        });
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
