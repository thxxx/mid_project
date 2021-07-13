package com.example.anonymoussns

class Comment {
    /**
    * 댓글의 아이디
    * */
    var commentId = ""
    /**
    * 댓글의 대상이 되는 글의 ID
    * */
    var postId=""
    /**
     * 댓글 내용
     * */
    var message = ""

    /**
     * 댓글 작성자의 아이디
     * */
    var writerId = ""

    /**
     * 작성 시간
     * */
    var writeTime:Any=Any()
    /**
     * 배경 이미지
     * */
    var bgUri = ""
}