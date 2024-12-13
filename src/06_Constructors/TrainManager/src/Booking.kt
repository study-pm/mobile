/**
 * Represents a place where tickets are sold (a booking office)
 * @param capacity a maximum [Int] quantity of tickets a single booking office can sell
 */
class Booking(private val capacity: Int = 201) {
    /**
     * Sells a random quantity of tickets within the specified age (limited to the booking office capacity)
     * @param minValue a minimum quantity of tickets
     * @return a random [Int] representing the quantity of tickets sold
     */
    fun sellTickets(minValue: Int = 5): Int {
        return (minValue..capacity).random()
    }
}
