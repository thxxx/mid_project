package com.example.study;

import android.widget.Toast;

public class ToastUtilJava {
    public static void toastShort(String message){
        Toast.makeText(MainApplication.getAppContext(), message, Toast.LENGTH_SHORT).show();
    }
    public static void toastLong(String message){
        /**
         * 이러면 설명이 되나?
         * 오래 보여주는 토스트 메시지
         * */
        Toast.makeText(MainApplication.getAppContext(), message, Toast.LENGTH_LONG).show();
    }
}
