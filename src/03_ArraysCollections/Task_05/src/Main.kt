import java.util.SortedSet

/* Создать приложение, в котором пользователь вводит массив из различных слов.
 * На выходе приложение должно показать слова сгруппированные по признаку "состоят из одинаковых букв".
 */

fun main() {
    println("Enter words, each from a new line (enter 0 to end the sequence):")
    val groups = mutableMapOf<SortedSet<Char>, MutableList<String>>()
    while (true) {
        val input = readln()
        if (input == "0") break
        val key = input.toSortedSet()
        if (groups.containsKey(key)) groups[key]?.add(input)
        else groups[key] = mutableListOf(input)
    }
    println("\u001b[32mList of words having the same character set:\u001b[0m"  )
    for ((k, v) in groups) {
        println("\u001b[1m${k.joinToString(separator = "") { it.toString() }}\u001b[0m: $v")
    }
}

/* Sample I/O
=== Input ===
eat
tea
tan
ate
nat
bat
0
=== Output ===
List of words having the same character set:
aet: [eat, tea, ate]
ant: [tan, nat]
abt: [bat]
*/
