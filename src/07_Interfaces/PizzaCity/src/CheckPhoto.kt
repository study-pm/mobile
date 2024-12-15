/**
 * Provides a 50 rub. discount for a receipt photo
 */
interface CheckPhoto {
    // Учет количества показанных чеков и общей суммы скидок
    var photoCount: Int
    var totalDiscounts: Double

    fun showCheckPhoto() {
        println("У вас есть фотография чека?")
        println("1. Да\n2. Нет")
        if (readln() == "1") {
            photoCount++
            totalDiscounts += 50.0
            println("Вам будет скидка 50 рублей с покупки")
        }
    }

    fun showPhotoStats(totalSales: Int) {
        if (totalSales > 0) {
            // Подсчет процента покупателей с фотографией чека
            println("\u001b[4mСтатистика по чекам:\u001b[0m")
            println("Процент покупателей с фотографией чека: ${getPercentage(photoCount, totalSales).toPercent()}")
            println("Количество показанных чеков: $photoCount")
            println("Общая сумма скидок: $totalDiscounts руб.")
        }
    }
}
