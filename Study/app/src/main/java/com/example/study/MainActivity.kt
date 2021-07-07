package com.example.study

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    // It's Kotlin now.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 레이아웃에 button1이라는 ID로 선언된 뷰에 클릭 이벤트 리스너를 등록한다.
        button1.setOnClickListener {
            // 버튼이 클릭되었을때의 코드 작성
            startActivity(Intent(this@MainActivity, BmiJavaActivity::class.java))
        }

        button2.setOnClickListener {
            // 버튼이 클릭되었을때의 코드 작성
            startActivity(Intent(this@MainActivity, BmiKotlinActivity::class.java))
        }

        controlButtonK.setOnClickListener{
            startActivity(Intent(this@MainActivity, ControlKotlinActivity::class.java))
        }

        controlButtonJ.setOnClickListener{
            startActivity(Intent(this@MainActivity, ControlJavaActivity::class.java))
        }
    }
}
