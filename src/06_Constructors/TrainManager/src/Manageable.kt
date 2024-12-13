/**
 * Provides actions for managing the trains
 * @property route a [Route] object from one [City] to another
 * @property tickets an [Int] number of tickets sold
 * @property train a [Train] object
 * @property isArrived a [Boolean] flag showing if the train arrived to the destination point
 * @property isLeft a [Boolean] flag showing if the train left off the departure point
 */
interface Manageable {
    val route: Route
    var tickets: Int
    var train: Train
    var isArrived: Boolean
    var isLeft: Boolean

    /**
     * Creates a single [Route]
     * @returns a new [Route] object with cities
     */
    fun createRoute(): Route

    /**
     * Makes up a single [Train]
     * @return a fully complete [Train] object with cars filled with passengers
     */
    fun makeUpTrain(): Train

    /**
     * Sells tickets
     * @param bookingOffice a [Booking] instance
     * @return a total [Int] number of sold tickets
     */
    fun sellTickets(bookingOffice: Booking): Int

    /**
     * Sends the [Train] for the given [Route]
     * @param dist an [Int] value corresponding to the relative distance between the [Route] endpoints
     * @param interval a [Long] number of milliseconds to sleep for
     * @return `true`
     */
    fun sendTrain(dist: Int, interval: Long, sound: String = "clickety-clack"): Boolean
}
