/*
주제: Kotlin 기초공부3
kotlin 기초 문법 탐구하기3
 */

fun main(){
    val thread = ThreadTest("안녕하세요",3)
    thread.start()
}
class ThreadTest(tn: String, ti: Int): Thread() {

    private val testers = tn;
    private val tester = ti;

    override fun run() {
        println("New Thread!+ String is $testers, Int is $tester")
    }

}