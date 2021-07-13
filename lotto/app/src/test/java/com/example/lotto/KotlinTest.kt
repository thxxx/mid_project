package com.example.lotto

import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class KotlinTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testAnimalMap(){
        val af = Animal(mutableMapOf(
            "name" to "cat",
            "age" to 20
        ))

        Assert.assertEquals("cat", af.name)
        Assert.assertEquals(20, af.age)

        af.name = "dog"
        af.age = 30

        Assert.assertEquals("dog", af.name)
        Assert.assertEquals(30, af.age)
    }
}