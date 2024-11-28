fun main() {
    val testArr = intArrayOf(9, 7, 6)
    doubleArray(testArr)
    println()
    testArr.forEach { print(it.toString() + "\t") }
}

fun doubleArray(numArr: IntArray) {
    for (i in 0..numArr.size - 1) {
        numArr[i] = numArr[i] * 2
    }
    print("Печать из функции\t")
    numArr.forEach { print(it.toString() + "\t") }
}
