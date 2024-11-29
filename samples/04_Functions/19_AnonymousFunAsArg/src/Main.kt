fun main() {
    doOperation(9, 5, fun(x: Int, y: Int): Int = x + y)
    doOperation(9, 5, fun(x: Int, y: Int): Int = x - y)

    val action = fun(x: Int, y: Int): Int = x * y
    doOperation(9, 5, action)
}

fun doOperation(x: Int, y: Int, op: (Int, Int) -> Int) {
    val result = op(x, y)
    println(result)
}
