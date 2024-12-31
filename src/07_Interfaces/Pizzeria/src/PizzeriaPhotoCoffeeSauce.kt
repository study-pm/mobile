/**
 * Represents a pizzeria with photo service
 * @param city a [City] object specifying the city
 * @param assortment a [Map] of a product sort [String] as a key and a [Product] object as a value
 * @param discount a [Double] value of rebate provided
 * @property bills a [MutableList] of [Bill] values
 */
class PizzeriaPhotoCoffeeSauce(city: City, assortment: Map<String, Product>, private val discount: Double) :
    Pizzeria(city, assortment), Discountable, Checkable, Drinkable, Servable {

    /**
     * Offers services to a user
     * @return a [Bill] object reflecting the user's choice
     */
    override fun offerServices(): Bill? {
        val coffeePrice = assortment[CoffeeSort.Latte.value]?.price!!
        var bill = super.offerServices()
        if (bill != null) {
            bill = checkPhoto(bill, discount)
            offerDrink(bill, coffeePrice)
            offerSauce(bill, assortment)
        }
        return bill
    }

    /**
     * Prints general, pizza, coffee and sauce statistics
     */
    override fun printStatistics() {
        super.printStatistics()
        printStatsExtra(Kind.Coffee)
        printStatsExtra(Kind.Sauce)
        printStatsSort(SauceSort::class.java)
    }
}
