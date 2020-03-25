package com;

public class Dev_20_03_25 {

    /*주제: Thread란?
    CPU가 독립적으로 처리하는 하나의 작업 단위이다. 요즘 세대는 대부분 멀티코어, 멀티쓰레드 CPU가 주로 사용되기 때문에
    프로그램 또한 멀티쓰레드를 사용하는것이 적합하다.
     */

    //메인 실행함수
    public static void main(String[] args){
        new Dev_20_03_25().Thread_Lamda();
        new Thread_Example().run();
        new Thread_Example_2().run();
    }

    //쓰레드 생성 방법1 - 람다
    //필자가 자주 사용하는 방법이다.
    public void Thread_Lamda(){
        new Thread( () -> {
            System.out.println("This is new Thread!");
        }).start();
    }
}
//쓰레드 생성 방법2 - Thread 클래스 상속
class Thread_Example extends Thread {
    public void run(){
        System.out.println("This is new Thread!");
    }
}
//쓰레드 생성 방법3 - Runnable 인터페이스
class Thread_Example_2 implements Runnable{
    @Override
    public void run() {
        System.out.println("This is new Thread!");
    }
}
