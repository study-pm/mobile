import kotlin.math.pow

fun main() {
    val arrNum = arrayOfNulls<Number>(6)

    arrNum.forEach { print(it.toString() + "\t") }

    println()

    for (i in arrNum.indices) {
        print("before: ${arrNum[i]}, ")
        arrNum[i] = i * i
        print("after: ${arrNum[i]}\n")
    }

    for (i in 0..arrNum.size - 1) {
        arrNum[i] = i.toDouble().pow(i)
    }

    arrNum.forEach { print(it.toString() + "\t") }
}
