package com.example.aistudymentor;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class FirstTestActivity extends AppCompatActivity {
    private final String ACTIVITY_LOG = "ACTIVITY_LOG";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // phuong thuc nay se tu dong chay khi 1 Activity dc khoi dong(kich hoat)
        // phuong thuc nay se lam noi de load giao dien - hien thi giao dien
        // phuong thuc nay se lam noi xu ly cac logic
        setContentView(R.layout.activity_first_test);
        Log.i(ACTIVITY_LOG, "*** onCreate finished ***");
        // tim phan tu Button ngoai View
        Button btnClickMe = findViewById(R.id.btnClickMe);
        // bat su kien "click" cho button
        btnClickMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // chuyen sang Second Activity
                Intent intent = new Intent(FirstTestActivity.this, SecondTestActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onPause() {
        super.onPause();
        // phuong thuc nay se duoc goi khi chuan bi co 1 Activity moi xuat hien
        Log.i(ACTIVITY_LOG, "*** onPause - Second Test Activity will show");
    }
    @Override
    protected void onStop() {
        super.onStop();
        // phuong thuc nay se duoc goi khi Activity hien tai bi an di nhuong cho cho Activity moi
        Log.i(ACTIVITY_LOG, "*** onStop - First Test Activity Hide");
    }

    @Override
    protected void onStart() {
        super.onStart();
        // phuong thuc se chay ngay truoc khi giao dien duoc hien thi
        Log.i(ACTIVITY_LOG, "*** onStart finished ***");
    }

    @Override
    protected void onResume() {
        super.onResume();
        // phuong thuc nay se duoc goi ngay sau khi Activity bat dau co the tuong tac voi nguoi dung
        Log.i(ACTIVITY_LOG, "*** onResume finished ***");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        // phuong thuc nay se duoc goi khi hien thi tro lai 1 activity da bi an di truoc do
        Log.i(ACTIVITY_LOG,"*** onRestart - First Test Activity Back ****");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // phuong thuc nay se duoc goi khi thoat hoan toan ung dung
        Log.i(ACTIVITY_LOG, "*** onDestroy - Application Exit ***");
    }
}
