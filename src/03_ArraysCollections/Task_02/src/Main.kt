/* Имеется массив целых чисел из 5-и строк и 5-и столбцов.
 * Выяснить, симметричен ли он относительно главной диагонали.
 * То есть - элемент 1,2 равен элементу 2,1. Элемент 1,3 равен элементу 3,1 и т.д.
 */

/**
 * Checks if matrix is symmetric: A is symmetric ⇔ ∀(i, j) a_{ji} = a_{ij}, A = A^T
 * @param matrix a square matrix
 * @return `true` if symmetric, `false` if not
 *
 * Examples:
 * ```kotlin
 *  // Symmetric matrix population
 *  var matrix1: Array<IntArray> = arrayOf(
 *         intArrayOf(5, 9, 6, 7, 2),
 *         intArrayOf(9, 8, 4, 5, 3),
 *         intArrayOf(6, 4, 3, 8, 7),
 *         intArrayOf(7, 5, 8, 4, 8),
 *         intArrayOf(2, 3, 7, 8, 1),
 *     )
 *  checkIfSymmetric(matrix1) // => true
 *
 *  var matrix2 = Array(size) { IntArray(size) }
 *
 *  // Random values matrix population
 *  for (row in matrix2) for (colIndex in row.indices) {
 *         row[colIndex] = (0..100).random()
 *     }
 *
 *  checkIfSymmetric(matrix2) // => false
 * ```
 */
fun checkIfSymmetric(matrix: Array<IntArray>): Boolean {
    var isSymmetric = true
    for ((rowIndex, row) in matrix.withIndex()) {
        for ((colIndex, cell) in row.withIndex()) {
            if (cell != matrix[colIndex][rowIndex]) {
                isSymmetric = false
                break
            }
        }
    }
    return isSymmetric
}

fun main() {
    val size = 5
    val matrix = Array(size) { IntArray(size) }
    try {
        println("Enter matrix values: must be five rows, each starting with a new line " +
                "and consisting of five integer numbers separated by a space")
        for (r in matrix.indices) {
            val line = readln().split(' ')
            if (line.size != size) throw Exception("Must be five rows, each consisting of five integer numbers")
            matrix[r] = line.map { it.toInt() }.toIntArray()
        }
        println("The matrix is${if (checkIfSymmetric(matrix)) " " else " not "}symmetric.")
    } catch (exc: Exception) {
        println("\u001b[31mInvalid input: \u001b[0m" + exc.message)
    }
}

/* Sample I/O
=== Input 1 ===
5 9 6 7 2
9 8 4 5 3
6 4 3 8 7
7 5 8 4 8
2 3 7 8 1
=== Output 1 ===
* The matrix is symmetric.

=== Input 2 ===
1 1 1 1 1
1 2 1 1 1
1 1 3 1 1
1 1 1 4 1
1 1 1 1 5
=== Output 2 ===
* The matrix is symmetric.

=== Input 3 ===
95 56 9 37 26
73 59 47 20 90
55 16 87 11 54
27 30 13 37 97
54 93 44 31 25
=== Output 3 ===
* The matrix is not symmetric.

=== Input 4 ===

=== Output 4 ===
* Invalid input: Must be five elements in a row

=== Input 5 ===
1 2 3 4 5 6 7 8 9
=== Output 5 ===
* Invalid input: Must be five elements in a row

=== Input 6 ===
a b c d e
=== Output 6 ===
* Invalid input: For input string: "a"

=== Input 7 ===
1 2 3 4.5 6
=== Output 7 ===
* Invalid input: For input string: "4.5"
*/
