/**
 * Represents a pizzeria
 * @param city a [City] object specifying the city
 * @param assortment a [Map] of a product sort [String] as a key and a [Product] object as a value
 * @property bills a [MutableList] of [Bill] values
 */
open class Pizzeria(private val city: City, val assortment: Map<String, Product>) : Billable {
    override var bills: MutableList<Bill> = mutableListOf()

    /**
     * Handles the user's pizza choice
     * @param choice a [String] specifying the user's choice
     * @return an [Item] to be added to the receipt
     */
    private fun handlePizzaChoice(choice: String?): Item? {
        if (choice == null) {
            printStatistics()
            printBorder()
            return null
        }
        return Item(assortment[choice]!!, 1)
    }

    /**
     * Offers pizza from a specified assortment
     * @return a [String] value representing user choice
     */
    private fun offerPizza(): String? {
        println("Choose a pizza:")
        val sorts = assortment.mapNotNull { if (it.value !is Pizza) null else it.value.sort }
        sorts.forEachIndexed { i, v -> println("${i+1}. $v") }
        println("0. Show statistics")
        val choice = try { readln().toInt() } catch (e: Exception) { 0 }
        if (choice == 0) return null
        return sorts.getOrNull(choice-1)
    }

    /**
     * Offers services to a user
     * @return a [Bill] object reflecting the user's choice
     */
    open fun offerServices(): Bill? {
        val item = handlePizzaChoice(offerPizza())
        if (item != null) {
            thank(item)
            return Bill(mutableSetOf(item))
        }
        else return null
    }

    /**
     * Prints pizza statistics with a corresponding header
     */
    private fun printPizzaStats() {
        printSubheader("Pizza")
        println(showSold(getCount(Category.Food), getEarnings(Category.Food)))
        assortment
            .filter { it.value.kind == Kind.Pizza }
            .forEach { println(it.value.sort + ":${if (it.value.sort.length < 8) "\t\t" else "\t"}" +
                    "sold " + getCount(it.value.sort) + " pcs. " +
                    "for " + getEarnings(it.value.sort) + " RUR" +
                    " / " + getShare(getCount(it.value.sort), totalCount).toPercent(2)
            )
            }
    }

    /**
     * Prints general and pizza statistics
     */
    open fun printStatistics() {
        printStats()
        printPizzaStats()
    }

    /**
     * Thanks a user for buying a product [Item]
     */
    private fun thank(item: Item) {
        println("Thank you for buying ${item.product.sort} ${item.product.kind} in ${city.value}!")
    }
}
