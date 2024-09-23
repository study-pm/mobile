fun main() {
    for (i in 1..10) print(i.toString() + "\t")
    println()

    for (i in 1 until 10) print(i.toString() + "\t")
    println()

    for (i in 10 downTo 1) print(i.toString() + "\t")
    println()

    for (i in 1..10 step 3) print(i.toString() + "\t")
    println()
}
