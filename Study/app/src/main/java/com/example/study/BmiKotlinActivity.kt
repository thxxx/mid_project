package com.example.study

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class BmiKotlinActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bmiButton.setOnClickListener{
            //tallField의 값을 읽어온다.
            val tall = tallText.text.toString().toDouble()
            val weight = weightText.text.toString().toDouble()

            val bmi = weight / Math.pow(tall / 100, 2.0)

            resultText.text = "키 : ${tallText.text}, 체중 : ${weightText.text}, BMI: $bmi"

        }
    }
}