/* Создать приложение, в котором пользователь вводит две различных цифры.
 * На выходе приложение выдает, если это возможно, из введенных цифр, нечетное число.
 * Результат выводится в консоль.
 * При невозможности создать нечетное число выводится сообщение – «Создать нечетное число невозможно».
 * Каждое число вводится на отдельной строке
 */

enum class NumberCondition {
    ANY,
    EVEN,
    ODD
}

/** Return checked input string character
 *  @param s input string to check
 *  @return first character of the original string
 *  @throws Exception if the input string doesn't meet the requirements
 */
fun checkIfDigit(s: String): Char {
    if (s.length != 1 || s[0] !in '0'..'9')
        throw Exception("Must be a single numeric symbol (in range from 0 to 9)")
    return s[0]
}

/** Check if an integer is even
 *  @param n input number
 *  @return `true` if even, `false` if not
 */
fun checkIfEven(n: UInt): Boolean {
    return n.toInt() % 2 == 0
}
fun checkIfEven(n: Int): Boolean {
    return n % 2 == 0
}

/** Return numbers that can be constructed from two different digits
 *  @param input array of characters representing two different digits
 *  @param condition enum specifying the condition for numbers to be constructed (can be even, odd or any)
 *  @return mutable set of constructed numbers (can contain 2, 1 or 0 values)
 */
fun constructNumbers(input: CharArray, condition: NumberCondition): MutableSet<UInt> {
    val src = arrayOf("${input[0]}${input[1]}".toUInt(), "${input[1]}${input[0]}".toUInt())
    if (condition.name == "ANY") return src.toMutableSet()
    val res = mutableSetOf<UInt>()
    for (n in src) {
        if ((condition.name == "EVEN"  && checkIfEven(n)) || (condition.name == "ODD" && !checkIfEven(n)))
            res.add(n)
    }
    return res
}

fun main() {
    val green = "\u001b[32m"
    val red = "\u001b[31m"
    val reset = "\u001b[0m"
    println("Enter two different digits (each from a new line): ")
    try {
        val input = charArrayOf(checkIfDigit(readln()), checkIfDigit(readln()))
        if (input[0] == input[1]) throw Exception("Must be different values (symbols must not be the same)")
        val res = constructNumbers(input, NumberCondition.ODD)
        println()
        val ending = " can be made from the given input:"
        if (res.isEmpty()) println(green + "No odd number" + ending)
        else {
            println(green + "The following odd number" + (if (res.size > 1) "s" else "") + ending + reset)
            for (n in res) println(n)
        }
    }
    catch(exc: Exception) {
        println("${red}Invalid input:${reset} " + exc.message)
    }
}

/* Sample I/O data
        => !
a       => !
0,      => !
0, a    => !
0, 0    => !
0, 1    => 1
1, 0    => 1
1, 1    => !
1, 2    => 21
2, 1    => 21
1, 3    => 13, 31
3, 1    => 31, 13
2, 2    => !
2, 3    => 23
3, 2    => 23
2, 4    => -
4, 2    => -
12      => !
0, 12   => !
 */
