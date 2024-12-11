/**
 * Represents a straight line
 * @property eq a [Triple] of params representing the line equation coefficient
 */
open class Line(override val eq: Triple<Float, Float, Float>) : Curve {

    /**
     * param a [Float] number representing the param before x in the line equation
     * param b [Float] number representing the param before y in the line equation
     * param c [Float] number representing the c param in the line equation
     */
    constructor(a: Float, b: Float, c: Float) : this(Triple(a, b, c))

    /**
     * @param points a [Pair] of [Point2D] objects defining the line
     */
    constructor(points: Pair<Point2D, Point2D>) : this(getEq(points))

    /**
     * Gets two lines intersection point coordinates based on the Cramer's rule
     * @param l1 the first [Line]
     * @param l2 the second [Line]
     * @return a [Point2D] intersection of [l1] and [l2] or `null` if the lines are parallel
     */
    private fun getLineIntersection(l1: Line, l2: Line): Point2D? {
        val d = l1.eq.first * l2.eq.second - l2.eq.first * l1.eq.second
        if (d == 0f) return null
        val d1 = -l1.eq.third * l2.eq.second + l2.eq.third * l1.eq.second
        val d2 = -l1.eq.first * l2.eq.third  + l2.eq.first * l1.eq.third
        return Point2D(d1 / d, d2 / d)
    }

    /**
     * Gets point coordinates of intersection between a line and the given line
     * @param l a line
     * @return a [Point2D] intersection of [l] and the given line or `null` if the lines are parallel
     */
    fun intersects(l: Line) = getLineIntersection(this, l)

    /**
     * Gets a string representation of a [Line]
     * @return a [String] representation of [Line] basic properties: type of object (class name) and its equation
     */
    override fun toString() = "$name ${eq.first}x" +
            "${if (eq.second < 0) " " else " +"} ${eq.second}y" +
            "${if (eq.third < 0) " " else " +"} ${eq.third}"

    /**
     * Gets an intersection of a line and the given line
     * @param line a right side [Line]
     * @return a lines intersection [Point2D] or `null` if the lines are parallel
     */
     operator fun minus(line: Line) = this.intersects(line)

    /**
     * Contains utility methods associated with line segments
     */
    companion object {

        /**
         * Gets equation of a straight line defined by the two points
         * @param points a [Pair] of [Point2D] objects defining the line
         */
        fun getEq(points: Pair<Point2D, Point2D>): Triple<Float, Float, Float> {
            val a = points.first.y - points.second.y
            val b = points.second.x - points.first.x
            val c = points.first.x * points.second.y - points.second.x * points.first.y
            return Triple(a, b, c)
        }
    }
}
