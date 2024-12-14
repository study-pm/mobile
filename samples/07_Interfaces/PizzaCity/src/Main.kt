import kotlin.system.exitProcess

/* PizzaCity
 *
 * Описать сеть пиццерий (две пиццерии минимум), которые имеют одинаковый ассортимент,
 * но цены на один и тот же товар различен. Функционал пиццерий одинаков, но они могут иметь некоторые различия,
 * связанные с местоположением. Пиццерии должны выводить некоторую статистику:
 * например, сколько каких пицц и на какую сумму продали.
 */

fun main() {
    val pizzaPeter = PizzaPeter(
        175.0, 241.5,
        167.5, 215.0
    )
    val pizzaMoscow = PizzaMoscow(
        215.0, 250.5,
        180.5, 240.0
    )
    var currentPizzaCity: PizzaCity

    while(true) {
        println("Добрый день! Выберите город")
        println("1. Москва\n2. Санкт-Петербург\n\n0. Выход из программы")

        val city = readln()
        currentPizzaCity = when (city) {
            "1" -> pizzaMoscow
            "2" -> pizzaPeter
            "0" -> break
            else -> {
                println("ERROR")
                continue
            }
        }

        println("Выберите пиццу:")
        println("1. Неаполитанская пицца\n2. Римская пицца\n3. Сицилийская пицца\n" +
                "4. Тирольская пицца\n\n0. Показать статистику")
        selectPizza(currentPizzaCity)
    }
}

private fun selectPizza(currentPizzaCity: PizzaCity) {
    when (readln()) {
        "1" -> {
            currentPizzaCity.neapolitanPizzaSale()
            selectAddService(currentPizzaCity)
        }

        "2" -> {
            currentPizzaCity.romanPizzaSale()
            selectAddService(currentPizzaCity)
        }

        "3" -> {
            currentPizzaCity.sicilianPizzaSale()
            selectAddService(currentPizzaCity)
        }

        "4" -> {
            currentPizzaCity.tyroleanPizzaSale()
            selectAddService(currentPizzaCity)
        }

        "0" -> currentPizzaCity.showStatistics()
        else -> {
            println("ERROR")
            exitProcess(1)
        }
    }
}

fun selectAddService(currentPizzaCity: PizzaCity) {
    when (currentPizzaCity) {
        is CheckPhoto -> currentPizzaCity.showCheckPhoto()
        is Drink -> currentPizzaCity.drinkSale()
    }
}
