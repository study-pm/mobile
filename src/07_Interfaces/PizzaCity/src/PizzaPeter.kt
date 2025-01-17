/**
 * Represents a pizzeria in St. Petersburg
 */
class PizzaPeter(
    neapolitanPizzaPrice: Double, romanPizzaPrice: Double,
    sicilianPizzaPrice: Double, tyroleanPizzaPrice: Double
) : PizzaCity(
    neapolitanPizzaPrice, romanPizzaPrice,
    sicilianPizzaPrice, tyroleanPizzaPrice
), Drink {
    override var city = "Санкт-Петербург"

    // Учет количества проданного кофе и общей суммы выручки от кофе
    override var coffeeCount = 0
    override var coffeeEarnings = 0.0

    // Учет количества кофе, купленного с каждой пиццей
    override var coffeeWithNeapolitan = 0
    override var coffeeWithRoman = 0
    override var coffeeWithSicilian = 0
    override var coffeeWithTyrolean = 0

    override fun showStatistics() {
        super.showStatistics()
        showCoffeeStats(totalCustomers)
        super.showTotals()
    }
}
