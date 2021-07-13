package com.example.anonymoussns

import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase


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

    var rc = findViewById<RecyclerView>(R.id.recyclerView) // <> 안에 뭘 넣는지가 너무 헷갈린다.

    val posts :MutableList<Post> = mutableListOf()

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
        rc.layoutManager = layoutManager
        // recyclerView에 adapter를 설정한다.
        rc.adapter = MyAdapter()

        //Firebase 에서 Post 데이터를 가져온 후 posts 변수에 저장
        FirebaseDatabase.getInstance().getReference("/Posts")
            .orderByChild("writeTime").addChildEventListener(object: ChildEventListener{
                //글이 추가된 경우
                override fun onChildAdded(snapshot: DataSnapshot?, prevChildKey:String?){
                    snapshot?.let { snapshot ->
                        // snapshot 의 데이터를 Post 객체로 가져옴
                        val post = snapshot.getValue(Post::class.java)
                        post?.let{
                            //새 글이 마지막 부분에 추가된 경우
                            if (prevChildKey == null){
                                // 글 목록을 저장하는 변수에 post 객체 추가
                                posts.add(it)
                                // RecyclerView의 adapter 에 글이 추가된 것을 알림
                                rc.adapter?.notifyItemInserted(posts.size - 1)
                            } else{
                                //글이 중간에 삽입된 경우 prevChildKet로 한단계 앞의 데이터의 위치를 찾은 데이터를 추가한다.뒤
                                val prevIndex = posts.map {it.postId}.indexOf(prevChildKey)
                                posts.add(prevIndex + 1, post)
                                // RecyclerView의 adapter에 글이 추가된 것을 알림
                                rc.adapter?.notifyItemInserted(prevIndex + 1)
                            }
                        }
                    }
                }

                override fun onChildChanged(snapshot: DataSnapshot, prevChildKey: String?) {
                    snapshot?.let {
                        snapshot -> //snapshot의 데이터를 Post 객체로 가져옴
                        val post = snapshot.getValue(Post::class.java)
                        post?.let {
                                post -> //글이 변경된 경우 글의 앞의 데이터 인덱스에 데이터를 변경한다.
                            val prevIndex = posts.map {it.postId}.indexOf(prevChildKey)
                            posts[prevIndex + 1] = post
                            rc.adapter?.notifyItemChanged(prevIndex + 1)
                        // 내가 생성한 posts를 가지고 지지고 볶고 하네?
                        }

                    }
                }


            }
            )


    }

    // RecyclerView에서 사용하는 View 홀더 클래스
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView = itemView.findViewById<ImageView>(R.id.imageView)
        // val imageView = itemView.imageView 를 고쳤다
    }

    // RecyclerView에서
    inner class MyAdapter : RecyclerView.Adapter<MyViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            // TODO("Not yet implemented")
            // 리싸뷰에 사용하는 뷰홀더 클래스를 CARD_BACKGROUND 레이아웃 리소스 파일을 사용하도록 생성한다.
            return MyViewHolder(LayoutInflater.from(this@WriteActivity)).inflater(R.layout.card_background, parent, false)
        }
    }

    override fun getItemCount(): Int{
        return bgList.size
    }

    override fun onBindViewHolder(holder:MyViewHolder, position:Int){
        TODO("Not yet implemented")
    }
    
    /**
     * 디바이스 ID를 반환하는 메소드
     * 글쓴 사람의 ID를 인식합니다.*/
    fun getMyId(): String{
        return Settings.Secure.getString(this.contentResolver, Settings.Secure.ANDROID_ID)
    }


}