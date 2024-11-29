fun main() {
    val action1 = selectAction(1)
    val result1 = action1(4, 5)
    println(result1)
    val action2 = selectAction(2)
    println(action2(4, 5))
    val action3 = selectAction(3)
    println(action3(4, 5))
    val action = selectAction(9)
    println(action(4, 5))
}

fun selectAction(key: Int): (Int, Int) -> Int {
    return when (key) {
        1 -> fun(x: Int, y: Int): Int = x + y
        2 -> fun(x: Int, y: Int): Int = x - y
        3 -> fun(x: Int, y: Int): Int = x * y
        else -> fun(_: Int, _: Int): Int = 0
    }
}
