fun main() {
    val arrOne = IntArray(10) { i -> i * 3 }

    for (item in arrOne) {
        print(item.toString() + "\t")
    }
    println()

    var iter = arrOne.iterator()
    while (iter.hasNext()) {
        val item = iter.next()
        print(item.toString() + "\t")
    }
}
