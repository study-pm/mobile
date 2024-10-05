fun main() {
    var arrOne = intArrayOf(7, 9, 1, 15, 8, 1, 24, 5, 78)
    arrOne.sort()
    arrOne.forEach { print(it.toString() + "\t") }
    println()
    arrOne = intArrayOf(7, 9, 1, 15, 8, 1, 24, 5, 78)
    arrOne.sort(4)
    arrOne.forEach { print(it.toString() + "\t") }
    println()
    arrOne = intArrayOf(7, 9, 1, 15, 8, 1, 24, 5, 78)
    arrOne.sort(3, 6)
    arrOne.forEach { print(it.toString() + "\t") }
}
