fun main() {
    val arrOne = IntArray(10) { i -> i * 3 }

    arrOne.forEach { item -> print(item.toString() + "\t") }
    println()

    arrOne.forEachIndexed { index, item -> println("Элемент с индексом $index равен $item") }
}
