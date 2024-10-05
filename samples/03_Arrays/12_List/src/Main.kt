fun main() {
    val testListStr = listOf("один", "два", "три", 97)
    val testListNum = listOf(3, 7, 9)
    val testListSum: List<Int>

    testListSum = testListNum + listOf(6, 8, 2)

    println(testListStr)
    println(testListNum)
    println(testListSum)
}
