import kotlin.math.*

/* Создать приложение, с помощью которого пользователь, введя целое число n и основание степени x узнает,
 * существует ли целочисленный показатель степени y для которого выполняется равенство x^y = n.
 * В случае, если показатель существует – вычислить его и вывести.
 * В противном случае вывести текст – «Целочисленный показатель не существует».
 */

/**
 * Checks if ∃(y ∈ ℤ) P(y), P(y) : x^y = n, n ∈ ℤ, x ∈ ℝ and gets `y` if such an expression is true
 * @param n candidate power
 * @param x candidate power base
 * @return pair `<doesExist, exponent?>`, where
 *
 * `doesExist`: `true` if ∃(y ∈ ℤ) P(y), `false` if ∄(y ∈ ℤ) P(y)
 *
 * `exponent`: ∃!(y ∈ ℤ) P(y) => `y`, ∄(y ∈ ℤ) P(y) => `null`, ∃(y ∈ Y : Y ⊂ ℤ, |Y| > 1) P(y) => `null`
 *
 * Usage examples demonstrating some typical and edge cases:
 * ```kotlin
 * checkIfExponentExists(8, 2)      // => (true, 3)     // 2^3 = 8      [y = 3]
 * checkIfExponentExists(-8, 2)     // => (false, null) // 2^! = -8     [y ∈ ∅]
 * checkIfExponentExists(8, -2)     // => (false, null) // (-2)^! = 8   [y ∈ ∅]
 * checkIfExponentExists(-8, -2)    // => (true, 3)     // (-2)^3 = -8  [y = 3]
 * checkIfExponentExists(4, 2)      // => (true, 2)     // 2^2 = 4      [y = 2]
 * checkIfExponentExists(-4, 2)     // => (false, null) // 2^! = -4     [y ∈ ∅]
 * checkIfExponentExists(-4, -2)    // => (false, null) // (-2)^! = -4  [y ∈ ∅]
 * checkIfExponentExists(2, 8)      // => (false, null) // 8^(1/3) = 2  [y = 1/3, y ∉ ℤ]
 * checkIfExponentExists(2, 2)      // => (true, 1)     // 2^1 = 2      [y = 1]
 * checkIfExponentExists(2, 1)      // => (false, null) // 2^! = 1      [y ∈ ∅]
 * checkIfExponentExists(2, 0)      // => (false, null) // 2^! = 0      [y ∈ ∅]
 * checkIfExponentExists(2, .5)     // => (true, -1)    // .5^(-1) = 2  [y ∈ -1]
 * checkIfExponentExists(4, .5)     // => (true, -2)    // .5^(-2) = 4  [y ∈ -2]
 * checkIfExponentExists(2, -2)     // => (false, null) // 2^! = -2     [y ∈ ∅]
 * checkIfExponentExists(1, 1)      // => (true, null)  // 1^? = 1      [y ∈ ℝ]
 * checkIfExponentExists(-1, 1)     // => (false, null) // 1^! = -1     [y ∈ ∅]
 * checkIfExponentExists(1, -1)     // => (true, null)  // (-1)^? = 1   [y = 2k, k ∈ ℤ]: y ∈ {...,-4, -2, 0, 2, 4, ...}
 * checkIfExponentExists(-1, -1)    // => (true, null)  // (-1)^? = -1  [y = 2k+1, k ∈ ℤ]: y ∈ {...,-3, -1, 1, 3, ...}
 * checkIfExponentExists(1, 0)      // => (false, 0)    // 0^0 = 1(?)   [y ∈ ∅] or [y = 0]
 * checkIfExponentExists(0, 1)      // => (false, null) // 1^! = 0      [y ∈ ∅]
 * checkIfExponentExists(0, 0)      // => (true, null)  // 0^? = 0      [y ∈ ℤ+]
 * checkIfExponentExists(1, 2)      // => (true, 0)     // 2^0 = 1      [y = 0]
 * checkIfExponentExists(-1, 2)     // => (false, null) // 2^! = -1     [y ∈ ∅]
 * checkIfExponentExists(1, -2)     // => (true, 0      // (-2)^0 = 1   [y = 0]
 * checkIfExponentExists(9, .33333333333333333333)      // => (true, -2) (1/3)^(-2) = 9
 */
fun checkExponent(n: Int, x: Double): Pair<Boolean, Int?> {
    var y = n.toDouble()
    var i = 0
    var doesExist = false
    if (x == 1.0) return Pair((n == 1), null)
    if (x == -1.0) return Pair((abs(n) == 1), null)
    if (n == 1) return Pair(x != 0.0, if (x == 1.0) null else 0)
    if (n == 0) return Pair(x == 0.0, null)
    do {
        y /= if (abs(x) < 1) 1/x else x
        if (abs(x) < 1) i-- else i++
        if (y == 1.0) doesExist = true
    }
    while (abs(y) > 1)
    return Pair(doesExist, if (doesExist) i else null)
}

fun main() {
    try {
        print("Enter n (a candidate power, must be an integer): ")
        val n = readln().toInt()
        print("Enter x (a candidate base of power, must be a real number): ")
        val x = readln().toDouble()
        val (doesExist, y) = checkExponent(n, x)
        if ((doesExist) and (y == null)) {
            println("Multiple integer exponents exist.")
        }
        else if (doesExist) {
            println("A single integer exponent exists and equals to $y.")
        }
        else if (y == 0) {
            println("An integer exponent does not exist or may be 0.")
        }
        else {
            println("An integer exponent does not exist.")
        }
    }
    catch (exc: Exception) {
        println("Invalid input: " + exc.message)
    }
}
