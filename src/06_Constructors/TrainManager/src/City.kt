import kotlin.math.*

/**
 * Represents a single city (large town) along with its location
 * @param name a [String] name of the city
 * @param location a [Pair] of [Float] values representing the city coordinates (latitude and longitude)
 */
class City(val name: String, private val location: Pair<Float, Float>) {

    /**
     * Get the distance between the given city and the other one
     * @param other represents another [City] to calculate distance between it and the given city
     * @return a resulting distance as a [Double] value
     *
     * Usage examples
     * ```
     * val NY = City("New York", Pair(40.7128f, -74.0060f))
     * val LA = City("Los Angeles", Pair(34.0522f, -118.2437f))
     *
     * NY.distance(LA)  \\ => 3935.7463659448917
     * LA distance NY   \\ => 3935.7463659448917
     */
    infix fun distance(other: City): Double {
        return measureDistance(this.location, other.location)
    }

    /**
     * Get the travel time between the given city and the other one
     * @param other represents another [City] to calculate distance between it and the given city
     * @return a resulting time as a [Double] value
     */
    fun time(other: City): Double {
        return getTravelTime(this.location, other.location)
    }

    /**
     * Contains supplementary data related to [City]
     */
    companion object {
        /**
         * Specifies [Array] of [City] objects representing the larges U.S. cities with their coordinates
         */
        val cities = arrayOf(
            City("New York", Pair(40.7128f, -74.0060f)),
            City("Los Angeles", Pair(34.0522f, -118.2437f)),
            City("Chicago", Pair(41.8781f, -87.6298f)),
            City("Houston", Pair(29.7604f, -95.3698f)),
            City("Phoenix", Pair(33.4484f, -112.0740f)),
            City("Philadelphia", Pair(39.9526f, -75.1652f)),
            City("San Antonio", Pair(29.4241f, -98.4936f)),
            City("San Diego", Pair(32.7157f, -117.1611f)),
            City("Dallas", Pair(32.7767f, -96.7970f)),
            City("San Jose", Pair(37.3382f, -121.8863f)),
            City("Austin", Pair(30.2672f, -97.7431f)),
            City("Jacksonville", Pair(30.3322f, -81.6557f)),
            City("San Francisco", Pair(37.7749f, -122.4194f)),
            City("Columbus", Pair(39.9612f, -82.9988f)),
            City("Fort Worth", Pair(32.7555f, -97.3308f)),
            City("Indianapolis", Pair(39.7684f, -86.1581f)),
            City("Charlotte", Pair(35.2271f, -80.8431f)),
            City("Seattle", Pair(47.6062f, -122.3321f)),
            City("Denver", Pair(39.7392f, -104.9903f)),
            City("El Paso", Pair(31.7619f, -106.4850f)),
            City("Washington, D.C.", Pair(38.9072f, -77.0369f))
        )

        /** Get distance between two points using Haversine formula to calculate distance having two coordinates
         * @param c1 a [Pair] of [Float] values representing first point coordinates
         * @param c2 a [Pair] of [Float] values representing second point coordinates
         * @return a [Double] distance in kilometers
         */
        fun measureDistance(c1: Pair<Float, Float>, c2: Pair<Float, Float>): Double {
            val r = 6371.0 // Radius of the Earth in kilometers
            val p1 = Pair(c1.first.toRad(), c1.second.toRad())
            val p2 = Pair(c2.first.toRad(), c2.second.toRad())

            val dLat = p2.first - p1.first
            val dLon = p2.second - p1.second

            val a = sin(dLat / 2).pow(2) + cos(p1.first) * cos(p2.first) * sin(dLon / 2).pow(2)
            val c = 2 * atan2(sqrt(a), sqrt(1 - a))

            return r * c
        }

        /**
         * Calculate travel time based on distance and average train speed
         * @param speed average train speed
         * @param c1 a [Pair] of [Float] values representing first point coordinates
         * @param c2 a [Pair] of [Float] values representing second point coordinates
         * @return a [Double] representing travel time in hours
         */
        fun getTravelTime(c1: Pair<Float, Float>, c2: Pair<Float, Float>, speed: Double = 100.0) =
            measureDistance(c1, c2) / speed
    }
}
