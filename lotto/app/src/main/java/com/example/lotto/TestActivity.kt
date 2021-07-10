package com.example.lotto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test) // 어느 화면을 보여줄지 설정

        // 버튼과 같은 View가 클릭되었을 때 실행될 listener를 등록하는 메소드가 setOnClickListener이다.
        findViewById<View>(R.id.button).setOnClickListener{
            // MainActivity를 시작하는 Intent를 생성한다.
            val intent = Intent(this@TestActivity, MainActivity::class.java)
            // intent 를 사용하여 Activity를 시작한다.
            println("메인으로 이동")
            startActivity(intent)
        }

        findViewById<View>(R.id.button3).setOnClickListener{
            val intent = Intent(this@TestActivity, NameActivity::class.java)
            println("네임으로 이동")
            startActivity(intent)
        }

        findViewById<View>(R.id.button4).setOnClickListener{
            val intent = Intent(this@TestActivity, NameActivity::class.java)
            println("결과화면으로 이동")
            startActivity(intent)
        }
    }
    /*
    * xml에서 참조할 수 있게 메소드를 정의
    * */
    fun goConstellation(view: View){
        val intent = Intent(this@TestActivity, ConstellationActivity::class.java)
        println("콘스텔레이션으 이동")
        startActivity(intent)
    }
}