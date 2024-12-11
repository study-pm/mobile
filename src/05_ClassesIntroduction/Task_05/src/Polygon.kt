import kotlin.math.abs

/**
 * Represents a plane figure made up of line segments connected to form a closed polygonal chain
 * @property perimeter a [Float] representing closed path that outlines either a two-dimensional shape
 * @property area a [Float] number representing the space surrounded or enclosed with the boundary of perimeter of the given [Polygon]
 * @property sides a [List] of [Segment] objects representing the [Polygon] edges
 */
open class Polygon(vertices: List<Point2D>) : Chain(vertices), Shape {
    private val perimeter: Float
        get() = length

    override val area: Float
        get() {
            val pos = vertices
                .dropLast(1)
                .foldIndexed(0f) { i, acc, p -> acc + vertices[i].x * vertices[i+1].y } +
                    vertices[vertices.lastIndex].x * vertices[0].y
            val neg = vertices
                .dropLast(1)
                .foldIndexed(0f) { i, acc, p -> acc + vertices[i+1].x * vertices[i].y } +
                    vertices[0].x * vertices[vertices.lastIndex].y
            return 0.5f * abs(pos - neg)
        }

    override val sides: List<Segment>
        get() = super.sides + Segment(Pair(vertices.last(), vertices[0]))

    /**
     * Gets an extended string representation of the [Polygon]
     * @return a [String] representation of the [Polygon] additional properties such as its perimeter and area
     */
    override fun toStringAll() = super.toStringAll() + ", perimeter: $perimeter, area: $area"
}
