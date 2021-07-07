package com.example.study

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_control.*

class ControlKotlinActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        controlButtonK.setOnClickListener {
            val number = numberField.text.toString().toInt()

            when {
                number % 2 == 0 -> Toast.makeText(applicationContext, "${number}는 2의 배수입니다.", Toast.LENGTH_LONG).show();
                number % 2 == 0 -> Toast.makeText(applicationContext, "${number}는 2의 배수입니다.", Toast.LENGTH_LONG).show();
                else -> Toast.makeText(applicationContext, "2의 배수입니다.", Toast.LENGTH_LONG).show();
            }
        }
    }
}