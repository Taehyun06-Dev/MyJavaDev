import java.util.*;

public class Dev_20_03_18 {

    /*주제: 목록 변수의 정의와 원리
    널리 사용되는 ArrayList, LinkedList, HashSet, List[] 에 대해 탐구한다.
     */

    //List[]
    //주로 임시 변수에 사용한다.
    private String[] Example_List = {"a", "b", "c", "d"};

    //메인 실행 함수
    public static void main(String[] args){
        new Dev_20_03_18().Perform_ArrayList();
        new Dev_20_03_18().Perform_LinkedList();
        new Dev_20_03_18().Perform_HashSet();
    }

    //ArrayList
    //구조가 가장 간단하고 접근 속도가 빠르다. 다만 중간에 자료를 삽입하기 위해선
    //연속된 빈 공간이 필요하며, 삽입, 삭제시 자료의 이동이 필요하여 작업이 번거롭다.
    public void Perform_ArrayList(){
        //변수 생성
        ArrayList<String> List_Array = new ArrayList<>();
        //Enhanced For문
        for (String a : Example_List){
            //값을 List_Array에 넣어줌
            List_Array.add(a);
            System.out.println("String[] -> ArrayList, value: "+a);
        }
        //ArrayList 출력
        for (String b : List_Array){
            System.out.println("ArrayList, value: "+b);
        }
    }

    //LinkedList
    //삭제, 삽입 작업이 용이하다. 연결링크(포인터)가 필요해 힙 영역 이용효율이 좋지않다.
    //연결을 위한 포인터를 찾아야해 접근 속도가 느리다.
    //주로 삭제/삽입이 빈번한 구문에서 사용한다.
    public void Perform_LinkedList(){
        //변수 생성
        LinkedList<String> List_Linked = new LinkedList<>();
        //Enhanced For문
        for (String a : Example_List){
            //값을 List_Linked에 넣어줌
            List_Linked.add(a);
            System.out.println("String[] -> LinkedList, value: "+a);
        }
        //LinkedList 출력
        for (String b : List_Linked){
            System.out.println("LinkedList, value: "+b);
        }
    }

    //Hashset
    //다른 목록 변수들과 다르게 순서를 기억하지 않는다. 접근속도가 매우 빠르다.
    public void Perform_HashSet(){
        //변수 생성
        Set<String> List_Set = new HashSet<>();
        //Enhanced For문
        for (String a : Example_List){
            //값을 List_Set에 넣어줌
            List_Set.add(a);
            System.out.println("String[] -> HashSet, value: "+a);
        }
        //HashSet 출력
        for (String b : List_Set){
            System.out.println("HashSet, value: "+b);
        }
    }
}
