package com;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Dev_20_03_28 {

    /*주제: 정규식(Regular Expression)과 split&replace, substring()을 활용한 문자열 추출
    파싱, 크롤링등 특정 문자열에서 값을 추출하기 위한 방법들을 탐구해본다.
     */

    //테스트용 객체 생성
    private String Test_String = "오늘의 날씨는 '좋음' 이며, 기온은 평균 26도씨 입니다.";

    //메인 실행 함수
    public static void main(String[] args){
        new Dev_20_03_28().Extr_substring();
        new Dev_20_03_28().Extr_splitandreplace();
        new Dev_20_03_28().Extr_regularExpr();
    }

    //substring()을 활용한 추출
    public void Extr_substring(){
        System.out.println("Extract using substring(): ");
        System.out.println("    값1: "+Test_String.substring(9, 11)+", 값2: "+Test_String.substring(24, 26));
    }

    //split&replace를 활용한 추출
    public void Extr_splitandreplace(){
        //공백으로 split한 객체 생성
        String[] list = Test_String.split(" ");
        System.out.println("Extract using split&replace: ");
        System.out.println("    값1: "+list[2].replaceAll("\'", "")+", 값2: "+list[6].replaceAll("도씨", ""));
    }

    //Regular Expression을 활용한 추출
    public void Extr_regularExpr(){
        Matcher matcher = Pattern.compile("오늘의 날씨는 '(.*)' 이며, 기온은 평균 (.*)도씨 입니다.").matcher(Test_String);
        System.out.println("Extract using Regular Expression: ");
        if (matcher.find()) {
            System.out.println("    값1: "+matcher.group(1)+", 값2: "+matcher.group(2));
        }else{
            System.out.println("no match!");
        }
    }
}
