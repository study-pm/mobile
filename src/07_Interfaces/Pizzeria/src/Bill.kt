/**
 * Represents a single receipt
 * @param items a [MutableSet] of [Item] objects representing positions
 * @param discount a [Double] value of discount amount for a single receipt
 * @property amount a [Double] value of total sold items
 */
class Bill(val items: MutableSet<Item>, var discount: Double = 0.0) {
    val amount: Double
        get() = items.fold(0.0) { acc, v -> acc + v.amount }

    /**
     * Adds discount to a single receipt
     * @param discount a [Double] value of the discount
     */
    fun addDiscount(discount: Double) {
        this.discount = discount
    }

    /**
     * Adds a position to a single receipt
     * @param item an [Item] representing a single position in the receipt
     */
    fun addItem(item: Item) {
        items.add(item)
    }
}
