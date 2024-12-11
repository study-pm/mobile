/**
 * Represents a line segment - a part of a straight line that is bounded by two distinct end points,
 * and contains every point on the line that is between its endpoints (a fully closed interval).
 * @param points a [Pair] of endpoints represented by [Point2D] objects
 * @property length a [Float] number representing the distance between the segment endpoints
 */

open class Segment(val points: Pair<Point2D, Point2D>) : Line(points), Arc {

    constructor(p1: Point2D, p2: Point2D) : this(Pair(p1, p2))

    override val length: Float
        get() = points.second - points.first

    /**
     * Gets a string representation of the [Segment]
     * @return a [String] representation of the [Segment] basic properties: type of object (class name) and its endpoints
     */
    override fun toString() = "$name $points"

    /**
     * Gets an extended string representation of the [Segment]
     * @return a [String] representation of the [Segment] additional properties such as its perimeter and area
     */
    fun toStringAll() = toString() + ", equation: $eq"

}
