import java.sql.Timestamp

/**
 * Represents a logger
 */
class Logger : Loggable {
    /**
     * Logs an event with a message, a status and a timestamp mark
     * @param msg a message [String]
     * @status a [Loggable.Status] item representing the event status
     */
    override fun log(msg: String, status: Loggable.Status) {
        print(Console.format("${Timestamp(System.currentTimeMillis())} ", Console.Color.Gray))
        print(Console.format(status.value.uppercase(), getColor(status)))
        print(" $msg\n")
    }
}
