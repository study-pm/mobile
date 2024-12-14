class PizzaPeter(
    neapolitanPizzaPrice: Double, romanPizzaPrice: Double,
    sicilainPizzaPrice: Double, tyroleanPizzaPrice: Double
) : PizzaCity(
    neapolitanPizzaPrice, romanPizzaPrice,
    sicilainPizzaPrice, tyroleanPizzaPrice
), Drink {
    override fun neapolitanPizzaSale() {
        neapolitanPizzaCount++
        println("Спасибо за покупку неаполитанской пиццы в Санкт-Петербурге")
    }
    override fun romanPizzaSale() {
        romanPizzaCount++
        println("Спасибо за покупку римской пиццы в Санкт-Петербурге")
    }
    override fun sicilianPizzaSale() {
        romanPizzaCount++
        println("Спасибо за покупку сицилийской пиццы в Санкт-Петербурге")
    }
    override fun tyroleanPizzaSale() {
        sicilainPizzaCount++
        println("Спасибо за покупку тирольской пиццы в Санкт-Петербурге")
    }

    override fun drinkSale() {
        println("Вы будете кофе?")
        println("1. Да\n2. Нет")
        if (readln() == "1") println("С вас 200 рублей")
    }
}
