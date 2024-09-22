fun main() {
    val testSize = (1..10).random()
    var testArr = IntArray(testSize)

    var i = 0
    while (i < testSize) {
        testArr[i] = (20..50).random()
        i++
    }
    testArr.forEach { print(it.toString() + "\t") }
    println()
    var j = 0
    do {
        testArr[j] = (20..50).random()
        j++
    } while (j < testSize)
    testArr.forEach { print(it.toString() + "\t") }
}
