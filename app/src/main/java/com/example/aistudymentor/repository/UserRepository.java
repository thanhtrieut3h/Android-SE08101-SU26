package com.example.aistudymentor.repository;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.aistudymentor.helpers.Helper;
import com.example.aistudymentor.models.UserModel;
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

    @SuppressLint("Range")
    public UserModel loginUser(String username, String password){
        UserModel user = new UserModel();
        // SELECT id, username, email, phone, role FROM users WHERE username = ? AND password = ?
        String[] cols = { ID_USER, USERNAME_USER, EMAIL_USER, PHONE_USER, ROLE_USER };
        String condition = USERNAME_USER + " =? AND " + PASSWORD_USER + " =? ";
        String[] params = { username, password };
        SQLiteDatabase db = this.getReadableDatabase(); // doc du lieu
        Cursor data = db.query(USER_TABLE, cols, condition, params, null, null, null);
        if (data.getCount() > 0) {
            data.moveToFirst();
            // do du lieu tu bang vao model
            user.setId(data.getInt(data.getColumnIndex(ID_USER)));
            user.setUsername(data.getString(data.getColumnIndex(USERNAME_USER)));
            user.setEmail(data.getString(data.getColumnIndex(EMAIL_USER)));
            user.setPhoneNumber(data.getString(data.getColumnIndex(PHONE_USER)));
            user.setRole(data.getInt(data.getColumnIndex(ROLE_USER)));
        }
        data.close();
        db.close();
        return  user;
    }
}
