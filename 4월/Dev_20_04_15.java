package com.year2020.April;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.Timer;
import java.util.TimerTask;

public class Dev_20_04_15 {

    /*주제: Timer 을 활용하여 반복하는 구문을 만들자.
    매초 또는 일정시간마다 반복되는 구문을 사용해본다.
     */

    //메인 실행 함수
    public static void main(String[] args){
        new Dev_20_04_15().ReaptingTask();
    }

    //Timer
    public void ReaptingTask(){
        //1초마다 실행되는 작업
        new Timer().scheduleAtFixedRate(new TimerTask() {
            public void run() {
                System.out.println("Hello world!");
            }
        }, 0, 1000);
    }
}
