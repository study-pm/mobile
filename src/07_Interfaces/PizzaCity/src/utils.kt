/**
 * Converts a [Double] to the percentage value
 * @return a [String] representation of the double value
 */
fun Double.toPercent() = "${"%.2f".format(this * 100)}%"

/**
 * Gets a share relative to some [Int] value
 * @return a [Double] value representing a share
 */
fun getPercentage(amt: Int, total: Int) = amt.toDouble() / total
