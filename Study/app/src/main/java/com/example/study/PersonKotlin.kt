package com.example.study

class Person {
    //생성자에서 이름을 받는다.
    var age:Int = 0

    var nickname:String = ""
        set(value){
            field = value.toLowerCase()
        }
}