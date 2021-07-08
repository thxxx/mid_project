package com.example.study

import org.junit.Assert
import org.junit.Test

class KotlinTest {
    @Test
    fun test1(){
        Assert.assertEquals(4, 2+2)
    }
    @Test
    fun testUser(){
        val user = User()
        // 닉네임을 대소문자가 섞인 문자열로 저장
        user.nickname = "death Note"
        println("not init")
        // 닉네임이 모두 대문자로 변경되었는지 확인
        Assert.assertEquals("DEATH NOTE", user.nickname)
        Assert.assertNotNull(user.httpText)
    }
    @Test
    fun testUserObservable(){
        val user = User()
        user.name = "john"
        user.name = "daniel"
    }
}