package com.example.aistudymentor.activities.categories;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aistudymentor.R;
import com.example.aistudymentor.activities.MenuActivity;
import com.example.aistudymentor.repository.CategoryRepository;

public class AddCategoryActivity extends AppCompatActivity {
    EditText edtCategoryName, edtDescription;
    Button btnSave, btnBack;
    CategoryRepository categoryRepository;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        edtCategoryName = findViewById(R.id.edtCategoryName);
        edtDescription  = findViewById(R.id.edtCategoryDescription);
        btnSave = findViewById(R.id.btnSaveNewCategory);
        btnBack = findViewById(R.id.btnBackNewCategory);
        categoryRepository = new CategoryRepository(AddCategoryActivity.this);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                String categoryName = edtCategoryName.getText().toString().trim();
                if (TextUtils.isEmpty(categoryName)) {
                    edtCategoryName.setError("Category name is required");
                    return;
                }
                String description = edtDescription.getText().toString().trim();
                long insert = categoryRepository.saveNewCategory(categoryName, description);
                if (insert == -1){
                    // co loi xay ra, khong them duoc du lieu
                    Toast.makeText(AddCategoryActivity.this, "Created Fail", Toast.LENGTH_SHORT).show();
                    return;
                }
                // thanh cong
                Toast.makeText(AddCategoryActivity.this, "Created Successfully", Toast.LENGTH_SHORT).show();
                // quay ve lai trang menu
                Intent intent = new Intent(AddCategoryActivity.this, MenuActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("MENU_TAB","category");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddCategoryActivity.this, MenuActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("MENU_TAB","category");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
