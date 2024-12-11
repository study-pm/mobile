fun main() {
    val personOne = Person(19)
    val personTwo = Person(166)
    val personThree = Person(-19)

    personOne.age = 55
    println(personOne.age)
    personOne.prnAge()
    println()

    println(personTwo.age)
    personTwo.prnAge()
    println()

    personThree.age = -19
    println(personTwo.age)
    personThree.prnAge()
}

class Person(_age: Int) {
    var age = _age
        set(value) {
            if ((value > 0) and (value < 124)) {
                field = value
            } else {
                field = 45
                println("Неправильный возраст")
            }
        }
        get() {
            if ((field > 0) and (field < 124)) {
                return field
            } else {
                println("Установленный возраст")
                return 85
            }
        }
    fun prnAge() = println("Возраст $age")

}
