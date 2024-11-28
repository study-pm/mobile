fun main() {
    prnStr("Иван", "Петр", "Афанасий", "Владимир", "Дмитрий")
    println()
    prnStr("C++", "Kotlin", "Golang")
    println()
    print(prnSum(1, 9, 5, 16, 73))
}

fun prnStr(vararg name: String) {
    for (item in name) {
        print(item + "\t")
    }
}

fun prnSum(vararg itemNum: Int):Int {
    var result = 0
    for (item in itemNum) result += item
    return result
}
