package com.example.anonymoussns

import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ServerValue
import com.squareup.picasso.Picasso


class WriteActivity : AppCompatActivity() {
    var currentBgPosition = 0
    var postId = ""
    /**
     * 배경 리스트데이터
     * res/drawble 디렉토리에 있는 배경 이미지를 uri주소로 사용한다.
     * uri주소로 사용하면 추후 웹에 있는 이미지 URL도 바로 사용이 가능하다.
     * */
    val bgList = mutableListOf(
        "android.resource://com.example.anonymoussns/drawable/default_bg",
        "android.resource://com.example.anonymoussns/drawable/bg2",
        "android.resource://com.example.anonymoussns/drawable/bg3",
        "android.resource://com.example.anonymoussns/drawable/bg4",
        "android.resource://com.example.anonymoussns/drawable/bg5",
        "android.resource://com.example.anonymoussns/drawable/bg6",
        "android.resource://com.example.anonymoussns/drawable/bg7",
        "android.resource://com.example.anonymoussns/drawable/bg8",
        "android.resource://com.example.anonymoussns/drawable/bg9"
    )

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write)

        var rc = findViewById<RecyclerView>(R.id.recyclerView) // <> 안에 뭘 넣는지가 너무 헷갈린다. <- xml보고 그대로 넣으면 됨!
        var sendbutton = findViewById<Button>(R.id.sendButton)
        var input = findViewById<EditText>(R.id.input)

        //actionbar의 타이틀을 "글쓰기"로 변경
        supportActionBar?.title = "글쓰기"

        //recyclerView에서 사용할 레이아웃 매니저를 생성한다.
        val layoutManager = LinearLayoutManager(this@WriteActivity)
        // recyclerView를 횡으로 스크롤 할 것이므로 레이아웃매니저의 방향을 Horizaontal로 설정한다.
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL

        // recyclerView에 레이아웃 매니저를 방금 생성한 layoutManager로 설정한다.
        rc.layoutManager = layoutManager
        // recyclerView에 adapter를 설정한다.
        rc.adapter = MyAdapter()

        // 공유하기 버튼이 클릭된 경우에 이벤트 리스너를 설정한다.
        sendbutton.setOnClickListener{
            if(TextUtils.isEmpty(input.text)){
                Toast.makeText(applicationContext, "메세지를 입력하세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            //Post 객체 생성
            val post = Post()
            //Firebase의 Posts 참조에서 객체를 저장하기 위한 새로운 키를 생성하고 참조는 newRef에 저장
            val newRef = FirebaseDatabase.getInstance().getReference("Posts").push()
            //넣을 내용들
            post.writeTime = ServerValue.TIMESTAMP
            post.bgUri = bgList[currentBgPosition]
            post.message = input.text.toString()
            post.writerId = getMyId()
            post.postId = newRef.key.toString()
            //Post객체를 새로 생성한 참조에 저장
            newRef.setValue(post)
            // 저장 성곤 토스트 알림을 보여주고 액티비티 종료
            Toast.makeText(applicationContext, "저장되었습니다.", Toast.LENGTH_SHORT).show()
            finish()
        }

    }

    /**
     * 디바이스 ID를 반환하는 메소드
     * 글쓴 사람의 ID를 인식합니다.*/
    fun getMyId(): String{
        return Settings.Secure.getString(this.contentResolver, Settings.Secure.ANDROID_ID)
    }

    // RecyclerView에서 사용하는 View 홀더 클래스
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView = itemView.findViewById<ImageView>(R.id.imageView)
        // val imageView = itemView.imageView 를 고쳤다
    }

    // RecyclerView에서
    inner class MyAdapter : RecyclerView.Adapter<MyViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            // 리싸뷰에 사용하는 뷰홀더 클래스를 CARD_BACKGROUND 레이아웃 리소스 파일을 사용하도록 생성한다.
            return MyViewHolder(LayoutInflater.from(this@WriteActivity).inflate(R.layout.card_background, parent, false))
        }

        override fun getItemCount(): Int{
            return bgList.size
        }

        override fun onBindViewHolder(holder:MyViewHolder, position:Int){
            // 이미지 로딩 라이브러리인 피카소 객체로 뷰홀더에 존재하는 이미지 뷰에 이미지 로
            Picasso.get()
                .load(Uri.parse(bgList[position]))
                .fit()
                .centerCrop()
                .into(holder.imageView)

            // 각 배경화면 행이 클릭된 경우에 이벤트 리스너 설정
            holder.itemView.setOnClickListener{
                currentBgPosition = position
                // 이미지 로딩 라이브러리인 피카소 객체로 뷰홀더에 존재하는 글쓰기 배경 이미지뷰에 이미지 로딩
                Picasso.get()
                    .load(Uri.parse(bgList[position]))
                    .fit()
                    .centerCrop()
                    .into(findViewById<ImageView>(R.id.writeBackground))
            }
        }
    }
}
