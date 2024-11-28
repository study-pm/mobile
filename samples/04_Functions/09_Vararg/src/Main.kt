fun main() {
    if(myCount(7, "Иван", "Петр", "Сергей")) {
        println("Людей больше, чем мест")
    }
    else {
        println("Мест хватает для всех")
    }
}

fun myCount(counts: Int, vararg users: String) = if (counts > users.size) {
    false
} else {
    true
}
