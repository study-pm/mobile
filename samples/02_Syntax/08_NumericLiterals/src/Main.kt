fun main() {
    var testInt = 123
    var testLong = 120L

    var testHex = 0x0F
    var testBinary = 0b00001011
    // printDouble(testBinary) // => // Ошибка: несоответствие типов

    var testDoublePoint = 123.5
    var testDoubleE = 123.5e12

    var testFloat_f = 481.78f
    var testFloat_F = 346.49F

    val oneMillion = 1_000_000
    val creditCardNumber = 1234_5678_9012_3456L
    val socialSecurityNumber = 999_99_9999L
    val hexBytes = 0xFF_EC_DE_5E
    val bytes = 0b11010010_01101001_10010100_10010010
}

fun printDouble(testPar: Double) {
    println(testPar)
}

// В языке Kotlin присутствуют следующие виды символьных постоянных (констант) для целых значений
// Десятичные числа: 123
// Тип Long обозначается заглавной L: 123L
// Шестнадцатеричные числа: 0x0F
// Двоичные числа: 0b00001011
// ВНИМАНИЕ: Восьмеричные литералы не поддерживаются.

// Также Kotlin поддерживает числа с плавающей запятой:
// Тип Double по умолчанию: 123.5, 123.5e10
// Тип Float обозначается с помощью f или F: 123.5f

// Можно использовать нижние подчеркивания, чтобы сделать числовые константы более читаемыми
