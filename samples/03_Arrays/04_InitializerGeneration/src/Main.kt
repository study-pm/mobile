import kotlin.math.pow
import kotlin.math.sqrt

fun main() {
    val arrGenerate = IntArray(7) { i -> i }
    arrGenerate.forEach { print(it.toString() + "\t") }
    println()

    val arrGenerateFloat = FloatArray(10) { i -> sqrt(i.toFloat()) }
    arrGenerateFloat.forEach { print(it.toString() + "\t") }
    println()

    val arrGenerateDouble = DoubleArray(11) { i -> i.toDouble().pow(1/(i+1).toDouble()) }
    arrGenerateDouble.forEach { print("%.2f".format(it) + "\t") }
    println()

    val arrGenerateStr = Array(3) { i -> "Элемент $i = ${i * i}" }
    arrGenerateStr.forEach { print(it.toString() + "\t") }
    println()

    println(8.0.pow(1 / 3))
    println(8.0.pow(1 / 3.0))
}
