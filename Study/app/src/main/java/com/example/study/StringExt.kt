package com.example.study

fun String.lastString():String {
    return this.get(this.length - 1).toString()
}

class ExtTest{
    //String 클래스에 extFunc라는 확장 함수를 추가한다.
    fun String.extFunc(){
        // 확장함수에 접근 가능하
        println(this.lastString())
    }

    fun method1(){
        //확장함수 extFunc에 접근 가능하다.
        "test m1".extFunc()
    }
}

fun test(){
    "test m2".lastString()
    //여기서는 extFunc에 접근 불가능하다.
    // "test m3".extFunc()
}
