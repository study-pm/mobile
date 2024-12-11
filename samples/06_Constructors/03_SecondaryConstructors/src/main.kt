class AddValue {
    constructor(a: Int, b: Int) {
        println("Складываем целые числа: $a + $b = ${a + b}")
    }
    constructor(a: String, b: Int) {
        println("Складываем строку и число: $a + $b = ${a.toInt() + b}")
    }
    constructor(a: Int, b: String) {
        println("Складываем число и строку: $a + $b = ${a + b.toInt()}")
    }
    constructor(a: String, b: String) {
        println("Складываем строки: $a + $b = ${a.toInt() + b.toInt()}")
    }
}

fun main() {
    val one = AddValue(25, 35)
    val two = AddValue("65", 75)
    val three = AddValue(45, "85")
    val four = AddValue("95", "75")
}
