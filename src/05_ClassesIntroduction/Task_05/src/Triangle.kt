/**
 * Represents a polygon with three corners and three sides on a plane
 * @param vertices a [List] of triangle apexes
 */
class Triangle(vertices: List<Point2D>) : Polygon(vertices) {

    /**
     * @param a a [Point2D] representing the triangle vertex
     * @param b a [Point2D] representing the triangle vertex
     * @param c a [Point2D] representing the triangle vertex
     */
    constructor(a: Point2D, b: Point2D, c: Point2D) : this(listOf(a, b, c))

    /**
     * Gets the points of angle bisectors intersection with the opposite sides
     * @return a [List] of angle bisectors bases
     */
    private fun getAngleBisectorBases() = vertices.map { apex ->
            val adj = sides.filter { it.points.first == apex || it.points.second == apex }
            val opp1 = adj.first().points.toList().first { it != apex }
            val opp2 = adj.last().points.toList().first { it != apex }
            val sum = adj.first().length + adj.last().length
            val x = (adj.last().length * opp1.x + adj.first().length * opp2.x) / sum
            val y = (adj.last().length * opp1.y + adj.first().length * opp2.y) / sum
            Point2D(x, y)
    }

    /**
     * Gets straight lines through the vertices that cut the corresponding angles in halves
     * @return a [List] of [Line] objects representing the angle bisectors
     */
    private fun getAngleBisectorLines(): List<Line> {
        val bases = getAngleBisectorBases()
        return vertices.mapIndexed { i, p -> Line(p, bases[i]) }
    }

    /**
     *  Gets the center of the circle that contains all the vertices of the [Triangle] (circumcircle)
     *  @return a perpendicular bisectors intersection [Point2D] representing the circumcenter
     */
    fun getCircumcenter(): Point2D {
        val bisectors = getPerpendicularBisectorLines()
        return (bisectors[0] - bisectors[1])!!
    }

    /**
     *  Gets the radius of the circumscribed circle of the [Triangle]
     *  @return a [Point2D] representing the circumradius
     */
    fun getCircumradius(): Float = vertices[0] - getCircumcenter()

    /**
     * Gets the center of the largest circle that can be contained in the triangle and tangent to the three sides
     * @return a [Point2D] representing the center of the inscribed circle
     */
    fun getIncenter(): Point2D {
        val angleBisectors = getAngleBisectorLines()
        return (angleBisectors[0] - angleBisectors[1])!!
    }

    /**
     *  Gets the radius of the inscribed circle of the [Triangle]
     *  @return a [Point2D] representing the inradius
     */
    fun getInradius() = sides[0].distance(getIncenter())

    /**
     * Gets line segments joining vertices to the midpoints of the opposite sides, thus bisecting those sides
     * @return a [List] of [Line] objects representing the triangle medians
     */
    fun getMedianLines(): List<Line> {
        val apexShift = listOf(vertices[2], vertices[0], vertices[1])
        return getMidpoints().mapIndexed { i, p -> Segment(apexShift[i], p) }.map { Line(it.eq) }
    }

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
    private fun getPerpendicularBisectorLines() = getMidpoints().mapIndexed { i, p ->
        val k = 1 / (sides[i].eq.first / sides[i].eq.second)
        Line(Triple(k, -1f, p.y - k * p.x))
    }

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
