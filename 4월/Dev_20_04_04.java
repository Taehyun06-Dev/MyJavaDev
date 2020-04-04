package com;

public class Dev_20_04_04 {

    /*주제: 람다란?
    람다는 식별자 없이 호출이 가능한 함수이다.
    따로 함수를 생성하지 않고 단순 코드상에서 호출이 가능하다.
    형태는 다음과 같다. [(매개변수, ...) -> { 실행문 ... }]
    람다는 형태가 간결하고 사용하기 편하지만, 디버깅이 까다롭고 남발하면 코드가 지저분해질 수 있다.
     */

    //메인 실행 함수
    public static void main(String[] args) {
        new Dev_20_04_04().Lamda_Example();
        new Dev_20_04_04().non_Lamda();
    }

    public void Lamda_Example(){
        //람다를 활용한 쓰레드 생성
        new Thread(() ->{
            System.out.println("hello! this is lamda-thread!");
        });
    }

    public void non_Lamda(){
        //일반적인 쓰레드 생성
        Thread thread = new Thread() {
            public void run(){
                System.out.println("hello! this is non-lamda-thread!");
            }
        };
        thread.start();
    }
}
