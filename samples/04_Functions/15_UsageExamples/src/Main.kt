fun main() {
    val operationFun = ::myFun
    printResult(operationFun, 5, 67)
    printResult(::addFun, 72, 28)
    println(myDiff(addFun(67, 45), 67))
}

fun myDiff(a: Int, b: Int) = a - b

fun addFun(a: Int, b: Int) = a + b

fun myFun(a: Int, b: Int) = a * b

fun printResult(function: (Int, Int) -> Int, a: Int, b: Int) {
    println(function(a, b))
}
