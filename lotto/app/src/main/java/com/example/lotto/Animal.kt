package com.example.lotto

class Animal(val map:MutableMap<String, Any?>) {
    // 프로퍼티를 map객체로 위임한다. map 객체에서 값을 읽고,
    // 값을 변경하면 map 객체에서 값이 변경된다.
    var name:String by map
    var age:Int by map
}