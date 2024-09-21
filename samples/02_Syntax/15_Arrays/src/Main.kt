fun main() {
    var testArray = arrayOf("1", 2, true)
    testArray.forEach { print(it.toString() + "\t") }
    println("\n")
    val testArrayNum = Array(5) { i -> (i * i).toString() }
    testArrayNum.forEach { println(it) }
    println(testArrayNum.get(3))
    testArrayNum.set(0, "99")
    testArrayNum.forEach { print(it + "\t") }
}

// Массивы в Kotlin представлены классом Array, обладающим функциями get и set (которые обозначаются []
// согласно соглашению о перегрузке операторов), и свойством size, а также несколькими полезными встроенными функциями.
// Также для создания массива можно использовать фабричную функцию, которая принимает размер массива и функцию,
// возвращающую начальное значение каждого элемента по его индексу.
