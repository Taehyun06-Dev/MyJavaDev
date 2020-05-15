    /*
    주제: Kotlin 기초공부
    kotlin 기초 문법 탐구하기
     */

    val c = 5
    val d = "안녕하세요"

    fun main(){
        println("이것은 메인함수")
        test(c, d)
        for(x in 1..5){
            println("값은 $x")
        }
    }

    fun test(a: Int, b: String){
        println("a: $a and b: $b")
    }



