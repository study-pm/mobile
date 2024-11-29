fun main() {
    val actionOne = selectAction(1)
    println(actionOne(15, 95))
    val actionTwo = selectAction(2)
    println(actionTwo(43, 67))
}

fun selectAction(key: Int): (Int, Int) -> Int {
    return when (key) {
        1 -> ::sumFun
        2 -> ::subtractionFun
        else -> ::emptyFun
    }
}

fun sumFun(a: Int, b: Int) = a + b

fun subtractionFun(a: Int, b: Int) = a - b

fun emptyFun(a: Int, b: Int) = 0
