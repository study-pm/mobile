fun main() {
    val width = 5
    val height = 16

    println("Площадь = ${calcRecArea(width, height)}")
    println("Периметр = ${calcPerimeter(width, height)}")
    println("S = ${calcRecArea(17, 67)}")
    println("P = ${calcPerimeter(17, 67)}")

}

fun calcRecArea(width: Int, height: Int) = width * height

fun calcPerimeter(width: Int, height: Int) = 2 * (width + height)
