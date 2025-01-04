/**
 * Represents a pizzeria with coffee service
 * @param city a [City] object specifying the city
 * @param assortment a [Map] of a product sort [String] as a key and a [Product] object as a value
 * @property bills a [MutableList] of [Bill] values
 */
class PizzeriaCoffee(city: City, assortment: Map<String, Product>) :
    Pizzeria(city, assortment), Drinkable {

    /**
     * Offers services to a user
     * @return a [Bill] object reflecting the user's choice
     */
    override fun offerServices(): Bill? {
        val coffeePrice = assortment[CoffeeSort.Latte.value]?.price!!
        val bill = super.offerServices()
        if (bill != null) offerDrink(bill, coffeePrice)
        return bill
    }

    /**
     * Prints general, pizza and coffee statistics
     */
    override fun printStatistics() {
        super.printStatistics()
        printStatsExtra(Kind.Coffee)
    }
}
