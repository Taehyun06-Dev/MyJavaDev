package com.year2020.April;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;

public class Dev_20_04_10 {

    /*주제: 날짜와 시간
    날짜와 현재시간을 표시하는법, 밀리세컨드 활용법에 대해 알아본다.
     */

    //메인 실햄함수
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new Dev_20_04_10().Current_Date();
        new Dev_20_04_10().Task_Time();
    }

    //날짜와 현재시각 표시
    public void Current_Date(){
        //날짜 출력 형식
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        //Date 인스턴스 생성
        Date date = new Date();
        //형식에 맞는 현재 날짜 출력
        System.out.println(formatter.format(date));
    }

    //밀리세컨드를 활용하여 작업 소요시간 표시
    public void Task_Time() throws ExecutionException, InterruptedException {
        //현재 밀리세컨드를 변수에 담음
        long Current_Milli = System.currentTimeMillis();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Boolean> Future_Task = executorService.submit(new Test_Task());
        //Task 가 완료될때까지 대기
        Future_Task.get();
        //1000으로 나누면 Second 형식으로도 출력이 가능하다.
        System.out.println("소요 밀리세컨드: "+(System.currentTimeMillis()-Current_Milli));
    }

}
class Test_Task implements Callable<Boolean>{

    @Override
    public Boolean call() throws Exception {
        //딜레이를 주기 위함
        Thread.sleep(1200);
        return true;
    }
}