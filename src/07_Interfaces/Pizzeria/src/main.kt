/* PizzaCity
 *
 * Описать сеть пиццерий (две пиццерии минимум), которые имеют одинаковый ассортимент,
 * но цены на один и тот же товар различен. Функционал пиццерий одинаков, но они могут иметь некоторые различия,
 * связанные с местоположением. Пиццерии должны выводить некоторую статистику:
 * например, сколько каких пицц и на какую сумму продали.
 *
 * Доработать программу добавив следующий функционал:
 * 01. Учитывается количество показанных чеков и общая сумма скидок
 * 02. Учитывается количество проданных кофе и общая сумма выручки за кофе
 * 03. Выручка по пиццерии показывается с учетом скидок и проданных кофе
 * 04. Подсчитать в процентном соотношении, сколько человек показывают фотографию чека, а сколько – нет.
 *     Результат выводить на экран по запросу статистики
 * 05. Подсчитать в процентном соотношении, сколько человек покупают кофе, а сколько – отказываются.
 *     Результат вывести на экран по запросу статистики
 * 06. Выводить на экран статистику, показывающую, к какой пицце чаще покупают кофе.
 *     Статистику выводить в количественном и процентном видах
 * 07. Определить область видимости для параметров в классе PizzaCity, чтобы не было сообщений об ошибке
 * 08. Добавить еще одну пиццерию в другом городе,
 *     но она должна и делать скидку при предъявлении фотографии чека, и предлагать кофе.
 *     Также, в данной пиццерии, должны предлагать к пицце, на выбор, один из двух соусов.
 *     Цену соусов установить самостоятельно.
 * 09. В статистику добавить количество проданных соусов и выручку за каждый из видов.
 *     Также итоговая сумма по данной пиццерии должна учитывать все скидки и выручку за дополнительные услуги
 * 10. Если пиццерия не предлагает какую-либо услугу, то статистика по этой услуге не показывается
 */

fun main() {
    val pizzaMoscow = PizzeriaPhoto(
        City.Moscow,
        mapOf(
            PizzaSort.Neapolitan.value to Pizza(PizzaSort.Neapolitan, 529.0),
            PizzaSort.Roman.value to Pizza(PizzaSort.Roman, 490.0),
            PizzaSort.Sicilian.value to Pizza(PizzaSort.Sicilian, 599.0),
            PizzaSort.Tyrolean.value to Pizza(PizzaSort.Tyrolean, 625.0),
        ),
        50.0
    )
    val pizzaSPb = PizzeriaCoffee(
        City.SPb,
        mapOf(
            PizzaSort.Neapolitan.value to Pizza(PizzaSort.Neapolitan, 490.0),
            PizzaSort.Roman.value to Pizza(PizzaSort.Roman, 460.0),
            PizzaSort.Sicilian.value to Pizza(PizzaSort.Sicilian, 570.0),
            PizzaSort.Tyrolean.value to Pizza(PizzaSort.Tyrolean, 614.0),
            CoffeeSort.Americano.value to Coffee(CoffeeSort.Americano, 140.0),
            CoffeeSort.Cappuccino.value to Coffee(CoffeeSort.Cappuccino, 180.0),
            CoffeeSort.Latte.value to Coffee(CoffeeSort.Latte, 160.0),
            CoffeeSort.Espresso.value to Coffee(CoffeeSort.Espresso, 190.0),
        )
    )
    val pizzaSevastopol = PizzeriaPhotoCoffeeSauce(
        City.Sevastopol,
        mapOf(
            PizzaSort.Neapolitan.value to Pizza(PizzaSort.Neapolitan, 520.0),
            PizzaSort.Roman.value to Pizza(PizzaSort.Roman, 580.0),
            PizzaSort.Sicilian.value to Pizza(PizzaSort.Sicilian, 490.0),
            PizzaSort.Tyrolean.value to Pizza(PizzaSort.Tyrolean, 570.0),
            CoffeeSort.Americano.value to Coffee(CoffeeSort.Americano, 170.0),
            CoffeeSort.Cappuccino.value to Coffee(CoffeeSort.Cappuccino, 200.0),
            CoffeeSort.Latte.value to Coffee(CoffeeSort.Latte, 150.0),
            CoffeeSort.Espresso.value to Coffee(CoffeeSort.Espresso, 210.0),
            SauceSort.Barbecue.toString() to Sauce(SauceSort.Barbecue, 40.0),
            SauceSort.Cheese.toString() to Sauce(SauceSort.Cheese, 60.0),
        ),
        60.0
    )
    var currentPizzeria: Pizzeria

    while(true) {
        println()
        println("Hello! Choose your city:")
        println("1. Moscow\n2. Saint Petersburg\n3. Sevastopol\n0. Exit")

        val city = readln()
        currentPizzeria = when (city) {
            "1" -> pizzaMoscow
            "2" -> pizzaSPb
            "3" -> pizzaSevastopol
            "0" -> break
            else -> {
                println("ERROR")
                continue
            }
        }

        val bill = currentPizzeria.offerServices()
        if (bill != null) currentPizzeria.sell(bill)
    }
}
