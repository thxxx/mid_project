package com.example.study

import java.io.InputStreamReader
import java.net.URL
import kotlin.properties.Delegates

class User {
    // 닉네임 DelegateString 클래스에 위임
    var nickname by DelegateString()

    val httpText by lazy{
        println("lazy start")
        InputStreamReader(URL("https://www.naver.com").openConnection().getInputStream()).readText()
    }

    var name:String by Delegates.observable(""){
        property, oldValue, newValue ->
        println("기존값: ${oldValue}, 새로 적용될 값: $newValue")
    }
}