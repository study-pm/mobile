import kotlin.math.pow

fun main() {
    val testArrayInt: IntArray = intArrayOf(1, 2, 3)
    testArrayInt[0] = testArrayInt[1] + testArrayInt[2]
    testArrayInt.forEach { print(it.toString() + "\t") }
    println()
    val testArrayInit = IntArray(5)
    testArrayInit.forEach { print(it.toString() + "\t ") }
    println()
    val testArrayConst = IntArray(5) { 79 }
    testArrayConst.forEach { print(it.toString() + "\t ") }
    println()
    var testArrayLambda = DoubleArray(9) { 2.0.pow(it) }
    testArrayLambda.forEach { print(it.toString() + "\t ") }
    println()
}

// В Kotlin есть особые классы для представления массивов примитивных типов без дополнительных затрат на оборачивание:
// ByteArray, ShortArray, IntArray и т.д. Данные классы не наследуют класс Array,
// хотя и обладают тем же набором методов и свойств. У каждого из них есть соответствующая фабричная функция
