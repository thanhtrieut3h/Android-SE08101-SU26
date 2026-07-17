package com.example.aistudymentor.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.aistudymentor.helpers.Helper;
import com.example.aistudymentor.sqlite.DbHelper;

public class CategoryRepository extends DbHelper {
    public CategoryRepository(@Nullable Context context) {
        super(context);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public long saveNewCategory(String name, String description){
        String currentDate = Helper.getCurrentDate();
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME_CATEGORY, name);
        values.put(DESCRIPTION_CATEGORY, description);
        values.put(STATUS_CATEGORY, 1);
        values.put(CREATED_AT, currentDate);
        long insert = db.insert(CATEGORY_TABLE, null, values);
        db.close();
        return insert;
    }
}
