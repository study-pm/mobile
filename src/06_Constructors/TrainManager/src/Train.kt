/**
 * Represents a series of connected vehicles (cas) that run along a railway track and transport people or freight
 * @param cars a [MutableList] of [Car] objects consisting a train *
 */
class Train(var cars: MutableList<Car> = mutableListOf()) : Nameable {
    /**
     * Gets a string representation of a [Train]
     * @return a [String] representation of the [Train] general information: name of object and cars information
     */
    override fun toString(): String {
        var carsStr = ""
        cars.forEach { carsStr += "$it-" }
        return "$name ${carsStr.dropLast(1)}"
    }

    /**
     * Gets a verbose string representation of a [Train]
     * @return a [String] representation of the [Train] extended information: car numbers, seats vacant and occupied
     */
    fun toStringVerbose():String {
        var res = ""
        cars.forEachIndexed { i, v ->
            res += "Car ${i + 1} with ${v.capacity} seats occupied by ${v.passengers} passengers\n"
        }
        return res
    }
}
