fun main() {
    val num = mutableMapOf(1 to "один", 2 to "два", 3 to "три", 4 to "три")
    num.remove(3)
    println(num)
    num[1] = "восемь"
    num[55] = "пятьдесят пять"
    println(num)
    num.putAll(setOf(6 to "шесть", 9 to "девять"))
    num.putAll(mapOf(16 to "десять и шесть", 29 to "двадцать и девять"))
    println(num)
}
