/**
 * Represents any of the separate sections of a train that carry passengers (a single train carriage)
 * @param capacity an [Int] specifying the maximum quantity of riders the car can accommodate
 * @property passengers an [Int] number of riders in the car
 */
class Car(var capacity: Int = capacityRange.random()) : Nameable {
    var passengers: Int = 0

    /**
     * Fill in the car with passengers
     * @param riders an [Int] number of passenger candidates
     * @return a remaining [Int] number of passengers that could not fit into the carriage
     */
    fun fillInCar(riders: Int): Int {
        passengers = if (riders > capacity) capacity else riders
        return passengers
    }

    /**
     * Gets a string representation of a [Car]
     * @return a [String] representation of the [Car] basic properties: name, passengers number, capacity
     */
    override fun toString() = "[$name $passengers/$capacity]"

    /**
     * Contains supplementary data related to [Car]
     */
    companion object {
        /**
         * Specifies possible capacity as an [IntRange] from min to max values
         */
        val capacityRange = 5..25
    }
}
