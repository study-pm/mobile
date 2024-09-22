fun main() {
    val testArrayInt = IntArray(5) { it * 5 }

    for (item in testArrayInt) print(item.toString() + "\t")
    println()
    for (item in testArrayInt) {
        if (item % 3 == 0) {
            print("кратно\t")
        } else {
            print("некратно\t")
        }
    }
    println()
    for ((index, value) in testArrayInt.withIndex()) {
        println("Индекс - $index. Значение - $value")
    }
}
