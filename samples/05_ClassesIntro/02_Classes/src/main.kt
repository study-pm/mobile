fun main() {
    val rectOne = Rectangle(5, 19)

    println("Ширина - ${rectOne.width}, высота - ${rectOne.height}")
    println("Площадь - ${rectOne.calcArea()}")
    println("Площадь - ${rectOne.calcPerimeter()}")

    val rectTwo = Rectangle(7, 14)
    println("S = ${rectTwo.calcArea()}")
    println("P = ${rectTwo.calcPerimeter()}")
}
