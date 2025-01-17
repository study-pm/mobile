/**
 * Represents a single product
 * @param category a [Category] enumeration value specifying the category of the product
 * @param kind a [Kind] enumeration value specifying the kind of the product
 * @param sort a [String] value specifying the sort of the product
 * @param price a [Double] value specifying the price of the product
 */
abstract class Product (
    open val category: Category,
    open val kind: Kind,
    open val sort: String,
    open val price: Double
)
