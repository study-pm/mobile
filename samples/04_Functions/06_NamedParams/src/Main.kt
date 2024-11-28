fun main() {
    println(sumThree(koefC = 15, koefA = 25, koefB = 44))
    println(sumThree(16, koefB = 18))
    println(sumThree(16, koefC = 18, koefB = 75))
    println(myIncrease(koefB = 73, koefC = 64))
}

fun sumThree(koefA: Int, koefB: Int, koefC: Int = 5): String {
    return "$koefA + $koefB + $koefC = ${koefA + koefB + koefC}"
}

fun myIncrease(koefA: Int = 42, koefB: Int, koefC: Int): String {
    return "$koefA + $koefB + $koefC = ${koefA + koefB + koefC}"
}
