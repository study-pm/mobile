/**
 * Represents a polygon with three corners and three sides on a plane
 * @param vertices a [List] of triangle apexes
 */
class Triangle(private val vertices: List<Point2D>) : Figure {
    /**
     * Gets a point position relative to the triangle
     * @return a [Location] object representing the point position
      */
    fun getPointRelativePosition(point: Point2D): Location {
        val prods = listOf(
            (vertices[0].x - point.x) * (vertices[1].y - vertices[0].y) -
                    (vertices[1].x - vertices[0].x) * (vertices[0].y - point.y),
            (vertices[1].x - point.x) * (vertices[2].y - vertices[1].y) -
                    (vertices[2].x - vertices[1].x) * (vertices[1].y - point.y),
            (vertices[2].x - point.x) * (vertices[0].y - vertices[2].y) -
                    (vertices[0].x - vertices[2].x) * (vertices[2].y - point.y)
        )
        return when {
            prods.all { it > 0 } || prods.all { it < 0 }    -> Location.In
            prods.any { it == 0f }                          -> Location.On
            else                                            -> Location.Out
        }
    }

    /**
     * Checks if the specified point is contained inside the triangle
     * @return `true` if the point is inside the triangle or on it's side, `false` otherwise
     */
    operator fun contains(point: Point2D): Boolean =
        getPointRelativePosition(point) == Location.In || getPointRelativePosition(point) == Location.On

    /**
     * Gets a string representation of a [Point]
     * @return a [String] representation of [Point] basic properties: type of object (class name) and coordinates
     */
    override fun toString() = "$name $vertices)"

    /**
     * Contains a point's possible locations relative to the given triangle
     */
    enum class Location (val value: Int) {
        In(1),  // inside the triangle
        On(0),  // on the side of the triangle
        Out(-1) // outside the triangle
    }
}
