/**
 * Provides a drink offer capabilities
 */
interface Drinkable : Billable {
    /**
     * Offers drink by specified price
     * @param bill a [Bill] object specifying a single receipt
     * @param coffeePrice a [Double] value specifying coffee price
     * @return a modified/original [Bill] with coffee sale added depending on the user answer
     */
    fun offerDrink(bill: Bill, coffeePrice: Double): Bill {
        print("Would you like to have a cup coffee? 1. Yes; 0. No: ")
        if ( try { readln().toInt() } catch (exc: Exception) { 0 } == 1 ) {
            bill.addItem(Item(Coffee(CoffeeSort.Latte, coffeePrice), 1))
            println("Here's your coffee for $coffeePrice rub.")
        }
        return bill
    }
}
