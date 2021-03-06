package com.example.study;

import android.app.Application;
import android.content.Context;

// Application 클래스를 상속받는다.
// Application 클래스는 앱이 실행될 때 가장 먼저 실행되며 한개의 인스턴스만 존재한다.
// 앱의 전역적으로 사용하는 상태 정보를 관리하는 기본 클래스.
public class MainApplication extends Application{
    // applicationContext를 바인딩한다.
    private static Context applicationContext;

    // applicationContext를 전역적으로 제공할 메소드
    public static Context getAppContext() {
        return applicationContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // static으로 선언된 applicationContext에 현재 실행중인 applicationContext로 바인딩한다.
        applicationContext = getApplicationContext();
    }
}
