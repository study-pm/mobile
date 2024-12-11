/**
 * Provides an algebraic curve features
 * @property eq a [Triple] representing equation coefficients describing a curve
 */
interface Curve: Figure {
    /**
     * Represents equation params as `Triple<a, b, c>` for the equation form of ax + by + c
     */
    val eq: Triple<Float, Float, Float>
}
