package com.example.javastudio;

public class Car {
    private int currentSpeed = 0;
    private int maxSpeed = 100;

    private int brakeSpeed = 4;

    private int acceleration = 3;

    // 생성자
    public Car(int acceleration, int maxSpeed, int brakeSpeed){
        this.acceleration = acceleration;
        this.maxSpeed = maxSpeed;
        this.brakeSpeed = brakeSpeed;
    }

    public void brakePedal() {
        currentSpeed = currentSpeed - brakeSpeed;
        if(currentSpeed < 0){
            currentSpeed = 0;
        }
    }

    public String getCurrentSpeedText() {
        return "현재 속도는" + this.currentSpeed + "km/h 입니다.";
    }
}
