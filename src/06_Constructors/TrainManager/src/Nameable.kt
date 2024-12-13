/**
 * Provides an ability to name itself
 * @property name a [String] representation of a class name
 */
interface Nameable {
    /**
     * Gets a string representation of a [Nameable]
     * @return a [String] representation of [Nameable] simple name (type of object as a class name)
     */
    val name: String?
        get() = this::class.simpleName
}
