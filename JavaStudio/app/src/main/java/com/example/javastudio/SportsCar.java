package com.example.javastudio;

public class SportsCar extends Car{
    private boolean isOpenSunRoof = False;

    @Override
    public String getCurrentSpeedText() {
        return "스포츠카 입니다" + super.getCurrentSpeedText();
    }
}
