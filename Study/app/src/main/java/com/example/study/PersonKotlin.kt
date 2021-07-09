package com.example.study

open class Person {
    //생성자에서 이름을 받는다.
    open var age:Int = 0

    var nickname:String = ""
        set(value){
            field = value.toLowerCase()
        }
}