fun main() {
    val arrOne = IntArray(10) { i -> i * 3 }
    arrOne.forEach { i -> print(i.toString() + "\t") }
    println()
    arrOne.reverse()
    arrOne.forEach { i -> print(i.toString() + "\t") }
    println()
    arrOne.reversed()
    arrOne.forEach { i -> print(i.toString() + "\t") }
    println()
    val arrTwo = arrOne.reversed()
    arrTwo.forEach { i -> print(i.toString() + "\t") }
    println()
    arrOne.reverse(3, 6)
    arrOne.forEach { i -> print(i.toString() + "\t") }
    // arrTwo.reverse(3, 6) // => Kotlin: Unresolved reference: reverse
    // Reversed возвращает тип List, у которого отсутствует метод reverse, т.е.
    // val arrTwo: List<Int> = arrOne.reversed()
}
