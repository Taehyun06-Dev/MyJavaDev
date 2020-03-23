package com;

public class Dev_20_03_23 {

    /*
    주제: GarbageCollection(GC)란?
    보통 언어들이 윈도운(운영체제)단 에서 실행되지만, Java는 JVM이란 가상머신단에서 구동된다.
    JVM은 C로 컴파일된 머신으로, 메모리 관리등을 간접적으로 할 수 있게 해준다.
    운영체제에 크게 상관없이 코드가 돌아가는것도 이 JVM덕분이다.
    앞서 말했듯이, 타 언어들이 메모리 관리를 수동(프로그래머)인 반연, JVM은 자동으로 메모리를 관리한다.
    바로 가비지컬렉션(GC)를 통해서 힙영역을 자동으로 청소해준다. 즉, 쓸모없는 Object를 모두 정리해주는것이다.
    아레 코드를 통해 수동으로 실행할 수도 있다. GC는 약간의 지식을 더 쌓은후 추후에 더 탐구한다.
     */

    //메인 실행함수
    public static void main(String s[]) throws Exception {
        //런타임을 가져온다.
        Runtime rs = Runtime.getRuntime();
        System.out.println("Before GC: "+rs.freeMemory());
        //Gc실행
        rs.gc();
        System.out.println("After GC: "+rs.freeMemory());
    }
}
