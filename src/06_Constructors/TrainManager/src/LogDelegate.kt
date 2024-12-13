import kotlin.reflect.KProperty

/**
 * Represents a custom delegate implementing getValue and setValue operators to log the action to the console
 * When a property using this delegate is accessed or modified, it logs the action to the console.
 * @param value a property value
 * @param message a log message [String]
 * @property logger an attached [Logger] instance to handle the logging actions
 */
class LogDelegate<T>(private var value: T, private val message: String = "", private val isMute: Boolean = false) {
    private val logger = Logger()

    /**
     * @param thisRef the source object (the object you read)
     * @param property holds a description of the target property itself
     * @return value
     */
    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return value
    }

    /**
     * @param thisRef the source object (the object you read)
     * @param property holds a description of the target property itself
     * @param newValue a value to set
     */
    operator fun setValue(thisRef: Any?, property: KProperty<*>, newValue: T) {
        var msg = message
        if (message.isEmpty()) msg += "Set $newValue"
        else if (!isMute) msg += newValue
        logger.log(msg, Loggable.Status.Info);
        value = newValue
    }
}
