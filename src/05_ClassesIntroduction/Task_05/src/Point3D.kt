/**
 * Represents a three-dimensional point (point in a space)
 * @param value a [FloatArray] of the point coordinates
 * @property x a [Float] value of x coordinate
 * @property y a [Float] value of y coordinate
 * @property z a [Float] value of z coordinate
 */
class Point3D(value: FloatArray) : Point<Float>(value) {
    val x: Float
        get() = this.value[0]
    val y: Float
        get() = this.value[1]
    val z: Float
        get() = this.value[2]

    /**
     * @param x a [Float] number representing the x coordinate of [Point3D]
     * @param y a [Float] number representing the y coordinate of [Point3D]
     * @param z a [Float] number representing the z coordinate of [Point3D]
     */
    constructor(x: Float, y: Float, z: Float): this(floatArrayOf(x, y, z))
}
