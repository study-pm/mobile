/* Выбор наибольшего и наименьшего расстояний (обязательно использовать класс Точка)
 *
 * Множество точек расположено на координатной плоскости.
 * Количество точек задается в консоли при запуске программы и оно должно быть больше двух.
 * Каждая точка задается своими координатами.
 * Точки не совпадают друг с другом.
 * Требуется найти минимальное и максимальное расстояние между точками.
 */

fun main() {
    try {
        // Input
        val invalidNumberMsg = "Must be more than 2 points"
        print("Enter points quantity ($invalidNumberMsg): ")
        val qty = readln().toInt()
        if (qty < 3) throw Exception("Invalid points number: $invalidNumberMsg")
        println("Enter distinct points' coordinates on a plane (must have different coordinates, " +
                "each from a new line, consisted of two values separated by a space, comma or semicolon)")
        val points = buildSet(qty) {
            do add(readln().toPoint(2) as Point2D)
            while (size < qty)
        }
        // Calculation
        val distances = Point.measureDistances(points)
        val min = distances.entries.first()
        val max = distances.entries.last()
        // Output
        println("The minimum distance is ≈" + String.format("%.2f", min.value) +
                " for ${min.key.first} and ${min.key.second}.")
        println("The maximum distance is ≈" + String.format("%.2f", max.value) +
                " for ${max.key.first} and ${max.key.second}.")
    } catch (exc: Exception) {
        println(exc.message)
    }
}

/* Sample I/O
=== Input 1 ===
Enter points quantity (Must be more than 2 points): 5
Enter distinct points' coordinates on a plane (must have different coordinates, each from a new line, consisted of two values separated by a space, comma or semicolon)
1 2
4 6
7 1
3 3
0 0
=== Output 1 ===
The minimum distance is ≈2.24 for Point2D (1.0, 2.0) and Point2D (3.0, 3.0).
The maximum distance is ≈7.21 for Point2D (4.0, 6.0) and Point2D (0.0, 0.0).

=== Input 2 ===
Enter points quantity (Must be more than 2 points): 4
Enter distinct points' coordinates on a plane (must have different coordinates, each from a new line, consisted of two values separated by a space, comma or semicolon)
1 1
2 2
2 2
3 3
3 3
3 3
4 4
=== Output 2 ===
The minimum distance is ≈1.41 for Point2D (1.0, 1.0) and Point2D (2.0, 2.0).
The maximum distance is ≈4.24 for Point2D (1.0, 1.0) and Point2D (4.0, 4.0).

=== Input 3 ===
Enter points quantity (Must be more than 2 points): 2
=== Output 3 ===
Invalid points number: Must be more than 2 points

=== Input 4 ===
Enter points quantity (Must be more than 2 points): 3
Enter distinct points' coordinates on a plane (must have different coordinates, each from a new line, consisted of two values separated by a space, comma or semicolon)
a b c
=== Input 4 ===
Cannot convert the string to a Point: For input string: "a"
 */
