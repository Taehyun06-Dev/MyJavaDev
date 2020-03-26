package com;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Dev_20_03_26 {

    /*주제: Robot를 활용한 AutoTyper만들기
    Java에서 제공하는 Robot 라이브러리를 활용하여 자동 입력기를 만들어본다.
    새로운 쓰레드에서 구동할것 이지만, 반복적으로 쓰레드를 생성/제거 하는것은
    프로그램에 상당한 무리가 가기때문에 ThreadPool을 사용한다.
     */

    //쓰레드풀 객체 생성(1개의 쓰레드풀을 생성)
    private ExecutorService executorService = Executors.newFixedThreadPool(1);

    //쓰레드 담당 메서드
    public void init_Thread(String str){
        //쓰레드풀로 작업을 처리한다.
        executorService.submit(new Thread_AutoTyper(str));
    }

    //메인 실행함수
    public static void main(String[] args) throws AWTException {
        //Robot 클래스가 한글 지원이 안되기에 영어로 입력해준다.
        new Dev_20_03_26().init_Thread("dkssudgktpdy");
    }
}
//쓰레드 담당 클래스
class Thread_AutoTyper implements Runnable{

    private String value;

    //Constructer 을 통해 출력값을 받아온다.
    public Thread_AutoTyper(String key){
        value = key;
    }

    @Override
    public void run() {
        //PowerShell 명령어로 notepad 프로그램 창을 맨앞으로 가져온다.
        try {
            new ProcessBuilder("powershell.exe", "-Command", "$wshell = New-Object -ComObject wscript.shell; $wshell.AppActivate('메모장')").start();
            Thread.sleep(2000);
            Robot r = new Robot();
            //한글자씩 입력한다.
            for (char c : value.toCharArray()) {
                int code = KeyEvent.getExtendedKeyCodeForChar(c);
                if (Character.isUpperCase(c))
                    r.keyPress(KeyEvent.VK_SHIFT);
                r.keyPress(code);
                r.keyRelease(code);
                if (Character.isUpperCase(c))
                    r.keyRelease(KeyEvent.VK_SHIFT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
