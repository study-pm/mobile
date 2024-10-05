import kotlin.random.Random

fun main() {
    var arrOne = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)
    arrOne.shuffle()
    arrOne.forEach { print(it.toString() + "\t") }
    println()
    arrOne = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)
    arrOne.shuffle(Random(2))
    arrOne.forEach { print(it.toString() + "\t") }
    println()
    arrOne = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)
    arrOne.shuffle(Random(2)) // => The same seed number gives the same sequence
    arrOne.forEach { print(it.toString() + "\t") }
    println()
    arrOne = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)
    arrOne.shuffle(Random(5))
    arrOne.forEach { print(it.toString() + "\t") }
}
