/**
 * Provide discount actions
 * @property totalDiscounts an [Int] value of total discounts number
 * @property totalDiscountAmount a [Double] value of total discounts amount (sum) made
 * @property totalDiscountsShare a [Double] value of discounts quantity relative to total sales number
 * @property total a [Double] value of net amount (a difference between total amount and total discount amount)
 */
interface Discountable : Billable {
    val totalDiscounts: Int
        get() = bills.count { it.discount > 0 }
    val totalDiscountAmount: Double
        get() = bills.fold(0.0) { acc, v -> acc + v.discount  }
    val totalDiscountsShare: Double
        get() = getShare(totalDiscounts, totalCount)
    val total: Double
        get() = totalAmount - totalDiscountAmount

    /**
     * Prints general statistics with a corresponding header considering all discounts
     */
    override fun printStats() {
        super.printStats()
        println("Total discounts made: $totalDiscounts/$totalCount ≈ ${totalDiscountsShare.toPercent(0)}")
        println("Total discount amount: $totalDiscountAmount RUR")
        println("Total amount regarding discounts: \u001B[1m$total RUR\u001B[0m")
    }
}
