/**
 * Represents a pizzeria in Moscow
 */
class PizzaMoscow(
    neapolitanPizzaPrice: Double, romanPizzaPrice: Double,
    sicilianPizzaPrice: Double, tyroleanPizzaPrice: Double
) : PizzaCity(
    neapolitanPizzaPrice, romanPizzaPrice,
    sicilianPizzaPrice, tyroleanPizzaPrice,
), CheckPhoto {
    override var city = "Москва"

    // Учет количества показанных чеков
    override var photoCount = 0
    override var totalDiscounts = 0.0

    override fun showStatistics() {
        super.showStatistics()
        showPhotoStats(totalCustomers)
        super.showTotals()
    }
}