package com.example.anonymoussns

class Post {
    /**
    * 글의 ID
    * */
    var postId = ""

    /**
    * 글 작성자의 ID
    * */
    var writerId = ""

    /**
    * 글의 메세지
    * */
    var message = ""

    /**
    * 글릐 배경 이미지
    * */
    var bgUri = ""
    /**
     * 글이 작성된 시간
     * */
    var writeTime:Any = Any()

    /**
    * 댓글의 개수
    * */
    var commentCount = 0
}