/**
 * Represents a line segment - a part of a straight line that is bounded by two distinct end points,
 * and contains every point on the line that is between its endpoints (a fully closed interval).
 * @param points a [Pair] of endpoints represented by [Point2D] objects
 * @property length a [Float] number representing the distance between the segment endpoints
 */

open class Segment(val points: Pair<Point2D, Point2D>) : Line(points), Arc {

    override val length: Float
        get() = points.second - points.first

    /**
     * Gets a string representation of a [Segment]
     * @return a [String] representation of [Segment] basic properties: type of object (class name) and its endpoints
     */
    override fun toString() = "$name $points"

}
