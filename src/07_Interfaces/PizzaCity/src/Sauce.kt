/**
 * Provides a sauce supplement offering to pizza
 */
interface Sauce {
    // Учет продаж соусов и общей выручки от соусов
    var sauce1Price: Double
    var sauce1Sales: Int
    val sauce1Earnings: Double

    var sauce2Price: Double
    var sauce2Sales: Int
    val sauce2Earnings: Double

    val sauceEarnings: Double
    var extraEarnings: Double

    fun selectSauce() {
        println("Выберите соус:")
        println("1. Барбекю ($sauce1Price руб.)\n2. Сырный ($sauce2Price руб.)\n3. Без соуса")
        when (readln()) {
            "1" -> {
                sauce1Sales++
                extraEarnings += sauce1Price
            }
            "2" -> {
                sauce2Sales++
                extraEarnings += sauce2Price
            }
            "3" -> {}
            else -> {
                println("Неверный выбор соуса.")
            }
        }
    }

    fun showSauceStats() {
        // Вывод статистики по соусам.
        val totalSaucesSold = sauce1Sales + sauce2Sales

        if (totalSaucesSold > 0) {
            println("\u001b[4mСтатистика по соусам:\u001b[0m")
            println("Барбекю - продано: $sauce1Sales шт., выручка: $sauce1Earnings руб.")
            println("Сырный - продано: $sauce2Sales шт., выручка: $sauce2Earnings руб.")
            println("Общее количество проданных соусов: $totalSaucesSold шт.")
            println("Общая выручка от соусов: $sauceEarnings руб.")
        } else {
            println("\nСоусы не были куплены.")
        }
    }
}
