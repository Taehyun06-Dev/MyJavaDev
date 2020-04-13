package com.year2020.April;

import java.awt.*;
import java.io.*;

public class Dev_20_04_13 {

    /*주제: 버퍼를 활용한 파일 리딩
    BufferedReader 을 활용해 파일의 정보를 읽는다.
     */

    //메인 실햄 함수
    public static void main(String[] args) throws IOException {
        new Dev_20_04_13().ReadFile();
    }


    public void ReadFile() throws IOException {
        //파일 객체 생성
        File file = new File("C:\\Users\\bkcha\\Desktop\\오목.sk");
        //버퍼리더 객체 생성
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String in;
        while((in = bufferedReader.readLine()) != null){
            //한줄씩 읽어온다
            System.out.println(in);
        }
    }
}
