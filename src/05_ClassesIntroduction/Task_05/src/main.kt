/* Окружность в треугольнике (обязательно использование класса Точка и класса Треугольник.
 * Класс Окружность и другие классы - по желанию)
 *
 * Треугольник расположен на координатной плоскости и описан координатами своих вершин.
 * Написать программу вычисляющую координаты центра вписанной в треугольник окружности и ее радиус.
 */

fun main() {
    try {
        // Input
        println("Enter the triangle apex coordinates on a plane " +
                "(must be two values separated by a space, comma or semicolon, each from a new line): ")
        val triangle = Triangle(buildList { (1..3).map { add(readln().toPoint(2) as Point2D) } })
        // Result
        val incenter = triangle.getIncenter()
        val inradius = triangle.getInradius()
        // Output
        println("The incenter for $triangle is $incenter, the inradius is ≈"
                + String.format("%.2f", inradius) + ".")
    }
    catch (exc: Exception) {
        println(exc.message)
    }
}

/* Sample I/O
=== Input 1 ===
Enter the triangle apex coordinates on a plane (the same format, each from a new line):
-4 4
-7 0
11 2
=== Output 1 ===
The incenter for Triangle [Point2D (-4.0, 4.0), Point2D (-7.0, 0.0), Point2D (11.0, 2.0)]) is Point2D (-3.2259662, 2.1557403), the inradius is ≈1.73.

=== Input 2 ===
Enter the triangle apex coordinates on a plane (the same format, each from a new line):
2 ,  , 3
4.56 7.0
6.8 ;  2
=== Output 2 ===
The incenter for Triangle [Point2D (2.0, 3.0), Point2D (4.56, 7.0), Point2D (6.8, 2.0)]) is Point2D (4.3360944, 3.982303), the inradius is ≈1.44.

=== Input 3 ===
Enter the triangle apex coordinates on a plane (the same format, each from a new line):

=== Output 3 ===
Cannot convert the string to a Point: Invalid context - must be exactly 2 coordinates provided

=== Input 4 ===
Enter the triangle apex coordinates on a plane (the same format, each from a new line):
a b
=== Output 4 ===
Cannot convert the string to a Point: For input string: "a"
 */
