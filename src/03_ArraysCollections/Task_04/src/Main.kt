/* Создать приложение, в котором пользователь дает на вход два числовых массива, числа в массиве могут повторяться.
 * На выходе приложение выдает пересечение этих массивов. То есть учитывается количество повторений чисел в массиве.
 */

import utils.*

/**
 * Gets two arrays intersection as a new array preserving the duplicating values if they exist in both input arrays
 * @param array1 input array
 * @param array2 input array
 * @return array consisting of values presenting in both arrays holding the duplicated elements
 *
 * Usage example:
 * ```kotlin
 * val arr1 = intArrayOf(1, 2, 3, 2, 0)
 * val arr2 = intArrayOf(5, 1, 2, 7, 3, 2, 2)
 * getArrayIntersection(arr1, arr2) // => [1, 2, 2, 3]
 * ```
 */
fun getArrayIntersection(array1: IntArray, array2: IntArray): IntArray {
    val arr1 = array1.sortedArray()
    val arr2 = array2.sortedArray()

    var i1 = 0
    var i2 = 0

    val res: MutableList<Int> = mutableListOf()

    while (i1 < arr1.size && i2 < arr2.size) {
        if (arr1[i1] < arr2[i2]) i1++
        else if (arr1[i1] > arr2[i2]) i2++
        else {
            res.add(arr1[i1])
            i1++
            i2++
        }
    }

    return res.toIntArray()
}

fun main() {
    try {
        // Handle user input
        println("Enter first array values (must be integer numbers separated by a space): ")
        val arr1 = readln().split(' ').map{ it.toInt() }.toIntArray()
        println("Enter second array values (must be integer numbers separated by a space): ")
        val arr2 = readln().split(' ').map{ it.toInt() }.toIntArray()

        // Get result
        val res = getArrayIntersection(arr1, arr2)

        // Handle output
        println("\n" + colorizeOut("Array intersection: ", OutputType.Success) + res.joinToString())
    } catch (exc: Exception) {
        println(colorizeOut("Invalid input: ", OutputType.Error) + exc.message)
    }
}
