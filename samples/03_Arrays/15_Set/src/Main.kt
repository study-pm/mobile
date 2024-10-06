fun main() {
    val testSetNum = setOf(1, 4, 8, 5, 4)
    println(testSetNum)
    val testSetTwo: Set<Int>
    testSetTwo = setOf(2, 4, 9, 3, 5)
    for (item in testSetNum) {
        print(testSetTwo.contains(item).toString() + "\t")
    }
    println()

    println(testSetNum.intersect(testSetTwo))       // пересечение наборов
    val testNewSet = testSetNum.union(testSetTwo)   // объединение
    println(testNewSet)
    val testPlusSet = testSetNum.plus(789)
    println(testPlusSet)
}
