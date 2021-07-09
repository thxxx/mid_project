package com.example.study;

public class ChildJava extends PersonJava {
    private int age = 10;

    public ChildJava(String name) {
        super(name);
    }

    @Override
    public int getAge() {
        return age;
    }
}
