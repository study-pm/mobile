fun main() {
    val testA = 1
    val testB: Byte = 1
    // println(testA == testB) // => Kotlin: Operator '==' cannot be applied to 'kotlin/Int' and 'kotlin/Byte'
    // val testD: Int = testB // => Kotlin: Initializer type mismatch: expected 'kotlin/Int', actual 'kotlin/Byte'

    val testH = testB.toInt()

    println(5 / 2)      // => 2
    println(5 / 2.0)    // => 2.5
    println(6.0 / 2.0)  // => 3.0

    val l = 1L + 3      // Long + Int => Long
    println(l)          // => 4
}

// Из-за разницы в представлениях меньшие типы не являются подтипами бОльших типов.
// Неявное преобразование меньших типов в большие НЕ происходит. Это значит, что мы не можем присвоить значение
// типа Byte переменной типа Int без явного преобразования.

// Каждый численный тип поддерживает следующие преобразования:
// toByte(): Byte       toShort(): Short        toInt(): Int        toLong(): Long
// toFloat(): Float     toDouble(): Double      toChar(): Char

// Часто необходимости в явных преобразованиях нет, поскольку тип выводится из контекста,
// а арифметические действия перегружаются для подходящих преобразований.
