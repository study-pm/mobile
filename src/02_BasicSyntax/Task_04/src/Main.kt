/* Создать приложение, с помощью которого пользователь, введя два числа и символ операции, узнает результат.
 * Символами операции могут быть: / — деление, * — умножение, + — сложение, - — вычитание.
 * Числа могут быть вещественными.
 * Числа и знак операции разделяются между собой одним пробелом.
 * Ввод производится в формате - ЧИСЛО1 ЧИСЛО2 ОПЕРАЦИЯ
 */

/**
 * Parses simple math expression string
 * @param s input string expression in the following format: `<OPERAND1> <OPERAND2> <OPERATOR>`
 * @return structured expression as a pair of parsed operands array and operator sign string
 * @exception Exception if the string doesn't meet the required format
 */
fun parseExp(s: String): Pair<Array<Double>, String> {
    val parsed = s.split(" ")
    if (parsed.size != 3) throw Exception("Must be three items separated by a space")
    return Pair(arrayOf(parsed[0].toDouble(), parsed[1].toDouble()), parsed[2])
}

/**
 * Evaluates simple math expression string
 * @param s input string expression. The syntax is: `<OPERAND1> <OPERAND2> <OPERATOR>`
 * @return a real number representing the result of the input string evaluation
 *
 * Example usage:
 * ```kotlin
 * println(evalExp("6 3 +"))        // => 9.0
 * println(evalExp("12.28 4.45 *")) // => 54.646
 * ```
 * @exception Exception if the string doesn't meet the required format.
 *
 * Wrong usage:
 * ```kotlin
 * evalExp("     ") // => Invalid input: Must be three items separated by a space
 * evalExp("5 * 2") // => Invalid input: For input string: "*"
 * evalExp("7 5 ^") // => Invalid input: Unrecognized operation symbol. Must be one of the following: + - * /
 * ```
 */
fun evalExp(s: String): Double {
    val expr = parseExp(s)
    return when (expr.second) {
        "+" -> expr.first[0] + expr.first[1]
        "-" -> expr.first[0] - expr.first[1]
        "*" -> expr.first[0] * expr.first[1]
        "/" -> expr.first[0] / expr.first[1]
        else -> throw Exception("Unrecognized operation symbol. Must be one of the following: + - * /")
    }
}

fun main() {
    println("Enter two numbers and operation symbol in the following format: ")
    println("\t<OPERAND> <OPERAND> <OPERATION>")
    println("where OPERAND stands for a real number and OPERATION must be any of: + - * /")
    val input = readln()
    try {
        val result = evalExp(input)
        println(result)
    }
    catch (exc: Exception) {
        println("Invalid input: " + exc.message)
    }
}
