fun main() {
    val testArr = intArrayOf(8, 9, 6, 7, 3, 4)
    println(myCount(5, *testArr))
}

fun myCount(koef: Int, vararg numbers: Int): Int {
    var summa = 0
    for (item in numbers) summa += item * koef
    return summa
}
