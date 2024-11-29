fun main() {
    printResult(::addFun, 72, 28)
    prnRes(::myFun)
}

fun addFun(a: Int, b: Int) = a + b

fun myFun() {
    println("Вы распечатали строку")
}

fun printResult(function: (Int, Int) -> Int, a: Int, b: Int) {
    println(function(a, b))
}

fun prnRes(function: () -> Unit) {
    function()
}
