/**
 * Returns the symbol occurrences count map in the order of their appearance in the original string
 * @param s the input string
 * @return unsorted map of symbols count
 */
fun getSymbolCount(s: String): MutableMap<Char, Int> {
    val res = mutableMapOf<Char, Int>()
    for (c in s) res[c] = res.getOrElse(c) { 0 } + 1
    return res
}

fun main() {
    print("Enter character sequence: ")
    val input = readln()

    val resMap = getSymbolCount(input).toSortedMap()
    resMap.forEach { println("${it.key} - ${it.value}") }
}

// Sample input: AASADDSS
/* Expected output:
    A - 3
    D - 2
    S - 3
 */
