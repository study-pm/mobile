// Создать приложение, которое преобразует введенное пользователем натуральное число из десятичной системы в двоичную.

/**
 * Returns the binary string representation of decimal input
 * @param d the input number
 * @return binary representation of decimal input
 */
fun convertDecToBinary(d: UInt): String {
    return d.toString(2)
}
fun convertDecToBinary(d: Int): String {
    var res = ""
    var quotient = if (d < 0) -d else d
    do {
        res = (quotient % 2).toString() + res
        quotient /= 2
    } while (quotient != 0)
    return if (d < 0) "-$res" else res
}

fun main() {
    print("Enter a natural number: ")
    try {
        val n10 = readln().toUInt()
        val n2 = convertDecToBinary(n10)
        println(n2)
    }
    catch(e: Exception) {
        println(e.message)
    }
}

// Sample input:    375
// Expected output: 101110111
