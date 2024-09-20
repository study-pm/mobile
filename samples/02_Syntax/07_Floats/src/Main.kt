fun main() {
    var testDouble: Double = 120.0
    // val one: Double = 1 // Ошибка: несоответствие типов
    // var testDoubleTwo: Double = 120 // => Kotlin: Initializer type mismatch: expected 'kotlin/Double', actual 'kotlin/Int'
    printDouble(testDouble) // => 120.0

    var testFloat: Float = 32000F
    // var testFloatTwo: Float = 32000.7 // => Kotlin: Initializer type mismatch: expected 'kotlin/Float', actual 'kotlin/Double'
    printDouble(testFloat) // => 32000.0

    val i = 1
    // printDouble(i) // Ошибка: несоответствие типов
}

fun printDouble(testPar: Float) {
    println(testPar)
}

fun printDouble(testPar: Double) {
    println(testPar)
}
// Для действительных чисел в Kotlin есть типы с плавающей точкой Float и Double. Согласно стандарту IEEE 754,
// типы с плавающей точкой различаются своим десятичным разрядом, то есть количеством десятичных цифр, которые
// они могут хранить. С точки зрения IEEE 754 Float является одинарно точным, а Double обеспечивает двойную точность.

// Тип	    Размер (биты)	Значимые биты	Биты экспоненты	    Разряды
// Float	    32              24	                8	        6-7
// Double	    64	            53	                11          15-16

// Можно инициализировать переменные Double и Float числами, имеющими дробную часть. Она должна быть отделена
// от целой части точкой (.). Для переменных, инициализированных дробными числами, компилятор автоматически
// определяет тип Double.

// Чтобы явно указать тип Float, добавьте после значения f или F. Если такое значение содержит более 6-7 разрядов,
// оно будет округлено.

// Обратите внимание, что в отличие от некоторых других языков, в Kotlin нет неявных преобразований для чисел.
// Например, функция с Double параметром может вызываться только для Double, но не для Float, Int или
// других числовых значений.

// Чтобы преобразовать числовые значения в различные типы, используйте Явные преобразования.
