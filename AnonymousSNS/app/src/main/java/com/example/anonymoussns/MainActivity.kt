package com.example.anonymoussns

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import org.joda.time.DateTime
import org.joda.time.Days
import org.joda.time.Hours
import org.joda.time.Minutes
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    // 파이어베이스의 test 키를 가진 데이터의 참조 객체를 가져온다.

    val posts :MutableList<Post> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fab = findViewById<FloatingActionButton>(R.id.floatingActionButton)

        fab.setOnClickListener{
            val intent = Intent(this@MainActivity, WriteActivity::class.java)
            startActivity(intent)
        }


        var rc = findViewById<RecyclerView>(R.id.recyclerView) // <> 안에 뭘 넣는지가 너무 헷갈린다. <- xml보고 그대로 넣으면 됨!

        //recyclerView에서 사용할 레이아웃 매니저를 생성한다.
        val layoutManager = LinearLayoutManager(this@MainActivity)
        // recyclerView를 횡으로 스크롤 할 것이므로 레이아웃매니저의 방향을 Horizaontal로 설정한다.
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL

        // recyclerView에 레이아웃 매니저를 방금 생성한 layoutManager로 설정한다.
        rc.layoutManager = layoutManager
        // recyclerView에 adapter를 설정한다.
        rc.adapter = MyAdapter()

        //Firebase 에서 Post 데이터를 가져온 후 posts 변수에 저장
        FirebaseDatabase.getInstance().getReference("/Posts")
            .orderByChild("writeTime").addChildEventListener(object: ChildEventListener {
                //글이 추가된 경우
                override fun onChildAdded(snapshot: DataSnapshot, prevChildKey: String?) {
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

                override fun onChildMoved(snapshot: DataSnapshot, prevChildKey: String?) {
                    //snapshot
                    snapshot?.let{
                        // 스냅샷의 데이터를 POST객체로 가져옴
                        val post = snapshot.getValue(Post::class.java)

                        post?.let {
                            post ->
                            // 기존의 인덱스를 구한다.
                            val existIndex = posts.map {it.postId}.indexOf(post.postId)
                            // 기존에 데이터를 지운다.
                            posts.removeAt(existIndex)
                            rc.adapter?.notifyItemRemoved(existIndex)
                            // prevChildKey가 없는 경우 맨 마지막으로 이동 된 것
                            if(prevChildKey == null){
                                posts.add(post)
                                rc.adapter?.notifyItemChanged(posts.size - 1)
                            } else{
                                // prevChildKey다음 글로 추가
                                val prevIndex = posts.map {it.postId}.indexOf(prevChildKey)
                                posts.add(prevIndex + 1, post)
                                rc.adapter?.notifyItemChanged(existIndex)
                            }
                        }
                    }
                }

                override fun onChildRemoved(snapshot: DataSnapshot) {
                    // 글이 삭제된 경우
                    snapshot?.let{
                        val post = snapshot.getValue(Post::class.java)

                        post?.let { post ->
                            // 기존의 인덱스를 구한다.
                            val existIndex = posts.map {it.postId}.indexOf(post.postId)
                            // 기존에 데이터를 지운다.
                            posts.removeAt(existIndex)
                            rc.adapter?.notifyItemRemoved(existIndex)
                            }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    // 소가 된 경우 에러를 로그로 보여준다.
                    error?.toException()?.printStackTrace()
                }
            }
            )
    }

        // RecyclerView에서 사용하는 View 홀더 클래스
        inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val imageView = itemView.findViewById<ImageView>(R.id.imageView)
            // val imageView = itemView.imageView 를 고쳤다
            val contentsText: TextView = itemView.findViewById<TextView>(R.id.contentsText)
            val timeTextView: TextView = itemView.findViewById<TextView>(R.id.timeTextView)
            val commentCountText: TextView = itemView.findViewById<TextView>(R.id.commentCountText)
        }

        // RecyclerView에서
        inner class MyAdapter : RecyclerView.Adapter<MyViewHolder>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
                // 리싸뷰에 사용하는 뷰홀더 클래스를 CARD_BACKGROUND 레이아웃 리소스 파일을 사용하도록 생성한다.
                return MyViewHolder(LayoutInflater.from(this@MainActivity).inflate(R.layout.card_post, parent, false))
            }

            override fun getItemCount(): Int{
                return posts.size
            }

            override fun onBindViewHolder(holder:MyViewHolder, position:Int){
                val post = posts[position]
                // 이미지 로딩 라이브러리인 피카소 객체로 뷰홀더에 존재하는 이미지 뷰에 이미지 로
                Picasso.get()
                    .load(Uri.parse(post.bgUri))
                    .fit()
                    .centerCrop()
                    .into(holder.imageView)
                holder.contentsText.text = post.message
                holder.timeTextView.text = getDiffTimeText(post.writeTime as Long)
                holder.commentCountText.text = "0"
            }
        }
    fun getDiffTimeText(targetTime: Long): String? {
        val curDateTime = DateTime()
        val targetDateTime = DateTime().withMillis(targetTime)

        val diffDay = Days.daysBetween(curDateTime, targetDateTime).days
        val diffHours = Hours.hoursBetween(targetDateTime, curDateTime).hours
        val diffMinutes = Minutes.minutesBetween(targetDateTime, curDateTime).minutes
        if(diffDay == 0){
            if(diffHours == 0 && diffMinutes == 0){
                return "방금 전"
            }
            return if(diffHours > 0){
                "" + diffHours + "시간 전"
            } else "" + diffMinutes + "분 전"
        }else {
            val format = SimpleDateFormat("yyyy년 MM월 dd일 HH:mm")
            return format.format(Date(targetTime))
        }
    }
}