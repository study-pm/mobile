fun main() {
    val arrOne: Array<IntArray> = arrayOf(
        intArrayOf(2, 7, 8, 1),
        intArrayOf(5, 2, 9, 3),
    )

    val arrTwo = arrayOfNulls<Array<Int>>(2)
    arrTwo[0] = Array(3) {0}
    arrTwo[1] = Array(2) {1}
}
