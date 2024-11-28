fun main() {
    println(prediction("Афанасий", 45))
}

private fun prediction(name: String, age: Int): String {
    return if (name.count() > 7 && age % 5 == 0) {
        "Все будет ОЧЕНЬ хорошо"
    } else {
        "Все будет отлично"
    }
}
