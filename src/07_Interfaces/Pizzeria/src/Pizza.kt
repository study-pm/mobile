/**
 * Represents a single pizza
 * @param sort a [PizzaSort] enumeration value specifying the sort of pizza
 * @param price a [Double] value specifying the price of pizza
 */
class Pizza(sort: PizzaSort, override val price: Double)
    : Product(Category.Food, Kind.Pizza, sort.name, price)

/**
 * Collection of pizza sorts
 */
enum class PizzaSort(val value: String) {
    Neapolitan("Neapolitan"),
    Roman("Roman"),
    Sicilian("Sicilian"),
    Tyrolean("Tyrolean")
}
