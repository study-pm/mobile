fun main() {
    var roadWidth = "Узкая"
    var colorSignal = ""
    val actionUser = when (colorSignal) {
        "Зеленый"   -> "Переходим улицу"
        "Желтый"    -> "Готовимся к переходу"
        "Красный"   -> "Ожидаем разрешающего сигнала"
        else        -> {
            println("Светофор неисправен")
            if (roadWidth == "Узкая") {
                "Посмотрим налево, направо и аккуратно переходим"
            } else {
                "Ожидаем регулировщика"
            }
        }
    }

    println(actionUser)
}
