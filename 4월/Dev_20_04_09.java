package com.year2020.April;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Dev_20_04_09 {

    /*주제: 소켓통신
    소켓이란 TCP/IP 네트워크를 이용하여 쉽게 통신 프로그램을 작성하도록 지원하는 기술이다.
    구성요소로는 서버 소켓과 클라이언트 소켓이 있다.
    특정 아이피와 포트번호를 이용하여 채널을 식별하고, 이에 통신하여 데이터를 교환할 수 있다.
     */

    //ThreadPool 객체 생성
    private static ExecutorService executorService;

    //메인 실행 함수
    public static void main(String[] args){
        executorService = Executors.newFixedThreadPool(2);
        new Dev_20_04_09().Run_Server();
        new Dev_20_04_09().Run_Client();
    }

    public void Run_Server(){
        executorService.submit(new ServerSocketHandler());
    }

    public void Run_Client(){
        executorService.submit(new ClientSocketHandler());
    }


}
class ClientSocketHandler implements Runnable{

    @Override
    public void run() {
        try {
            Socket socket = new Socket("localhost", 8282);
            System.out.println("console> PORT(" + 8282 + ") 로 접속을 시도합니다.");
            InputStream stream = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(stream));
            String response = br.readLine();
            System.out.println("console> 수신 response : " + response);
            socket.close();
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //코드 출처: https://diaryofgreen.tistory.com/98 [vida valiente]
}

class ServerSocketHandler implements Runnable{

    @Override
    public void run() {
        try{
            ServerSocket serverSocket = new ServerSocket(8282);
            System.out.println("console> 서버 : 클라이언트의 접속을 기다립니다.");
            while(true) {
                Socket socket = serverSocket.accept();
                System.out.println("console> 서버 " + socket.getInetAddress() + " 클라이언트와 " +
                        socket.getLocalPort() + " 포트로 연결되었습니다.");
                try {
                    OutputStream stream = socket.getOutputStream();
                    stream.write(new Date().toString().getBytes());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    socket.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //코드 출처: https://diaryofgreen.tistory.com/98 [vida valiente]
}

