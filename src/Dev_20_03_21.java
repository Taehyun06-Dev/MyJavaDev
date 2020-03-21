package com;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class Dev_20_03_21 {

    /*주제: Reflection 이란?
    프로그래밍 언어중 자바에만 있는 특별한 기능이며, JVM을 통해 다른 클래스의 내부값이나 메서드에 직접적으로
    접근이 가능하다. (JVM이 있기에 가능한 기능) 다만 속도가 느리고, 리플렉션 동작에는 JVM 최적화를 사용 할 수 없으므로
    무분별한 사용은 자제해야한다.
     */

    //메인 실행 함수
    public static void main(String[] args){
        new Dev_20_03_21().check_Reflection();
        new Dev_20_03_21().do_Reflection();
    }

    //해당 클래스 안에 있는 값들을 출력한다.
    public void check_Reflection(){
        try {
            Class cls = Class.forName("com.Test_Class");
            System.out.println("Methods: ");
            //메서드 출력
            for(Method mt : cls.getDeclaredMethods()){
                System.out.println("└ "+mt.getName());
            }
            System.out.println("Fields: ");
            //필드 출력
            for(Field fi : cls.getDeclaredFields()){
                System.out.println("└ "+fi.getName());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void do_Reflection(){
        try {
            Class cls = Class.forName("com.Test_Class");
            //Reflection을 통해 원격으로 작업을 수행한다.
            cls.getMethod("printMSG", String.class).invoke(new Test_Class(),"This is Reflection Message!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

//Relfection을 확인하기 위한 임의의 클래스 생성
class Test_Class{

    private String Ex_str = "apple";
    private int Ex_int = 7;

    public void printMSG(String text){
        System.out.println("Hello!".concat(text));
    }

}

