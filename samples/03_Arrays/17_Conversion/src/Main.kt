fun main() {
    val testSetNum = mutableSetOf(1, 9, 6, 4, 7)
    println(testSetNum)
    testSetNum.add(47)
    println(testSetNum)
    val testNoMutableSet = testSetNum.toSet() // Можно преобразовывать изменяемые коллекции в неизменяемые и наоборот
    testSetNum.add(59)
    println(testNoMutableSet)
    // testNoMutableSet.add(39) // Нельзя добавить элемент, так как мы вернули неизменяемую коллекцию
    println(testSetNum)
}
