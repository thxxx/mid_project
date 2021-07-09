package com.example.study

import org.junit.Assert
import org.junit.Test

class KotlinTest {
    @Test
    fun test1(){
        Assert.assertEquals(4, 2+2)
    }
    @Test
    fun testUserObservable(){
        val user = User()
        user.name = "john"
        user.name = "daniel"

        println(sum(2,5))
        Assert.assertEquals(4, sum(1,3))

        val exp = {x:Int, y:Int ->
            {z:Int -> (x + y)*z}
        }

        val sk = SingletonKotlin
        sk.log("된다고?")
    }
    @Test
    fun testCollection(){
        var list = listOf(1, "2", 3.5 ,4, 6)

        // filter : 컬렉션에서 특정 조건이 맞는 항목만 추출하여 새로운 컬렉션을 만든다.
        println(list.filter { item -> item is Int})

        // 람다에서는 파라미터가 하나인 경우 생략하고 it으로 접근 가능하다.
        println(list.filter {it is Int})

        // map : 컬렉션에서 아이템을 변환하여 새로운 컬렉션을 만든다.
        println(list.map {"value:${it}"})

        //filter에서 반환된 컬렉션을 map으로 반환
        println(list.filter {it is Int}.map {"value:${it}"})

        // 컬렉션을 그룹화하여 Map<String, List<T>>형태로 만든다. 아래코드는 각 아이템의 클래스별로 그룹화 된다.
        val map = list.groupBy { it.javaClass }
        println(map)

        //컬렉션안에 컬렉션
        var list2 = listOf(listOf(1,2), listOf(3,4))
        println(list2.map{"value:${it}"})

        //flatmap으로 리스트를 평평하게 만들고 반환한다.
        println(list2.flatMap { it.toList() })
    }
}