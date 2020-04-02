package com;

import com.oracle.deploy.update.Updater;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Dev_20_04_02 {

    /*주제: 웹 크롤링 실전편
    앞에서 탐구한 웹 크롤링을 바탕으로 Jsoup 라이브러리를 활용하여
    실제로 코로나19 확진자 데이터를 불러온다.
    개발자 도구를 활용하여 html의 구조를 파악하여 쉽게 크롤링을 사용할 수 있다.
     */

    //메인 실햄 함수
    public static void main(String[] args){
        new Dev_20_04_02().getCorona_Data();
    }

    public void getCorona_Data(){
        try {
            Document doc = Jsoup.connect("http://ncov.mohw.go.kr/bdBoardList_Real.do?brdId=1&brdGubun=13&ncvContSeq=&contSeq=&board_id=&gubun=").timeout(5000).get();
            //html 구조를 활용한 크롤링
            for (Element em : doc.select("tbody > tr")) {
                //합계값을 따로 빼온다
                if (em.select("th").get(0).text().equals("합계")) {
                    System.out.println("합계: "+Integer.parseInt(em.select("td").get(1).text().replaceAll(",", "")));
                } else {
                    //제시된 모든 지역별 확진자 출력
                    System.out.println(em.select("th").get(0).text()+": "+Integer.parseInt(em.select("td").get(1).text().replaceAll(",", "")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
