import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.util.concurrent.Callable
import java.util.concurrent.Executors

/*
주제: Kotlin 기초공부4 - 웹 페이지 크롤링하기
 */
fun main(){
    val excutor = Executors.newSingleThreadExecutor()
    val future = excutor.submit(Crowl_Test())
    System.out.println(future.get())
}

class Crowl_Test: Callable<String>{

    override fun call(): String {
        val url = URL("http://api.corona-19.kr/korea/?")
        val uc = url.openConnection()
        uc.addRequestProperty("User-Agent",
                "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
        var bufferedReader = BufferedReader(InputStreamReader(uc.getInputStream()))
        var string = ""
        for(str in bufferedReader.lines()){
            string = string+"\n"+str
        }
        return string
    }

}
/*결과
{
    "resultCode": "0",
    "TotalCase": "11,110",
    "TotalRecovered": "10,066",
    "TotalDeath": "263",
    "NowCase": "781",
    "city1n": "대구",
    "city2n": "기타",
    "city3n": "경북",
    "city4n": "서울",
    "city5n": "경기",
    "city1p": "61.85",
    "city2p": "12.47",
    "city3p": "12.31",
    "city4p": "6.77",
    "city5p": "6.62",
    "recoveredPercentage": 90.6,
    "deathPercentage": 2.37,
    "checkingCounter": "16,351",
    "checkingPercentage": "2.1",
    "caseCount": "11,110",
    "casePercentage": "1.4",
    "notcaseCount": "748,972",
    "notcasePercentage": "96.5",
    "TotalChecking": "776,433",
    "TodayRecovered": "128",
    "TodayDeath": "0",
    "TotalCaseBefore": "-96",
    "updateTime": "코로나바이러스감염증-19 국내 발생현황 (5.20. 00시 기준)",
    "resultMessage": "정상 처리되었습니다."
}
 */
