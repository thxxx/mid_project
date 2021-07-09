package com.example.study;

public class PersonJava {
    private int age = 3;

    // 초기화 된 이후로 수정되지 못한다. By final
    private final String name;

    // 생성자에서 이름을 받는다.
    public PersonJava(String name){
        this.name = name;
    }

    public int getAge(){
        return age;
    }

    public void setAge(int age){
        this.age = age;
    }

    public String getName(){
        return name;
    }
}
