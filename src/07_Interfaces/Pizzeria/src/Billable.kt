/**
 * Provides a sellable good actions for a single receipt
 * @property bills a [MutableList] of [Bill] items
 * @property totalAmount a [Double] value of total sales amount
 * @property totalCount an [Int] value of total sales number
 */
interface Billable {
    var bills: MutableList<Bill>
    val totalAmount: Double
        get() = bills.fold(0.0) { acc, v -> acc + v.amount }
    val totalCount: Int
        get() = bills.size

    /**
     * Gets a selected product distribution for the prepared product list
     * @param list a filtered [List] of [Bill] values
     * @return a [Map] of a pizza sort [String] as a key and an [Int] of product sales as value
     */
    fun distribute(list: List<Bill>): Map<String, Int> {
        val sortsDistribution = mutableMapOf<String, Int>()
        PizzaSort.entries.forEach { sortsDistribution[it.value] = 0 }
        list.forEach {
            it.items.forEach { item -> if (item.product.kind == Kind.Pizza)
                sortsDistribution[item.product.sort] = sortsDistribution[item.product.sort]!! + 1
            }
        }
        return sortsDistribution.toList().sortedByDescending { (_, value) -> value}.toMap()
    }

    /**
     * Gets total sales number depending on the product category
     * @param category a [Category] enumeration value specifying the product category
     * @return an [Int] value of total sales number
     */
    fun getCount(category: Category): Int {
        return bills.fold(0) { acc, bill ->
            acc + bill.items.filter { it.product.category == category }.size
        }
    }
    /**
     * Gets total sales number depending on the product kind
     * @param kind a [Kind] enumeration value specifying the product kind
     * @return an [Int] value of total sales number
     */
    fun getCount(kind: Kind): Int {
        return bills.fold(0) { acc, bill ->
            acc + bill.items.filter { it.product.kind == kind }.size
        }
    }
    /**
     * Gets total sales number depending on the product sort
     * @param sort a [String] value specifying the product sort
     * @return an [Int] value of total sales number
     */
    fun getCount(sort: String): Int {
        return bills.fold(0) { acc, bill ->
            acc + bill.items.filter { it.product.sort == sort }.size
        }
    }

    /**
     * Gets total gains depending on the product category
     * @param category a [Category] enumeration value specifying the product category
     * @return a [Double] value of total profit
     */
    fun getEarnings(category: Category): Double {
        return bills.fold(0.0) { acc, bill ->
            acc + bill.items.filter { it.product.category == category }.fold(0.0) { a, item -> a + item.amount}
        }
    }
    /**
     * Gets total gains depending on the product kind
     * @param kind a [Kind] enumeration value specifying the product kind
     * @return a [Double] value of total profit
     */
    fun getEarnings(kind: Kind? = null): Double {
        if (kind == null) return totalAmount
        return bills.fold(0.0) { acc, bill ->
            acc + bill.items.filter { it.product.kind == kind }.fold(0.0) { a, item -> a + item.amount}
        }
    }
    /**
     * Gets total gains depending on the product sort
     * @param sort a [String] value specifying the product sort
     * @return a [Double] value of total profit
     */
    fun getEarnings(sort: String): Double {
        return bills.fold(0.0) { acc, bill ->
            acc + bill.items.filter { it.product.sort == sort }.fold(0.0) { a, item -> a + item.amount}
        }
    }

    /**
     * Gets a selected product distribution by an accompanying pizza sales
     * @param category a [Category] enumeration value specifying the product category
     * @return a [Map] of a pizza sort [String] as a key and an [Int] of product sales as value
     */
    fun getSortsDistribution(category: Category): Map<String, Int> {
        val filtered = bills.filter { it.items.any { item -> item.product.category == category } }
        return distribute(filtered)
    }
    /**
     * Gets a selected product distribution by an accompanying pizza sales
     * @param kind a [Kind] enumeration value specifying the product kind
     * @return a [Map] of a pizza sort [String] as a key and an [Int] of product sales as value
     */
    fun getSortsDistribution(kind: Kind): Map<String, Int> {
        val filtered = bills.filter { it.items.any { item -> item.product.kind == kind } }
        return distribute(filtered)
    }

