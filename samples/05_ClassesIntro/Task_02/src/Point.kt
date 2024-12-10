import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Represents a geometry point object
 * @param value a [FloatArray] of the point coordinates
 * @property context defines a space dimensionality
 */
abstract class Point<T> (internal val value: FloatArray): Figure {
    private val context = this.value.size

    /**
     * Gets distance between any point and the given point
     * @param p a [Point]
     * @return distance between the points
     */
    private fun distance(p: Point<T>) = measureDistance(p, this)

    /**
     * Gets a string representation of a [Point]
     * @return a [String] representation of [Point] basic properties: type of object (class name) and coordinates
     */
    override fun toString() = "$name (${value.joinToString()})"

    /**
     * Gets distance between any point and the given point
     * @param p a right side [Point]
     * @return distance between the points
     */
    operator fun minus(point: Point<T>): Float = this.distance(point)

    /**
     * Contains utility methods associated with points
     */
    companion object {

        /**
         * Gets distance between 2 points
         * @param a the first [Point]
         * @param b the second [Point]
         * @return distance between the points
         *
         * Usage example
         * ```
         * Point.measureDistance(Point3D(1f, 2f, 3f), Point3D(4f, 5f, 6f)) // => 5.196152
         * ```
         */
        fun<T> measureDistance(a: Point<T>, b: Point<T>) =
            sqrt(a.value.foldIndexed(0f) { i, acc, _ -> acc + (b.value[i] - a.value[i]).pow(2) })

        /**
         * Parse [String] input trimming both the input string and each value as well as omitting empty values
         * @param s an input [String]
         *
         */
        fun parseValue(s: String) = s.trim().split(" ", ",", ";", ", ", "; ")
            .filter { it.isNotEmpty() }
            .map { it.trim().toFloat() }.toFloatArray()
    }
}

/**
 * Parses string to a [Point] object
 * @param context an [Int] defining the target context (1D, 2D, 3D) for stricter check while parsing
 * @return a [Point] of a specified or auto inferred dimensionality
 * @throws [IllegalArgumentException] when a string cannot be converted to a [Point] or context mismatch
 *
 * Usage example
 * ```
 * "1 2.3 4.56".toPoint() // => Point3D (1.0, 2.3, 4.56)
 * ```
 */
fun String.toPoint(context: Int = 0): Point<*> {
    try {
        val value = Point.parseValue(this)
        if (context != 0 && context != value.size) {
            throw Exception("Invalid context - must be exactly " +
                    "$context coordinate${if (context == 1) "" else "s"} provided")
        }
        return when (value.size) {
            1       -> Point1D(value)
            2       -> Point2D(value)
            3       -> Point3D(value)
            else    -> throw Exception("Invalid context - unsupported dimension")
        }
    }
    catch (exc: Exception) {
        throw IllegalArgumentException("Cannot convert the string to a Point: ${exc.message}")
    }
}
