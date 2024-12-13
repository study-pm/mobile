/**
 * Represents a Train Manager Proxy wrapping around the original instance of [Manager]
 * and intercepting its property accesses and method calls for making some extra work (like redirected to logger etc.)
 * @param base an original instance of [Manager] object
 * @property isArrived a [Boolean] flag showing if the train arrived to the destination point
 * @property isLeft a [Boolean] flag showing if the train left off the departure point
 * @property route a single [Route] object from one [City] to another
 * @property tickets an [Int] number of tickets sold
 * @property train a single [Train] object
 */
class ManagerProxy(private val base: Manager) : Manageable by base {

    private var _isArrived: Boolean by LogDelegate(base.isArrived, "Arrival", true)
    override var isArrived: Boolean
        get() = _isArrived // Get from the logging delegate
        set(value) {
            _isArrived = value // Set on the logging delegate (which logs)
            base.isArrived = value // Optionally update the original printer's name if needed
        }

    private var _isLeft: Boolean by LogDelegate(base.isLeft, "Departure", true)
    override var isLeft: Boolean
        get() = _isLeft // Get from the logging delegate
        set(value) {
            _isLeft = value // Set on the logging delegate (which logs)
            base.isLeft = value // Optionally update the original printer's name if needed
        }

    private var _route: Route by LogDelegate(base.route)
    override var route: Route
        get() = _route // Get from the logging delegate
        set(value) {
            _route = value // Set on the logging delegate (which logs)
            base.route = value // Optionally update the original printer's name if needed
        }

    private var _tickets: Int by LogDelegate(base.tickets, "Tickets sold: ")
    override var tickets: Int
        get() = _tickets
        set(value) {
            _tickets = value
            base.tickets = value
        }

    private var _train: Train by LogDelegate(base.train)
    override var train: Train
        get() = _train
        set(value) {
            _train = value
            base.train = value
        }

    /**
     * Logs as Step 1 while returning the original `createRoute()` method call
     * @returns a new [Route] object with cities
     */
    override fun createRoute(): Route {
        println(Console.format("Step 1: Create a route", Console.Color.Cyan, Console.Style.Bold))
        return base.createRoute()
    }

    /**
     * Logs as Step 3 while returning the original `makeUpTrain()` method call
     * @return a fully complete [Train] object with cars filled with passengers
     */
    override fun makeUpTrain(): Train {
        println(Console.format("Step 3: Make up a train", Console.Color.Cyan, Console.Style.Bold))
        return base.makeUpTrain()
    }

    /**
     * Logs as Step 2 while returning the original `sellTickets()` method call
     * @param bookingOffice a [Booking] instance
     * @return a total [Int] number of sold tickets
     */
    override fun sellTickets(bookingOffice: Booking): Int {
        println(Console.format("Step 2: Sell tickets", Console.Color.Cyan, Console.Style.Bold))
        return base.sellTickets(bookingOffice)
    }

    /**
     * Prints additional information on the train departure and arrival
     * @param dist an [Int] value corresponding to the relative distance between the [Route] endpoints
     * @param interval a [Long] number of milliseconds for thread to sleep for between repeating the dot graphics
     * @return the result of the original `sendTrain()` method call
     */
    override fun sendTrain(dist: Int, interval: Long, sound: String): Boolean {
        println("The train ${route.value.first.name} - ${route.value.second.name} consisting of " +
                "${train.cars.size} car${if (train.cars.size > 1) "s" else ""} " +
                "with $tickets passengers is setting off the terminal station")
        print(Console.format("${route.value.first.name} ", Console.Color.Yellow, Console.Style.Bold))
        print("Chug-chug!") // setting off
        val isArrived = base.sendTrain(dist, interval, sound)
        print("Toot-toot!") // at the place
        print(Console.format(" ${route.value.second.name}", Console.Color.Yellow, Console.Style.Bold))
        println()
        println("The train is arriving to the final station")
        return isArrived
    }

    /**
     * Logs as Step 4 while returning the original `sellTickets()` method call
     * @return `true`
     */
    fun setTrain(): Boolean {
        println(Console.format("Step 4: Send the train", Console.Color.Cyan, Console.Style.Bold))
        return true
    }
}
