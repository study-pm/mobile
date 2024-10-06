fun main() {
    val testListNum = mutableListOf(3, 9, 7, 9)
    println(testListNum)

    testListNum.add(798) // Добавили элемент в конец List
    println(testListNum)

    testListNum.add(0, 641) // Добавили элемент на место с индексом
    testListNum.forEach { println(it.toString() + "\t") }
    println(testListNum)

    testListNum.removeAt(0) // Удалили элемент по индексу
    testListNum.remove(9) // Удалили первое вхождение элемента со значением
    println(testListNum)
}
