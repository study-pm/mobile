/**
 * Represents a one-dimensional point (point on a surface)
 * @param value a [FloatArray] of the point coordinates
 * @property x a [Float] value of x coordinate
 * @property y a [Float] value of y coordinate
 */
class Point2D(value: FloatArray) : Point<Float>(value) {
    val x: Float
        get() = this.value[0]
    val y: Float
        get() = this.value[1]

    /**
     * @param x a [Float] number representing the x coordinate of [Point2D]
     * @param y a [Float] number representing the y coordinate of [Point2D]
     */
    constructor(x: Float, y: Float): this(floatArrayOf(x, y))
}
