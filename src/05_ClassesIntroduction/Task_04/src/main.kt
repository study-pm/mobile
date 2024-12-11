/* Треугольник в окружности (обязательно использование класса Точка и класса Треугольник. Класс Окружность и другие классы - по желанию)
 *
 * Треугольник расположен на координатной плоскости и описан координатами своих вершин.
 * Написать программу вычисляющую координаты центра описанной вокруг треугольника окружности и ее радиус.
 */

fun main() {
    try {
        // Input
        println("Enter the triangle apex coordinates on a plane (the same format, each from a new line): ")
        val triangle = Triangle(buildList { (1..3).map { add(readln().toPoint(2) as Point2D) } })
        // Result
        val circumcenter = triangle.getCircumcenter()
        val circumradius = triangle.getCircumradius()
        // Output
        println("The circumcenter for $triangle is $circumcenter, the circumradius is ≈"
                + String.format("%.2f", circumradius) + ".")
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
The circumcenter for Triangle [Point2D (-4.0, 4.0), Point2D (-7.0, 0.0), Point2D (11.0, 2.0)]) is Point2D (2.560606, -4.0454545), the circumradius is ≈10.38.

=== Input 2 ===
Enter the triangle apex coordinates on a plane (the same format, each from a new line):
2 ,  , 3
4.56 7.0
6.8 ;  2
=== Output 2 ===
The circumcenter for Triangle [Point2D (2.0, 3.0), Point2D (4.56, 7.0), Point2D (6.8, 2.0)]) is Point2D (4.7277946, 4.073412), the circumradius is ≈2.93.

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
