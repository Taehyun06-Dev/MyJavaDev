package com;

public class Dev_20_03_27 {

    /*주제: 피보나치 수열
    피보나치 수열은 앞 2개의 항을 더해 다음 항이 생성 되는 구조이다.
    이탈리아 수학자 피보나치가 발견하였다.
     */

    //메인 실행 함수
    public static void main(String[] args){
       new Dev_20_03_27().Print_fibo(5);
    }

    //피보나치 수열 출력 함수
    public void Print_fibo (int key){
        //합을 계산하기 위한 변수 생성
        int count = 0;
        for(int a = 1; a <= key; a++){
            System.out.println(Fibo_Num(a));
            count = count  + Fibo_Num(a);
        }
        System.out.println("Total Sum is "+count);
    }

    //피보나치 수열의 특정위치의 값 계산함수
    public int Fibo_Num(int key){
        if (key <= 1){
            return key;
        }else{
            //재귀함수
            return Fibo_Num(key-2)+Fibo_Num(key-1);
        }
    }
}
