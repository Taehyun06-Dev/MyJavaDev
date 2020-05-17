import kotlin.random.Random

/*
주제: Kotlin 기초공부2
kotlin 기초 문법 탐구하기2
 */
fun main(){
    val instance = DemoClass(Random.nextInt(3))
    instance.test()
}

class DemoClass(i: Int) {
    var int = 0
    init {
        int = i
    }
    fun test(){
       println("안녕하세요 테스트 입니다. $int")
    }
}
