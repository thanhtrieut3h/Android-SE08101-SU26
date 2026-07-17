package com.example.aistudymentor.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    // dinh nghia ten CSDL va phien ban
    private static final String DB_NAME = "study_with_ai";
    private static final int DB_VERSION = 2;

    // Dinh nghia cac bang du lieu
    // Dinh nghia thong tin bang "users" luu tru thong tin tai khoan
    protected static final String USER_TABLE = "users";
    // cac cot(truong) nam trong bang "users"
    protected static final String ID_USER = "id"; // ten cot
    protected static final String USERNAME_USER = "username";
    protected static final String PASSWORD_USER = "password";
    protected static final String EMAIL_USER = "email";
    protected static final String PHONE_USER = "phone_number";
    protected static final String ROLE_USER = "role";

    // tao bang categories
    protected static final String CATEGORY_TABLE = "categories";
    protected static final String ID_CATEGORY = "id";
    protected static final String NAME_CATEGORY = "name";
    protected static final String DESCRIPTION_CATEGORY = "descriptions";
    protected static final String STATUS_CATEGORY = "status_category";

    // 2 cot ve thoi gian tao va cap nhat
    protected static final String CREATED_AT = "created_at";
    protected static final String UPDATED_AT = "updated_at";

    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // tao bang users
        String users = " CREATE TABLE " + USER_TABLE + " ( "
                       + ID_USER + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                       + USERNAME_USER + " VARCHAR(30) NOT NULL, "
                       + PASSWORD_USER + " VARCHAR(255) NOT NULL, "
                       + EMAIL_USER    + " VARCHAR(60) NOT NULL, "
                       + PHONE_USER    + " VARCHAR(30), "
                       + ROLE_USER     + " TINYINT DEFAULT(1), "
                       + CREATED_AT    + " DATETIME, "
                       + UPDATED_AT    + " DATETIME ) ";

        String categories = " CREATE TABLE " + CATEGORY_TABLE + " ( "
                            + ID_CATEGORY + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + NAME_CATEGORY + " VARCHAR(100) NOT NULL, "
                            + DESCRIPTION_CATEGORY + " VARCHAR(200), "
                            + STATUS_CATEGORY      + " TINYINT DEFAULT(1), "
                            + CREATED_AT    + " DATETIME, "
                            + UPDATED_AT    + " DATETIME ) ";

        db.execSQL(users); // chay lenh sql de tao bang "users"
        db.execSQL(categories);  // chay lenh sql de tao bang "categories"
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE); // xoa bang du lieu
            db.execSQL("DROP TABLE IF EXISTS " + CATEGORY_TABLE); // xoa bang du lieu
            onCreate(db); // tao lai bang CSDL
        }
    }
    // class nay giup tao ra CSDL va cac bang du lieu nam trong CSDL
}
