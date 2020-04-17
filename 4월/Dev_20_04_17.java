package com.year2020.April;

public class Dev_20_04_17 {

    /*주제: volatile 변수
    volatile이란 변수를 Cpu cache가 아닌 Main Memory에 저장하겠다는 것이다.
    volatile을 사용하지 않는 Multi-Thread Apllication은 보통 성능향상을 위해
    Main Memory에서 읽은 값을 Cpu cache에 저장해둔다. 하지만 멀티쓰레드에서
    하나의 변수에 접근할경우 Cpu cache의 값만 바뀌고 실제 Main Memory의 값은 변하지 않는 경우가 있다.
    **volattile 은 Multi-Thread 환경에서 하나의 쓰레드가 Read&Write를 하고, 나머지는 Read Only인 경우에 적합하다.
    비슷한 예로는 synchronized가 있다.
    아래는 최적환경을 구현한 코드이다.
     */

    int share_int = 1;

    //메인 실행 함수
    public static void main(String[] args){
        new Dev_20_04_17().runTask();
    }

    public void runTask(){
        new Thread_1(this).run();
        new Thread_2(this).run();
    }

}
class Thread_1 extends Thread{

    public Dev_20_04_17 main;

    public Thread_1(Dev_20_04_17 dev_20_04_17){
        main = dev_20_04_17;
    }

    @Override
    public void run(){
        main.share_int++;
    }
}
class Thread_2 extends Thread{

    public Dev_20_04_17 main;

    public Thread_2(Dev_20_04_17 dev_20_04_17){
        main = dev_20_04_17;
    }

    @Override
    public void run(){
        main.share_int--;
    }
}
