/**
 * Represents a single bill position
 * @param product a [Product] item
 * @param quantity an [Int] value specifying the [Product] quantity
 * @property amount a [Double] value specifying the sale amount
 */
class Item(val product: Product, private val quantity: Int) {
    val amount: Double
        get() = product.price * quantity
}
