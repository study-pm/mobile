/**
 * Represents a polygon with three corners and three sides on a plane
 * @param vertices a [List] of triangle apexes
 */
class Triangle(vertices: List<Point2D>) : Polygon(vertices) {

    /**
     * Gets the midpoints of all the triangle sides
     * @return a [List] of [Segment] objects representing the triangle median bases
     */
    private fun getMidpoints() = sides.map { Point2D(
        (it.points.first.x + it.points.second.x) / 2,
        (it.points.first.y + it.points.second.y) / 2
    ) }

    /**
     * Gets the lines that divides the triangle sides exactly into two halves forming 90 degrees angle at the intersection point
     * @return a [List] of straight [Line] objects representing the perpendicular bisectors
     */
    private fun getPerpendicularBisectorsEq() = getMidpoints().mapIndexed { i, p ->
        val k = 1 / (sides[i].eq.first / sides[i].eq.second)
        Line(Triple(k, -1f, p.y - k * p.x))
    }

    /**
     *  Gets the center of the circle that contains all the vertices of the [Triangle] (circumcircle)
     *  @return a perpendicular bisectors intersection [Point2D] representing the circumcenter
     */
    fun getCircumcenter(): Point2D {
        val bisectors = getPerpendicularBisectorsEq()
        return (bisectors[0] - bisectors[1])!!
    }

    /**
     *  Gets the radius of the circumscribed circle of the [Triangle]
     *  @return a [Point2D] representing the circumradius
     */
    fun getCircumradius(): Float = vertices[0] - getCircumcenter()

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
     * Gets a string representation of the [Triangle]
     * @return a [String] representation of the [Triangle] basic properties: type of object (class name) and vertices coordinates
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
