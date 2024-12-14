abstract class PizzaCity(
    val neapolitanPizzaPrice: Double, val romanPizzaPrice: Double,
    val sicilainPizzaPrice: Double, val tyroleanPizzaPrice: Double
) {
    var neapolitanPizzaCount = 0
    var romanPizzaCount = 0
    var sicilainPizzaCount = 0
    var tyroleanPizzaCount = 0

    abstract fun neapolitanPizzaSale()
    abstract fun romanPizzaSale()
    abstract fun sicilianPizzaSale()
    abstract fun tyroleanPizzaSale()

    fun showStatistics() {
        println("Продано сицилийской пиццы: $sicilainPizzaCount")
        println("Продано неаполитанской пиццы: $neapolitanPizzaCount")
        println("Продано римской пиццы: $romanPizzaCount")
        println("Продано тирольской пиццы: $tyroleanPizzaCount")

        val money = neapolitanPizzaCount * neapolitanPizzaPrice + romanPizzaCount * romanPizzaPrice +
                sicilainPizzaCount * sicilainPizzaPrice + tyroleanPizzaCount * tyroleanPizzaPrice

        println("Всего заработано денег: $money")
    }
}
