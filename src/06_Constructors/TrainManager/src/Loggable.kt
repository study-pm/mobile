/**
 * Provides interface for logging events with messages, statuses and timestamps
 */
interface Loggable {
    /**
     * Gets [Console.Color] mapping for message [Status]
     * @status a [Status] enum representing a message status
     * @return a [Console.Color] item value
     */
    fun getColor(status: Status) = when(status) {
        Status.Error    -> Console.Color.Red
        Status.Info     -> Console.Color.Blue
        Status.Plain    -> Console.Color.Reset
        Status.Service  -> Console.Color.Gray
        Status.Success  -> Console.Color.Green
        Status.Warn     -> Console.Color.Yellow
    }

    /**
     * Logs an event with a message, a status and a timestamp mark
     * @param msg a message [String]
     * @status a [Loggable.Status] item representing the event status
     */
    fun log(msg: String, status: Status)

    /**
     * Collection of statuses for discriminating messages of various sorts
     */
    enum class Status(val value: String) {
        Error("Error"),
        Info("Info"),
        Plain(""),
        Service("Service"),
        Success("Success"),
        Warn("Warning")
    }
}
