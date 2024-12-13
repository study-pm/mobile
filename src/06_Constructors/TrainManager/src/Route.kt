/**
 * Represents a particular way or direction between two cities
 * (a way or course taken in getting from a starting point to a destination)
 * @property value a [Pair] of two cities which consist the route
 * @property distance a [Double] number of km between the [Route] endpoints
 */
class Route : Nameable {
    val value = buildSet { while (size < 2) add(City.cities.random()) }.toPair()
    val distance = value.first distance value.second

    /**
     * Gets a string representation of a [Route]
     * @return a [String] representation of the [Route] general information: name, endpoint names and distance
     */
    override fun toString() = name + " from " + value.first.name +
            " to " + value.second.name + ", " +
            "distance: ${distance.toString().split(".")[0]} km"
}
