fun main() {
    val testTrue = true
    val testFalse = false
    val testNull: Boolean? = null

    println(testTrue && testFalse)
    println(testTrue || testFalse)
    println(!testTrue || testFalse)
    println(!testTrue || !testFalse)

    println(2 > 5 && 9 > 0)
}

// Тип Boolean представляет логический тип данных и принимает два значения: true и false.
// При необходимости использования nullable-ссылок логические переменные оборачиваются Boolean?.
// Встроенные действия над логическими переменными включают:
//  || – ленивое логическое ИЛИ
//  && – ленивое логическое И
//  ! – отрицание
