fun main() {
    println(sumThree(15, 25, 44))
    println(sumThree(16, 18))
}

fun sumThree(koefA: Int, koefB: Int, koefC: Int = 5): String {
    return "$koefA + $koefB + $koefC = ${koefA + koefB + koefC}"
}
