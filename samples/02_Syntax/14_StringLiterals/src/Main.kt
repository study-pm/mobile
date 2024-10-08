fun main() {
    val s = "Hello, world!\n"
    println("Regular escaped string: " + s)

    var text = """
        for (c in "foo"){
            print(c)
        }"""

    println(text + "\n")
    println(text.trimIndent() + "\n")

    text = """
    |Tell me and I forget.
    |Teach me and I remember.
    |Involve me and I learn.
    |(Benjamin Franklin)
    """.trimMargin()
    println(text)
}

// В Kotlin представлены два типа строковых литералов:
//  экранированные строки с экранированными символами
//  обычные строки, которые могут содержать символы новой строки и произвольный текст
// Экранирование выполняется общепринятым способом, а именно с помощью обратного слеша (\).
// Обычная строка выделена тройной кавычкой ("""), не содержит экранированных символов, но может содержать
// символы новой строки и любые другие символы.
// Чтобы удалить пробелы в начале обычных строк, используйте функцию trimMargin().
// По умолчанию | используется в качестве префикса поля,
// но вы можете выбрать другой символ и передать его в качестве параметра, например, trimMargin(">").
