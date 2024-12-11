/**
 * Represents a polygonal chain - a connected series of line segments
 * @property vertices a [List] of points
 * @property sides a [List] of [Segment] objects representing the [Chain] edges
 * @property length a [Float] number representing the sum of distances between all the [Chain] edges
 */
open class Chain(val vertices: List<Point2D>) : Figure {
    open val sides: List<Segment>
        get() =
            List(vertices.dropLast(1).size) { i -> Segment(Pair(vertices[i], vertices[i+1])) }
    open val length: Float
        get() = sides.fold(0f) { acc, v -> acc + v.length }

    /**
     * @param points a sequence of [Point2D] objects consisting the chain
     */
    constructor(vararg points: Point2D) : this(points.asList())

    /**
     * Gets a string representation of the [Chain]
     * @return a [String] representation of the [Chain] basic properties: type of object (class name) and vertices coordinates
     */
    override fun toString() = "$name $vertices"

    /**
     * Gets an extended string representation of the [Chain]
     * @return a [String] representation of the [Chain] additional properties such as its sides length and the total length
     */
    open fun toStringAll() = toString() + ", sides: ${sides.map { it.length }}, length: $length"
}