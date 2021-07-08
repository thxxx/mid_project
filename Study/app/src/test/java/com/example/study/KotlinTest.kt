package com.example.study

import org.junit.Assert
import org.junit.Test

class KotlinTest {
    @Test
    fun test1(){
        Assert.assertEquals(4, 2+2)
    }
    @Test
    fun testGetSet(){
        val pk = Person()
        pk.nickname = "HoJin"

        Assert.assertEquals("hojin", pk.nickname)
    }
}