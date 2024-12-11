fun main() {
    val personOne = Person()
    val personTwo = Person()
    val personThree = Person()

    personOne.age = 19
    println(personOne.age)

    personTwo.age = 166
    println(personTwo.age)

    personThree.age = -19
    println(personTwo.age)
}

class Person() {
    var age = 0
        set(value) {
            if ((value > 0) and (value < 124)) {
                field = value
            } else {
                field = 45
                println("Неправильный возраст")
            }
        }
}
