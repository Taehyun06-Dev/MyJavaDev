package com;

public class Dev_20_03_22 {

    /*주제: 어제 탐구한 Reflection의 심화 탐구
    어제 Reflection은 JVM최적화가 지원되지 않고 속도가 느리다고 기술하였다.
    그럼 실제로 얼마나 느린지 수치상으로 검증해보도록 하자.
    측정은 밀리세컨드(1970년 1월 1일 0시부터 현재까지의 시간을 밀리세컨드로 나타낸것)의 차이를 이용한다.
    *같은 프로젝트 내부이므로 크게 차이가 나지 않을 수 있다.*
     */

    /*결론
    같은 프로젝트 내에서는 큰 차이가 나지 않았지만, 이론적으로 외부 프로그램에 접근하면 당연히 속도면에서
    Reflection이 뒤쳐질 수 밖에 없다. 이 부분은 나중에 탐구하도록 한다.
     */

    //메인 실행함수
    public static void main(String[] args){
        new Dev_20_03_22().Check_Speed();
    }

    public void Check_Speed(){
        try {
            Class cls = Class.forName("com.Test_Class_2");
            //Reflection을 통해 원격으로 작업을 수행한다.
            cls.getMethod("printTable", long.class).invoke(new Test_Class_2(), System.currentTimeMillis());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

//Relfection을 확인하기 위한 임의의 클래스 생성
class Test_Class_2{

    //메인 실행함수
    public static void main(String[] args){
        new Test_Class_2().printTable(System.currentTimeMillis());
    }

    //구구단 출력
    //인자로 밀리세컨드값을 전송해 소요시간을 측정한다.
    public void printTable(long Current_Milli){
        for(int i = 1; i <= 9; i++){
            System.out.println("==== [ 구구단 "+i+"단 ] ====");
            for(int i2 = 1; i2 <= 9; i2++){
                System.out.println(i+"x"+i2+"="+i*i2);
            }
        }
        //현재 밀리세컨드에서 실행시의 값을 뺄셈하여 실행 시간을 구한다.
        System.out.println("소요시간: "+(System.currentTimeMillis()-Current_Milli)+"ms");
    }
}
