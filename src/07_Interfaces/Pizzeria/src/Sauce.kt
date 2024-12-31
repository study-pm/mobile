/**
 * Represents a single sauce
 * @param sort a [SauceSort] enumeration value specifying the sort of sauce
 * @param price a [Double] value specifying the price of sauce
 */
class Sauce(sort: SauceSort, override val price: Double) : Product(Category.Dressing, Kind.Sauce, sort.name, price)

/**
 * Collection of sauce sorts
 */
enum class SauceSort(val value: Int) {
    Barbecue(1),
    Cheese(2);

    companion object {
        /**
         * Gets a sauce name from its value
         * @param value an [Int] value
         * @return a [SauceSort] matching the input value
         */
        fun from(value: Int): SauceSort? {
            return entries.firstOrNull { it.value == value }
        }
    }
}
