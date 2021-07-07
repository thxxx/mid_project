package com.example.study

import android.widget.Toast

fun toastShort(message: String?) {
    Toast.makeText(MainApplication.getAppContext(), message, Toast.LENGTH_SHORT).show()
}

fun toast(message: String?, length:Int = Toast.LENGTH_SHORT) {
    /**
     * 이러면 설명이 되나?
     * 오래 보여주는 토스트 메시지
     */
    Toast.makeText(MainApplication.getAppContext(), message, Toast.LENGTH_LONG).show()
}

