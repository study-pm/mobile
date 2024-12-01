/* Создать программу, выполняющую следующий функционал:
 * - запрашивается количество строк и столбцов для двумерного массива;
 * - вводится необходимое количество трехзначных чисел (числа могут повторяться);
 * - подсчитывается количество различных цифр в полученном массиве;
 * - на консоль выводится двумерный массив из введенных чисел и количество различных цифр используемых в данном массиве
 */

/**
 * Collection of console control characters representing ANSI color codes used in output
 */
enum class OutputType(val code: String) {
    Success("\u001b[32m"),
    Error("\u001b[31m"),
    Reset("\u001b[0m")
}

/**
 * Set color to console out considering the input type
 * @param msg input message string
 * @param type type of output that defines the output string color (`Success` | `Error`)
 * @return colored string fragment ending with color reset code
 *
 * Usage examples:
 * ```kotlin
 * println(colorizedOut("\nSuccess", OutputType.Success))
 * println(colorizedOut("\nInvalid input", OutputType.Error))
 * ```
 */
fun colorizeOut(msg: String, type: OutputType) = type.code + msg + OutputType.Reset.code
val cOut = ::colorizeOut

/**
 * Parse input string to three-digit whole number
 * @param s input string
 * @return parsed three-digit whole number
 * @exception NumberFormatException if the string is not a valid representation of a three-digit whole number
 *
 * Example usage:
 * ```kotlin
 * val a = parseInput("123")
 * ```
 */
fun parseInput(s: String): Int {
    if (s.length != 3) throw NumberFormatException("Must be a three digit whole number")
    return s.toUInt().toInt()
}

/**
 * Print matrix line by line as a table of values
 * @param matrix input matrix
 */
fun printMatrix(matrix: Array<IntArray>) {
    for (row in matrix) for ((i, v) in row.withIndex()) print("$v" + if (i == row.lastIndex) "\n" else "\t")
}

fun main() {
    val digits = mutableSetOf<Char>()
    try {
        print("Enter row count: ")
        val rowCount = readln().toUInt().toInt()
        print("Enter column count: ")
        val colCount = readln().toUInt().toInt()

        if (rowCount != 0 && colCount != 0) {
            println("Enter array elements\' values (must be three-digit whole numbers), each from a new line:")
        }
        val matrix = Array(rowCount) { IntArray(colCount) }

        // Fill in the matrix with user input
        for (row in matrix) {
            for (colIndex in row.indices) {
                row[colIndex] = parseInput(readln())
                for (char in row[colIndex].toString()) digits.add(char)
            }
        }
        val digitsCount = digits.size

        if (rowCount == 0 || colCount == 0) {
            println(cOut("\nNo input values entered", OutputType.Success))
        }
        else {
            println(cOut("\nInput array: ", OutputType.Success))
        }
        printMatrix(matrix)
        print(cOut("\nNumber of unique digits used: ", OutputType.Success) + digitsCount)

    } catch (exc: Exception) {
        println(cOut("Invalid input: ", OutputType.Error) + exc.message)
    }
}

/* Sample I/O
=== Input 1 ===
* Row count: 3
* Column count: 4
* Values:
100
951
101
950
519
999
155
501
510
911
144
554
=== Output 1 ===
* Input array:
100	951	101	950
519	999	155	501
510	911	144	554
*
* Number of unique digits used: 5

=== Input 2 ===
* Row count: 1
* Column count: 1
* Values:
123
=== Output 2 ===
* Input array:
111
*
* Number of unique digits used: 1

=== Input 3 ===
* Row count: 0
* Column count: 0
* Values:
=== Output 3 ===
* No input values entered
*
* Number of unique digits used: 0

=== Input 4 ===
* Row count: 2
* Column count: 3
* Values:
0 | 12 | a
=== Output 4 ===
* Invalid input: Must be a three digit whole number
*/
