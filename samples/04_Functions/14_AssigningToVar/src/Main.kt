fun main() {
    val operationFun: (Int, Int) -> Int
    val testA = (100..999).random()
    val testB = (100..999).random()
    if (testA < testB) {
        operationFun = ::addFun
    } else {
        operationFun = ::myDiff
    }
    print("$testA $testB => ")
    printResult(operationFun(testA, testB))
}

fun myDiff(a: Int, b: Int) = a - b

fun addFun(a: Int, b: Int) = a + b

fun printResult(function: Int) {
    println(function)
}
