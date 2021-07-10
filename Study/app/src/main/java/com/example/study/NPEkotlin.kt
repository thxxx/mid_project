package com.example.study

class NPEkotlin {
    //코틀린 타입은 Null을 허용하지 않는게 기본 설정이다.
    fun strLenNotNull(str: String):Int{
        return str.length
    }

    fun sln(str:String?):Int?{
        return str?.length
    }

    fun sln2(str:String?):Char{
        // ?.연산자를 사용하여 str이 널이면 "".single()이 반환 된다.
        return str?.get(str.length -1) ?: "".single()
    }

    fun sln3(str:String?) {
        str?.let { println(strLenNotNull(it)) }
    }
}
