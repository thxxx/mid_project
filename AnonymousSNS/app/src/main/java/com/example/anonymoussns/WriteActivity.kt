package com.example.anonymoussns

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class WriteActivity : AppCompatActivity() {
    /**
     * 배경 리스트데이터
     * res/drawble 디렉토리에 있는 배경 이미지를 uri주소로 사용한다.
     * uri주소로 사용하면 추후 웹에 있는 이미지 URL도 바로 사용이 가능하다.
     * */
    val bgList = mutableListOf(
        "android.resource://com.example.anonymous/drawable/default_bg",
        "android.resource://com.example.anonymous/drawable/bg2",
        "android.resource://com.example.anonymous/drawable/bg3",
        "android.resource://com.example.anonymous/drawable/bg4",
        "android.resource://com.example.anonymous/drawable/bg5",
        "android.resource://com.example.anonymous/drawable/bg6",
        "android.resource://com.example.anonymous/drawable/bg7",
        "android.resource://com.example.anonymous/drawable/bg8",
        "android.resource://com.example.anonymous/drawable/bg9"
    )

    var rc = findViewById<View>(R.id.recyclerView)

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write)

        //actionbar의 타이틀을 "글쓰기"로 변경
        supportActionBar?.title = "글쓰기"

        //recyclerView에서 사용할 레이아웃 매니저를 생성한다.
        val layoutManager = LinearLayoutManager(this@WriteActivity)
        // recyclerView를 횡으로 스크롤 할 것이므로 레이아웃매니저의 방향을 Horizaontal로 설정한다.
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL

        // recyclerView에 레이아웃 매니저를 방금 생성한 layoutManager로 설정한다.
        rc.setlayoutManager = layoutManager
        // recyclerView에 adapter를 설정한다.
        rc.adapter = MyAdapter()
    }

    // RecyclerView에서 사용하는 View 홀더 클래스
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView = itemView.itemView
    }

    inner.class MyAdapter
}