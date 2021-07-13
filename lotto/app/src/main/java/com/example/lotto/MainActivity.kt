package com.example.lotto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Toast.makeText(applicationContext, "Main 액티비티 입니다.", Toast.LENGTH_SHORT).show()
    }

    fun getRandomLottoNumber(): Int{
        return Random().nextInt(45) + 1
    }

    fun gRLNs(): MutableList<Int>{
        // 무작위로 생성된 로또 번호를 저장할 가변 리스트 생성
        val lottoNumbers = mutableListOf<Int>()

        // 6번 반복하는 for문
        for(i in 1..6){
            lottoNumbers.add(getRandomLottoNumber())
        }

        return lottoNumbers
    }

}