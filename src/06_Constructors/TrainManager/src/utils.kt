import kotlin.math.PI

/**
 * Parses [Set] to a [Pair] object
 * @return a [Pair] made up of the source [Set] elements
 * @throws [IllegalArgumentException] when a source [Set] contains more or less than 2 elements
 */
fun <T> Set<T>.toPair(): Pair<T, T> {
    if (this.size != 2) {
        throw IllegalArgumentException("List is not of length 2!")
    }
    return Pair(this.elementAt(0), this.elementAt(1))
}

/**
 * Converts a [Double] degrees value to radians
 * @return a [Double] representation of a radian value
 */
fun Float.toRad() = this * (PI / 180)
