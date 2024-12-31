/**
 * Provides extra service offer capabilities
 */
interface Servable : Billable {
    /**
     * Offers sauce by specified price
     * @param bill a [Bill] object specifying a single receipt
     * @param assortment a [Map] of a product sort [String] as a key and a [Product] object as a value
     * @return a modified/original [Bill] with coffee sale added depending on the user answer
     */
    fun offerSauce(bill: Bill, assortment: Map<String, Product>): Bill {
        println("Choose a sauce for your pizza:")
        SauceSort.entries.forEach { println("${it.value}. $it") }
        println("0. No sauce")
        val choice = try { readln().toInt() } catch (exc: Exception) { 0 }
        val sort = SauceSort.from(choice)
        if (sort != null) bill.addItem(Item(Sauce(sort, assortment[sort.toString()]?.price!!), 1))
        return bill
    }
}
