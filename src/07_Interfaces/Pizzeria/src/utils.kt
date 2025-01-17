/**
 * Converts a [Double] to the percentage value
 * @return a [String] representation of the double value
 */
fun Double.toPercent(places: Int) = "${"%.${places}f".format(if (this.isNaN()) 0.0 else this * 100)}%"

/**
 * Gets a share relative to some [Int] value
 * @return a [Double] value representing a share
 */
fun getShare(amt: Int, total: Int) = amt.toDouble() / total

/**
 * Get the names of the enum constants and find the longest name
 * @param enumClass a [Class] of enum constant
 * @return a [String] value of the longest constant name
 */
fun <E : Enum<E>> getLongestEnumName(enumClass: Class<E>): String? {
    return enumClass.enumConstants
        .map { it.name } //
        .maxByOrNull { it.length } //
}
