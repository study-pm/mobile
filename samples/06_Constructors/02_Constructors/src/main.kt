class InitOrderDemo (emp_id: Int = 100, emp_name: String = "User") {
    val id: Int
    val name: String
    init {
        id = emp_id
        name = emp_name

        println("Значение id: $id")
        println("Значение name: $name")
        println()
    }
}

fun main() {
    val empOne = InitOrderDemo(249857, "Александр")
    val empTwo = InitOrderDemo(372648)
    val empThree = InitOrderDemo()
}
