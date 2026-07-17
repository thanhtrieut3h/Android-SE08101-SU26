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
import com.example.aistudymentor.models.CategoryModel;
import com.example.aistudymentor.sqlite.DbHelper;

import java.util.ArrayList;

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
    @SuppressLint("Range")
    public ArrayList<CategoryModel> getAllCategories(){
        ArrayList<CategoryModel> categories = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + CATEGORY_TABLE + " ORDER BY " + CREATED_AT + " DESC ", null);
        if (data.getCount() > 0){
            if (data.moveToFirst()){
                do {
                    categories.add(
                            new CategoryModel(
                                    data.getInt(data.getColumnIndex(ID_CATEGORY)),
                                    data.getString(data.getColumnIndex(NAME_CATEGORY)),
                                    data.getString(data.getColumnIndex(DESCRIPTION_CATEGORY)),
                                    data.getInt(data.getColumnIndex(STATUS_CATEGORY)),
                                    data.getString(data.getColumnIndex(CREATED_AT)),
                                    data.getString(data.getColumnIndex(UPDATED_AT))
                            )
                    );
                } while (data.moveToNext());
            }
        }
        data.close();
        db.close();
        return categories;
    }
}
