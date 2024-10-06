fun main() {
    val testSetNum = mutableSetOf(1, 9, 6, 4, 7)
    val testSetTwo: MutableSet<Int> = mutableSetOf()
    for (i in 9..16) {
        testSetTwo.add(i * (105..145).random())
    }
    println(testSetNum)
    println(testSetTwo)
    testSetNum.remove(1)
    println(testSetNum)
    println(testSetNum.dropWhile { it > 6 })
}
