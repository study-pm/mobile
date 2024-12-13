/**
 * Represents a Train Manager
 * @property route a single [Route] object from one [City] to another
 * @property tickets an [Int] number of tickets sold
 * @property train a single [Train] object
 * @property isArrived a [Boolean] flag showing if the train arrived to the destination point
 * @property isLeft a [Boolean] flag showing if the train left off the departure point
 */
class Manager : Manageable {

    override var isArrived: Boolean = false

    override var isLeft: Boolean = false

    override var route: Route = Route()

    override var tickets: Int = 0

    override var train: Train = Train()

    /**
     * Creates a single randomly formed [Route]
     * @returns a new [Route] object with cities
     */
    override fun createRoute() = Route()

    /**
     * Makes up a single [Train] filling in the cars and appending them to a new [Train]
     * @return a fully complete [Train] object with cars filled with passengers
     */
    override fun makeUpTrain(): Train {
        val train = Train()
        var unset = tickets
        while (unset > 0) {
            val car = Car()
            val riders = car.fillInCar(unset)
            unset -= riders
            train.cars.add(car)
        }
        return train
    }

    /**
     * Sells tickets
     * @param bookingOffice a [Booking] instance
     * @return a total [Int] number of sold tickets
     */
    override fun sellTickets(bookingOffice: Booking): Int {
        return bookingOffice.sellTickets()
    }

    /**
     * Prints a dot without a newline at the specified intervals of sleep
     * @param dist an [Int] value corresponding to the relative distance between the [Route] endpoints
     * @param interval a [Long] number of milliseconds for thread to sleep for between repeating the dot graphics
     * @return `true`
     */
    override fun sendTrain(dist: Int, interval: Long, sound: String): Boolean {
        for (i in 1..dist) {
            print(if (i % 6 == 0) sound else ".")
            Thread.sleep(interval)
        }
        print(".")
        return true
    }
}
