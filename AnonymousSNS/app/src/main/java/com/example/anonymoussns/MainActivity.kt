package com.example.anonymoussns

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import android.util.Log

class MainActivity : AppCompatActivity() {
    // 로그에 TAG로 사용할 문자열
    val TAG = "MainActivity"

    // 파이어베이스의 test 키를 가진 데이터의 참조 객체를 가져온다.
    val ref = FirebaseDatabase.getInstance().getReference("test")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //값의 변경이 있는 경우의 이벤트리스너를 추가한다.
        ref.addValueEventListener(object:ValueEventListener{
            // 데이터 읽기가 취소된 경우 호출된다.
            // ex) 해당 데이터의 권한이 없는 경우
            override fun onCancelled(error:DatabaseError){
                error.toException().printStackTrace()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                // test 키를 다진 데이터 스냅샷에서 값을 읽고 문자열로 변경한다.
                val message = snapshot.value.toString()
                // 읽은 문자열을 로깅
                Log.d(TAG, message)
                // Firebase에서 전달받은 메세지로 제목을 변경한다.
                supportActionBar?.title = message
            }
        })
    }
}