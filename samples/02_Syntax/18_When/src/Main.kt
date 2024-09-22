fun main() {
    val testRandom = (1..9).random()
    println("testRandom: $testRandom")
    when (testRandom) {
        1 -> println("testRandom == 1")
        2, 4, 6, 8 -> println("testRandom == 2 или 4 или 6 или 8")
        else -> { // обратите внимание на блок
            println("testRandom не равен 1 и не кратно 2")
        }
    }
    val testArrayInt = IntArray(10) { it }
    val testRandomNew = (1..30).random()
    println("testRandomNew: $testRandomNew")
    when (testRandomNew) {
        in 10..20 -> println("Значение в промежутке от 10 до 20")
        in testArrayInt -> println("Значение попадает в массив с числами от 0 до 9")
        !in 21..25 -> println("Значение больше 25")
        else -> println("Значение в промежутке от 21 до 25")
    }
    val testRem = when(testRandomNew) {
        in 1..15 -> testRandomNew
        else -> "Значение больше 15"
    }
    println(testRem)
}
