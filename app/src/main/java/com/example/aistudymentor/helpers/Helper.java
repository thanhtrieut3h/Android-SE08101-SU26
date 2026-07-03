package com.example.aistudymentor.helpers;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Helper {
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String getCurrentDate(){
        // lay ra ngay thang hien tai kem theo gio-phut-giay
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ZonedDateTime zone = ZonedDateTime.now();
        return dtf.format(zone);
    }
}
