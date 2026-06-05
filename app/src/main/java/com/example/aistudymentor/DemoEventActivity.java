package com.example.aistudymentor;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DemoEventActivity extends AppCompatActivity {
    EditText edtData;
    CheckBox cbAgree;
    Button btnSubmit, btnAnswer;
    TextView tvCountText, tvTextData;
    RadioGroup rdgAddress;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_event);
        edtData = findViewById(R.id.edtData);
        cbAgree = findViewById(R.id.cbAgree);
        btnSubmit = findViewById(R.id.btnSubmit);
        tvCountText = findViewById(R.id.tvText);
        tvTextData  = findViewById(R.id.tvTextData);
        rdgAddress  = findViewById(R.id.rdgAddress);
        btnAnswer   = findViewById(R.id.btnAnswer);

        // block EditText and Button
        edtData.setEnabled(false);
        btnSubmit.setEnabled(false);
        // bat su kien khi nguoi dung click vao checkbox
        cbAgree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    edtData.setEnabled(true);
                    btnSubmit.setEnabled(true);
                } else {
                    edtData.setEnabled(false);
                    btnSubmit.setEnabled(false);
                    edtData.setText("");
                }
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = edtData.getText().toString().trim();
                if (TextUtils.isEmpty(data)){
                    edtData.setError("Data is required");
                    return;
                }
                Toast.makeText(DemoEventActivity.this, data, Toast.LENGTH_SHORT).show();
            }
        });
        edtData.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                String data = editable.toString().trim();
                int count = data.length();
                tvCountText.setText(String.valueOf(count));
                tvTextData.setText(data);
                if (count > 10) {
                    edtData.setEnabled(false);
                    cbAgree.setChecked(false);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
        });
        btnAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = rdgAddress.getCheckedRadioButtonId();
                RadioButton rad = findViewById(selectedId); // tim cai radioButton ma nguoi dung chon
                if(rad == null){
                    // chua chon dia chi
                    Toast.makeText(DemoEventActivity.this, "Choose Address, please", Toast.LENGTH_SHORT).show();
                } else {
                    // da chon
                    String address = rad.getText().toString().trim();
                    Toast.makeText(DemoEventActivity.this, "Your address is " + address, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