    /**
     * Prints a borderline made with a specific string fragment of a given length
     * @param symbol a [String] fragment consisting the border
     * @param length an [Int] value specifying the border length
     */
    fun printBorder(symbol: String = "=", length: Int = 54) {
        println(symbol.repeat(length))
    }

    /**
     * Prints a header formatted to be in line with the borderline of a given length
     * @param text a [String] value of a header
     * @param length an [Int] value specifying the border length
     */
    fun printHeader(text: String, length: Int = 54) {
        printBorder()
        val side = (length - text.length)/2
        println(" ".repeat(side) + text + " ".repeat(side))
        printBorder()
    }

    /**
     * Prints a subheader formatted to be in line with the borderline of a given length
     * @param text a [String] value of a header
     * @param length an [Int] value specifying the border length
     */
    fun printSubheader(text: String, length: Int = 54) {
        printBorder("_")
        val side = (length - text.length)/2
        val msg = "\u001b[4m$text\u001b[0m"
        println(" ".repeat(side) + msg + " ".repeat(side))
    }

    /**
     * Prints a selected product distribution accompanying the main product and relative to pizza sorts
     * @param kind a [Kind] enumeration value specifying the product kind
     */
    fun printDistribution(kind: Kind) {
        val sortsDist = getSortsDistribution(kind)
        val total = sortsDist.values.reduce { acc, count ->  acc + count }
        sortsDist.forEach { (sort, count) ->
            println("Sold $count pcs. with $sort pizza / ${getShare(count, total).toPercent(0)}")
        }
    }

    /**
     * Prints general statistics by the selected product kind
     * @param kind a [Kind] enumeration value specifying the product kind
     */
    fun printGeneral(kind: Kind) {
        println(showSold(getCount(kind), getEarnings(kind)) +
                " ≈" + getShare(getCount(kind), totalCount).toPercent(0) +
                " of total sales (" + getCount(kind) + "/" + totalCount + ")"
        )
    }

    /**
     * Prints general statistics with a corresponding header
     */
    fun printStats() {
        printHeader("Statistics")
        println("Total sales: \u001b[1m${bills.size}\u001b[0m, total amount sold: \u001b[1m$totalAmount\u001b[0m RUR")
    }

    /**
     * Prints extra statistics by the selected product kind
     * @param kind a [Kind] enumeration value specifying the product kind
     */
    fun printStatsExtra(kind: Kind) {
        printSubheader(kind.name)
        printGeneral(kind)
        printDistribution(kind)
    }

    /**
     * Prints statistics by the selected product sort
     * @param sortClass a [Class] specifying the product type
     */
    fun <E : Enum<E>>printStatsSort(sortClass: Class<E>) {
        var count: Int
        var earnings: Double
        var percentage: String
        var name: String
        val length = getLongestEnumName(sortClass)?.length!! + 1
        sortClass.enumConstants.forEach {
            count = getCount(it.toString())
            earnings = getEarnings(it.toString())
            percentage = getShare(count, totalCount).toPercent(2)
            name = it.name + ":"
            println("${name.padEnd(length, ' ')} sold $count pcs. for $earnings RUR / $percentage")}
    }

    /**
     * Adds a single formed receipt to a sales list
     * @param bill a [Bill] value specifying a single receipt
     */
    fun sell(bill: Bill) {
        bills.add(bill)
    }

    /**
     * Gets a sold amount with sales number
     * @param count an [Int] value specifying the number of pieces sold
     * @param amount a [Double] value specifying the sold amount
     * @return a [String] showing the sold amount with the sales number
     */
    fun showSold(count: Int, amount: Double) = "Sold \u001b[1m$count\u001b[0m pcs. for \u001b[1m$amount\u001b[0m RUR"
}
