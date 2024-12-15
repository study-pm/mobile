/**
 * Represents a new pizzeria in Kazan that offers both services.
 */
class PizzaSevastopol(
    neapolitanPizzaPrice: Double, romanPizzaPrice: Double,
    sicilianPizzaPrice: Double, tyroleanPizzaPrice: Double,
    override var sauce1Price: Double, override var sauce2Price: Double
) : PizzaCity(
    neapolitanPizzaPrice, romanPizzaPrice,
    sicilianPizzaPrice, tyroleanPizzaPrice
), CheckPhoto, Drink, Sauce {
    override var city = "Севастополь"

    // Учет количества показанных чеков и общей суммы скидок
    override var photoCount = 0

    // Учет количества проданного кофе и общей суммы выручки от кофе
    override var coffeeCount = 0
    override var coffeeEarnings = 0.0

    // Учет количества кофе, купленного с каждой пиццей
    override var coffeeWithNeapolitan = 0
    override var coffeeWithRoman = 0
    override var coffeeWithSicilian = 0
    override var coffeeWithTyrolean = 0

    // Учет продаж соусов
    override var sauce1Sales = 0
    override val sauce1Earnings: Double
        get() = sauce1Sales * sauce1Price

    override var sauce2Sales = 0
    override val sauce2Earnings: Double
        get() = sauce2Sales * sauce2Price

    override val sauceEarnings: Double
        get() = sauce1Earnings + sauce2Earnings

    override fun neapolitanPizzaSale() {
        neapolitanPizzaCount++
        selectSauce()
        thank(Pizza.Neapolitan)
    }

    override fun romanPizzaSale() {
        romanPizzaCount++
        selectSauce()
        thank(Pizza.Roman)
    }

    override fun sicilianPizzaSale() {
        sicilianPizzaCount++
        selectSauce()
        thank(Pizza.Sicilian)
    }

    override fun tyroleanPizzaSale() {
        tyroleanPizzaCount++
        selectSauce()
        thank(Pizza.Tyrolean)
    }

    override fun showStatistics() {
        super.showStatistics()
        showSauceStats()
        showCoffeeStats(totalCustomers)
        showPhotoStats(totalCustomers)
        super.showTotals()
    }
}
