fun main() {
    var testMax = 5
    val testNew = (0..9).random()
    println("testNew: $testNew")
    if (testMax < testNew) testMax = testNew
    println(testMax)

    val testOne = (10..19).random()
    val testTwo = (10..19).random()
    println("testOne: $testOne, testTwo: $testTwo")

    if (testOne > testTwo) {
        println(testOne)
    } else {
        println(testTwo)
    }

    val testPrint = if (testOne > testTwo) {
        print("Возвращаем testOne - ")
        testOne
    } else {
        print("Возвращаем testTwo - ")
        testTwo
    }

    println(testPrint)
}
