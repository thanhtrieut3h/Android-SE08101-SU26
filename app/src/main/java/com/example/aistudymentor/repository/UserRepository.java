package com.example.aistudymentor.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.aistudymentor.helpers.Helper;
import com.example.aistudymentor.sqlite.DbHelper;

public class UserRepository extends DbHelper {
    public UserRepository(@Nullable Context context) {
        super(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public long saveUserAccount(String user, String password, String email, String phone){
        String currentDate = Helper.getCurrentDate();
        ContentValues values = new ContentValues();
        values.put(USERNAME_USER, user);
        values.put(PASSWORD_USER, password);
        values.put(EMAIL_USER, email);
        values.put(PHONE_USER, phone);
        values.put(ROLE_USER, 1);
        values.put(CREATED_AT, currentDate);

        SQLiteDatabase db = this.getWritableDatabase();
        long insert = db.insert(USER_TABLE, null, values);
        db.close();
        return insert;
    }
}
