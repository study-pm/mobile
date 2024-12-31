/**
 * Represents a pizzeria with photo service
 * @param city a [City] object specifying the city
 * @param assortment a [Map] of a product sort [String] as a key and a [Product] object as a value
 * @param discount a [Double] value of rebate provided
 * @property bills a [MutableList] of [Bill] values
 */
class PizzeriaPhoto(city: City, assortment: Map<String, Product>, private val discount: Double) :
    Pizzeria(city, assortment), Discountable, Checkable {

    /**
     * Offers services to a user
     * @return a [Bill] object reflecting the user's choice
     */
    override fun offerServices(): Bill? {
        var bill = super.offerServices()
        if (bill != null) bill = checkPhoto(bill, discount)
        return bill
    }
}
