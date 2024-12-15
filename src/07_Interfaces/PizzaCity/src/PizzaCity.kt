/**
 * Represents a pizzeria
 */
abstract class PizzaCity(
    private val neapolitanPizzaPrice: Double, private val romanPizzaPrice: Double,
    private val sicilianPizzaPrice: Double, private val tyroleanPizzaPrice: Double
) {
    abstract var city: String
    var neapolitanPizzaCount = 0
    var romanPizzaCount = 0
    var sicilianPizzaCount = 0
    var tyroleanPizzaCount = 0
    val totalCustomers: Int
        get() = neapolitanPizzaCount + romanPizzaCount + sicilianPizzaCount + tyroleanPizzaCount

    // Учет выручки от продажи пиццы
    private var pizzaEarnings = 0.0

    // Учет выручки от прочих дополнительных услуг
    var extraEarnings = 0.0

    // Учет всех скидок
    open var totalDiscounts = 0.0

    // Общая выручка с учетом всех продаж и скидок
    private var totalEarnings = 0.0

    open fun neapolitanPizzaSale() {
        neapolitanPizzaCount++
        thank(Pizza.Neapolitan)
    }
    open fun romanPizzaSale() {
        romanPizzaCount++
        thank(Pizza.Roman)
    }
    open fun sicilianPizzaSale() {
        sicilianPizzaCount++
        thank(Pizza.Sicilian)
    }
    open fun tyroleanPizzaSale() {
        tyroleanPizzaCount++
        thank(Pizza.Tyrolean)
    }

    private fun calculatePizzaEarnings() {
        pizzaEarnings = neapolitanPizzaCount * neapolitanPizzaPrice + romanPizzaCount * romanPizzaPrice +
                sicilianPizzaCount * sicilianPizzaPrice + tyroleanPizzaCount * tyroleanPizzaPrice
    }

    open fun calculateTotalEarnings() {
        // Подсчет общей выручки с учетом всех продаж и скидок
        totalEarnings = pizzaEarnings + extraEarnings - totalDiscounts
    }

    open fun showPizzaStats() {
        calculatePizzaEarnings()
        println("\u001b[4mСтатистика по пицце:\u001b[0m")
        println("Продано сицилийской пиццы: $sicilianPizzaCount шт.")
        println("Продано неаполитанской пиццы: $neapolitanPizzaCount шт.")
        println("Продано римской пиццы: $romanPizzaCount шт.")
        println("Продано тирольской пиццы: $tyroleanPizzaCount шт.")
        println("Количество проданной пиццы: $totalCustomers шт.")
        println("Общая сумма выручки за пиццу: $pizzaEarnings руб.")
    }

    open fun showStatistics() {
        showPizzaStats()
    }

    open fun showTotals() {
        calculateTotalEarnings()
        print("\u001b[4m\u001b[1mИтого заработано денег с учётом всех продаж и скидок:\u001b[0m ")
        print("\u001B[1m$totalEarnings\u001B[0m руб.\n")
        println()
    }

    fun thank(pizza: Pizza) {
        val p = when(pizza.value) {
            1       -> "неаполитанской"
            2       -> "римской"
            3       -> "сицилийской"
            4       -> "тирольской"
            else    -> ""
        }
        val c = when(city) {
            "Москва"            -> "Москве"
            "Санкт-Петербург"   -> "Санкт-Петербурге"
            "Севастополь"       -> "Севастополе"
            else                -> ""
        }
        println("Спасибо за покупку $p пиццы в $c!")
    }

    enum class Pizza(val value: Int) {
        Neapolitan(1),
        Roman(2),
        Sicilian(3),
        Tyrolean(4)
    }
}
