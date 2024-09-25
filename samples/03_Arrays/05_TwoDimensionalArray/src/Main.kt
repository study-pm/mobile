fun main() {
    val arrOneDimens: Array<Int> = Array(10) { (0..255).random() }
    arrOneDimens.forEach { print(it.toString() + "\t") }
    println()

    val arrTwoDimens: Array<Array<Int>> = Array(10) {
        Array(10) {
            (0..255).random()
        }
    }

    for (i in 0..9) {
        for (j in 0..9) {
            print(arrTwoDimens[i][j].toString() + "\t\t")
        }
        println()
    }
}
