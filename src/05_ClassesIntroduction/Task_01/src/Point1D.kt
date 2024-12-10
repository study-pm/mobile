/**
 * Represents a one-dimensional point (point on a line)
 * @param value a [FloatArray] of the point coordinates
 * @property x a [Float] value of x coordinate
 */
class Point1D(value: FloatArray) : Point<Float>(value) {
    val x: Float
        get() = this.value[0]

    constructor(x: Float): this(floatArrayOf(x))
}
