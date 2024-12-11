class InitOrderDemo(name: String) {
    val firstProperty = "Первое свойство: $name"

    init {
        println("Первый блок инициализации: $name")
    }

    val secondProperty = "Второе свойство: ${name.length}"

    init {
        println("Второй блок инициализации: ${name.length}")
    }
}

fun main() {
    InitOrderDemo("User")
}
