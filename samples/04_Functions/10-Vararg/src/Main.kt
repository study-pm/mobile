fun main() {
    if(myCount("Иван", "Петр", "Сергей", counts = 2)) {
        println("Людей больше, чем мест")
    }
    else {
        println("Мест хватает для всех")
    }
}

fun myCount(vararg users: String, counts: Int) = if (counts > users.size) {
    false
} else {
    true
}
