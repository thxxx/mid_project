package com.example.study;

import org.junit.Assert;
import org.junit.Test;

public class JavaTest {
    @Test
    public void tets1(){
        Assert.assertEquals(4, 2+2);
    }

    @Test public void testGetSet(){
        PersonJava pj = new PersonJava("daniel");
        pj.setAge(20);

        Assert.assertEquals(20, pj.getAge());
        Assert.assertEquals("daniel", pj.getName());

        Person pk = new Person();
        pk.setAge(20);
        pk.setNickname("Apple");

        Assert.assertEquals(20, pk.getAge());
        Assert.assertEquals("john", pk.getName());
        Assert.assertEquals("apple", pk.getNickname());
    }
}
