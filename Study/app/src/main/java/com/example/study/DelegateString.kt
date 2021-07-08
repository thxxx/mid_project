package com.example.study

import kotlin.reflect.KProperty

class DelegateString {
    //Setter에서 호출된 값을 저장하는 변수
    var text = ""

    operator fun getValue(thisRef: Any?, property: KProperty<*>):String {
        return text
    }
    operator fun setValue(thisRef: Any?, property: KProperty<*>, value:String) {
        text = value.toUpperCase()
        //세터에 호출될 때의 문자열과 변경 후 문자열 프린트
        println("$value ==> ${text}")
    }
}