/* Расстояние между точками (с использованием класса Точка)
 *
 * Две точки находятся на координатной плоскости. Каждая точка описана своими координатами.
 * Найти расстояние между указанными точками.
 */

fun main() {
    // Input
    print("Enter the first point coordinates on a plane (must be two values separated by a space, comma or semicolon): ")
    val a = readln().toPoint(2) as Point2D
    print("Enter the second point coordinates on a plane (must be two values separated by a space, comma or semicolon): ")
    val b = readln().toPoint(2) as Point2D
    // Result
    val distance = a - b
    // Output
    println("The distance between $a and $b is " + String.format("%.2f", distance))
}

/* Sample I/O
=== Input 1 ==
Enter the first point coordinates on a plane (must be two values separated by a space, comma or semicolon): 1, 2
Enter the second point coordinates on a plane (must be two values separated by a space, comma or semicolon): 3 4
=== Output 2
The distance between Point2D (1.0, 2.0) and Point2D (3.0, 4.0) is 2.83

=== Input 2 ===
Enter the first point coordinates on a plane (must be two values separated by a space, comma or semicolon): 1.23 3.4
Enter the second point coordinates on a plane (must be two values separated by a space, comma or semicolon): 0 ; 0
=== Output 2 ===
The distance between Point2D (1.23, 3.4) and Point2D (0.0, 0.0) is 3.62

=== Input 3 ===
Enter the first point coordinates on a plane (must be two values separated by a space, comma or semicolon): -5 -2.789
Enter the second point coordinates on a plane (must be two values separated by a space, comma or semicolon): 157 2346
=== Output 3 ===
The distance between Point2D (-5.0, -2.789) and Point2D (157.0, 2346.0) is 2354.37
 */
