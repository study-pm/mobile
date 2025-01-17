/**
 * Represents a coffee product
 */
class Coffee(sort: CoffeeSort, override val price: Double) : Product(Category.Drink, Kind.Coffee, sort.name, price)

/**
 * Collection of coffee sorts
 */
enum class CoffeeSort(val value: String) {
    Americano("americano"),
    Cappuccino("cappuccino"),
    Latte("latte"),
    Espresso("espresso")
}
