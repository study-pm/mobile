fun main() {
    val num = mapOf(1 to "one", 2 to "two", 3 to "three", 4 to "three")
    val k = num.keys
    val v = num.values
    val e = num.entries

    println(k)
    println(v)
    println(e)

    for ((i, j) in e)
        println("The $i is $j!")
}
