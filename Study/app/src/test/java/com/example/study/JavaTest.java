package com.example.study;

import org.junit.Assert;
import org.junit.Test;

public class JavaTest {
    @Test
    public void tets1(){
        Assert.assertEquals(4, 2+2);
    }

    @Test public void testChild(){
        PersonJava pj = new ChildJava("daniel");

        SingletonJava sj = SingletonJava.getInstance();

        //객체의 메소드 사용 가능
        sj.log("헬로우");

        Assert.assertEquals(10, pj.getAge());
        Assert.assertEquals("daniel", pj.getName());

        FruitJava fj = new FruitJava();
        fj.fruitName = "사과";
        fj.fruitDescription = "맛있다";

        //객체를 넘기면 자동으로 toString() 메소드 호출
        System.out.println(fj);
    }
}
