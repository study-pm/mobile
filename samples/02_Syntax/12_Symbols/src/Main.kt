fun main() {
    val testChar = 'N'
    println(testChar)
    println("Новая строка\n")
    println("Еще одна новая строка")
    println()
    println("1\t2\t3\t4\t5")
    println('9'.digitToInt())
    // println("9".digitToInt()) // => Kotlin: Overload resolution ambiguity between candidates:
    println('\uFF00') // => ＀
}

// Символы в Kotlin представлены типом Char. Символьные литералы заключаются в одинарные кавычки: '1'.
// Специальные символы начинаются с обратного слеша \. Поддерживаются следующие escape-последовательности:
// \t, \b, \n, \r, \', \", \\ и \$.
// Для кодирования любого другого символа используйте синтаксис escape-последовательности Юникода: '\uFF00'.
