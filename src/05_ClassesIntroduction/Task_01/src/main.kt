/* Точка и треугольник (создать класс Точка и класс Треугольник)
 *
 * Треугольник описан координатами трех своих вершин. Указаны координаты отдельной точки.
 * Составить программу, показывающую, где находится точка - внутри или вне треугольника.
 * Треугольник и точка находятся на координатной плоскости.
 *
 * Обратить внимание на обработку ошибок при вводе координат.
 * Программа не должна "падать" при вводе неправильных значений.
 */

fun main() {
    try {
        // Input
        print("Enter the point coordinates on a plane (must be two values separated by a space, comma or semicolon): ")
        val p = readln().toPoint(2) as Point2D
        println("Enter the triangle apex coordinates on a plane (the same format, each from a new line): ")
        val triangle = Triangle(buildList { (1..3).map { add(readln().toPoint(2) as Point2D) } })
        // Output
        when (triangle.getPointRelativePosition(p)) {
            Triangle.Location.In    -> println("$p is inside the $triangle.")
            Triangle.Location.On    -> println("$p is on the side of the $triangle.")
            Triangle.Location.Out   -> println("$p is outside the $triangle.")
        }
    } catch (exc: Exception) {
        println(exc.message)
    }
}

/* Sample I/O
=== Input ===
Enter the point coordinates on a plane (must be two values separated by a space, comma or semicolon): -2 1.5
Enter the triangle apex coordinates on a plane (the same format, each from a new line):
-4; 4
-7; 0
11; 2
=== Output ==
Point2D (-2.0, 1.5) is inside the Triangle [Point2D (-4.0, 4.0), Point2D (-7.0, 0.0), Point2D (11.0, 2.0)]).

=== Input 2 ==
Enter the point coordinates on a plane (must be two values separated by a space, comma or semicolon):  2 ; 1
Enter the triangle apex coordinates on a plane (the same format, each from a new line):
-4 4
-7 0
11 2
=== Output ===
Point2D (2.0, 1.0) is on the side of the Triangle [Point2D (-4.0, 4.0), Point2D (-7.0, 0.0), Point2D (11.0, 2.0)]).

=== Input 3 ===
Enter the point coordinates on a plane (must be two values separated by a space, comma or semicolon): -2.34 5.6
Enter the triangle apex coordinates on a plane (the same format, each from a new line):
-4 , 4
-7 , 0
11 , 2
=== Output 3 ===
Point2D (-2.34, 5.6) is outside the Triangle [Point2D (-4.0, 4.0), Point2D (-7.0, 0.0), Point2D (11.0, 2.0)]).

=== Input 4 ===
Enter the point coordinates on a plane (must be two values separated by a space, comma or semicolon):
=== Output 4 ===
Cannot convert the string to a Point: Invalid context - must be exactly 2 coordinates provided

=== Input 5 ===
Enter the point coordinates on a plane (must be two values separated by a space, comma or semicolon): 1
=== Output 5 ===
Cannot convert the string to a Point: Invalid context - must be exactly 2 coordinates provided

=== Input 6 ===
Enter the point coordinates on a plane (must be two values separated by a space, comma or semicolon): 1 2 3 4
=== Output 6 ===
Cannot convert the string to a Point: Invalid context - must be exactly 2 coordinates provided

=== Input 7 ===
Enter the point coordinates on a plane (must be two values separated by a space, comma or semicolon): a b c
=== Output 7
Cannot convert the string to a Point: For input string: "a"
 */
