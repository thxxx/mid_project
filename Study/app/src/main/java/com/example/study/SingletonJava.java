package com.example.study;

public class SingletonJava {
    // 생성자를 private으로 감춘다.
    private SingletonJava(){}

    // 생성된 객체도 private으로 감추고 프로그램 시작할 때 초기화 한다.
    // 인스턴스 생성방법은 처음 사용시 초기화, 쓰레드 동기화 방법 등 여러가지가 있다.
    private static SingletonJava oneInstance = new SingletonJava();

    //외부에서 instance에 접근할 수 있는 방법을 제공해준다.
    public static SingletonJava getInstance(){
        return oneInstance;
    }

    public void log(String text){
        System.out.println(text);
    }
}
