package com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Dev_20_04_01 {

    /*주제: 웹 크롤링(파싱)
    크롤링이란, 웹 상의 정보들을 가공하여 사용하는 기법이다. 주로 사이트에 가상으로 연결한뒤
    정보들을 String 값으로 받아 지난시간에 탐구한 텍스트 추출기법을 이용해 추출한다.
    필자는 규모가 있는 사이트의 경우 Jsoup 라이브러리를 이용, Html docs를 이용해 추출하는 편이고,
    일반 소규모나 Json 형태의 API 일경우 BufferedReader 을 활용하는 편이다.
     */

    //URL 객체 생성
    //아래 URL 은 코로나 확진자 API 사이트 이다.
    private URL url = new URL("https://capi.msub.kr/");

    //객체 생성에 따른 Exception
    public Dev_20_04_01() throws MalformedURLException { }

    //메인 실햄 함수
    public static void main(String[] args) throws IOException {
        new Dev_20_04_01().Parse_BufferedReader();
    }

    //버퍼로 받아온다.
    //쓰레드 풀이나 new Thread()로 새로운 쓰레드에서 동작하는것이 효과적일 수 있다.
    public void Parse_BufferedReader() throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {
            String line22;
            StringBuilder sb = new StringBuilder();
            while ((line22 = br.readLine()) != null) {
                sb.append(line22);
                sb.append(System.lineSeparator());
            }
            System.out.println(sb.toString());
        }
    }
}
