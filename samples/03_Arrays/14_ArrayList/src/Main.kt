fun main() {
    val testListNum = mutableListOf<Int>() // Вызываем метод
    testListNum.add(97)
    testListNum.add(85)
    testListNum.add(17)
    println(testListNum)

    val testArrList = ArrayList<String>() // Вызываем конструктор класса
    testArrList.add("один")
    testArrList.add("два")
    testArrList.add("три")
    println(testArrList)

    // В обоих случаях получаем Array List. Можно использовать для получения динамических массивов.
}
