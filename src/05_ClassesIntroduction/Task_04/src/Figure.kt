/**
 * Provides a geometry figure features
 * @property name a [String] representation of a class name
 */
interface Figure {
    val name: String?
        get() = this::class.simpleName
}
