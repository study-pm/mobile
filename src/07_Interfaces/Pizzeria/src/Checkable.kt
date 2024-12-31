/**
 * Provides a receipt photo check capabilities
 */
interface Checkable {
    /**
     * Asks if a user has a receipt photo and adds a discount in the bill
     * @param bill a [Bill] representing the current receipt
     * @discount a [Double] value of discount
     * @return a modified/original [Bill] with discount added depending on the user answer
     */
    fun checkPhoto(bill: Bill, discount: Double): Bill {
        print("Do you have a receipt photo? 1. Yes; 0. No: ")
        if ( try { readln().toInt() } catch (exc: Exception) { 0 } == 1 ) {
            println("Thanks! You will get a $discount RUR discount off your buying.")
            bill.addDiscount(discount)
        }
        return bill
    }
}