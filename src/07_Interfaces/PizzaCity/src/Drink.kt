/**
 * Provides a coffee drink offering to pizza
 */
interface Drink {
    // Учет количества проданного кофе и общей суммы выручки от кофе
    var coffeeCount: Int
    var coffeeEarnings: Double
    var extraEarnings: Double

    // Учет количества кофе, купленного с каждой пиццей
    var coffeeWithNeapolitan: Int
    var coffeeWithRoman: Int
    var coffeeWithSicilian: Int
    var coffeeWithTyrolean: Int

    fun drinkSale(pizzaType: String) {
        println("Вы будете кофе?")
        println("1. Да\n2. Нет")
        if (readln() == "1") {
            val coffeePrice = 200.0
            coffeeCount++
            coffeeEarnings += coffeePrice
            extraEarnings += coffeePrice
            when (pizzaType) {
                "1" -> coffeeWithNeapolitan++
                "2" -> coffeeWithRoman++
                "3" -> coffeeWithSicilian++
                "4" -> coffeeWithTyrolean++
            }
            println("С вас 200 рублей за кофе")
        }
    }

    fun showCoffeeStats(totalCustomers: Int) {
        fun getShare(coffeeCount: Int) = getPercentage(coffeeCount, totalCustomers)
        if (totalCustomers > 0) {
            // Подсчет процента покупателей кофе
            println("\u001b[4mСтатистика по кофе:\u001b[0m")
            println("Процент покупателей кофе: ${getShare(coffeeCount).toPercent()}")
            println("Кофе с неаполитанской пиццей: ${getShare(coffeeWithNeapolitan).toPercent()}")
            println("Кофе с римской пиццей: ${getShare(coffeeWithRoman).toPercent()}")
            println("Кофе с сицилийской пиццей: ${getShare(coffeeWithSicilian).toPercent()}")
            println("Кофе с тирольской пиццей: ${getShare(coffeeWithTyrolean).toPercent()}")
            println("Количество проданных кофе: $coffeeCount")
            println("Общая сумма выручки за кофе: $coffeeEarnings руб.")
        }
        else {
            println("\nКофе не было куплено.")
        }
    }
}
