class PizzaMoscow(
    neapolitanPizzaPrice: Double, romanPizzaPrice: Double,
    sicilainPizzaPrice: Double, tyroleanPizzaPrice: Double
) : PizzaCity(
    neapolitanPizzaPrice, romanPizzaPrice,
    sicilainPizzaPrice, tyroleanPizzaPrice
), CheckPhoto {
    override fun neapolitanPizzaSale() {
        neapolitanPizzaCount++
        println("Спасибо за покупку неаполитанской пиццы в Москве")
    }
    override fun romanPizzaSale() {
        romanPizzaCount++
        println("Спасибо за покупку римской пиццы в Москве")
    }
    override fun sicilianPizzaSale() {
        sicilainPizzaCount++
        println("Спасибо за покупку сицилийской пиццы в Москве")
    }
    override fun tyroleanPizzaSale() {
        tyroleanPizzaCount++
        println("Спасибо за покупку тирольской пиццы в Москве")
    }

    override fun showCheckPhoto() {
        println("У вас есть фотография чека?")
        println("1. Да\n2. Нет")
        if (readln() == "1") println("Вам будет скидка 50 рублей с покупки")
    }
}
