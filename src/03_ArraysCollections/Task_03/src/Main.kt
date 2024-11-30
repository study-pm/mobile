/* Имеется массив из символов русского алфавита (все 33 символа, могут быть не по порядку).
 * Символы алфавита нумеруются от 1 до 33. Каждое число используется только один раз.
 * Сообщение шифруется с помощью ключевого слова, задаваемого пользователем.
 * Номер символа ключевого слова показывает, на сколько нужно сдвинуться по массиву из символов русского алфавита.
 * Составить программу шифровки строкового выражения.
 * Первый массив считать закольцованным. Регистр букв не имеет значения.
 */

/**
 * Collection of console control characters representing ANSI color codes used in output
 */
enum class OutputType(val code: String) {
    Success("\u001b[32m"),
    Error("\u001b[31m"),
    Reset("\u001b[0m")
}

/**
 * Set color to console out considering the input type
 * @param msg input message string
 * @param type type of output that defines the output string color (`Success` | `Error`)
 * @return colored string fragment ending with color reset code
 *
 * Usage examples:
 * ```kotlin
 * println(colorizedOut("\nSuccess", OutputType.Success))
 * println(colorizedOut("\nInvalid input", OutputType.Error))
 * ```
 */
fun colorizeOut(msg: String, type: OutputType) = type.code + msg + OutputType.Reset.code

/**
 * Returns exception description depending on the exception message
 * @param msg input message string
 */
fun handleException(msg: String?) = when(msg) {
    "DecryptMsg"    -> "Message cannot be decrypted because of the invalid character"
    "EncryptMsg"    -> "Message cannot be encrypted because of the invalid character"
    "KeyMap"        -> "Key must contain only the given map keys as characters"
    "KeyRange"      -> "Key must contain only the given range symbols"
    else            -> msg ?: "Unknown error"
}

/**
 * Encrypt and decrypt text
 * @param keyword input word or phrase string
 * @param respectCase defines if cyphering should be case-sensitive
 * @property codeMap map of characters and their respective numeric codes
 * @property isCaseSensitive determines cyphering case sensitivity
 * @property keyword combination of symbols used to encode and decode messages
 *
 * Usage examples:
 * ```kotlin
 * // Cyphering a Russian word regardless of case using the default keymap
 * val keyword = "ПОЛЕ"
 * val message = "СООБЩЕНИЕ"
 * val cypher: Cypher<Iterable<Char>> = Cypher(keyword)
 * val encrypted = cypher.encrypt(message)          // => аёфирхжсю
 * val decrypted = cypher.decrypt(encrypted)        // => сообщение
 *
 * // Cyphering a Russian word regardless of case using an external keymap
 * val keyword = "Котлин"
 * val message = "Программирование"
 * val cypher: Cypher<Iterable<Char>> = Cypher(keyword, Cypher.defaultMap)
 * val encrypted = cypher.encrypt(message)          // => ежюохзшэёёщюръёщ
 * val decrypted = cypher.decrypt(encrypted)        // => программирование
 *
 * // Cyphering an English word regardless of case using a randomized keymap
 * val keyword = "Kotlin"
 * val message = "Programming"
 * val cypher = Cypher(keyword, Cypher.letLowEn)
 * val encrypted = cypher.encrypt(message)          // => hnakuieqmef
 * val decrypted = cypher.decrypt(encrypted)        // => programming
 *
 * // Cyphering a Russian word regarding case using a randomized keymap
 * val keyword = "Полёты"
 * val message = "Ёжики"
 * val cypher = Cypher(keyword, Cypher.lettersRu, true)
 * val encrypted = cypher.encrypt(message)          // => ОуМйэ
 * val decrypted = cypher.decrypt(encrypted)        // => Ёжики
 *
 * // Cyphering an English phrase including punctuation characters regardless of case using a randomized keymap
 * val keyword = "Kotlin is"
 * val message = "A programming language that makes coding concise, cross-platform, and fun."
 * val cypher = Cypher(keyword, Cypher.letLowEn + Cypher.punctNum)
 * val encrypted = cypher.encrypt(message)
 * // =>  s' y5""e&y;4$e-m->'nm$;9"mu/>,tcoe"4y;4$?smbp""p$?"y4+h'i";my!&=q;m$o2i%c
 * val decrypted = cypher.decrypt(encrypted)
 * // => a programming language that makes coding concise, cross-platform, and fun.
 *
 * // Cyphering a Russian phrase including punctuation characters regarding case using a randomized keymap
 * val keyword = "Котлин - это"
 * val message = " кроссплатформенный, статически типизированный, объектно-ориентированный ЯП."
 * val cypher = Cypher(keyword, Cypher.lettersRu + Cypher.punctNum, true)
 * val encrypted = cypher.encrypt(message)
 * // => ЮЭ"ыЁ0зшМРЙЮ6Хч-втИъ*;.Ф,б(сЁ'ть4Ъ6б/б"ыьУЯ9Бю$<п,@с*ДЯтьС"бфк.5ъкД Я7ЕёЮснМ
 * val decrypted = cypher.decrypt(encrypted)
 * // =>  кроссплатформенный, статически типизированный, объектно-ориентированный ЯП.
 *
 * ```
 */
class Cypher<T: Iterable<Char>>(keyword: String, respectCase: Boolean = false) {
    /**
     * Collection of useful constants
     * @property lettersEn English alphabet letters
     * @property lettersRu Russian alphabet letters
     * @property letLowEn English alphabet letters lowercase
     * @property letLowRu Russian alphabet letters lowercase
     * @property punctNum ASCII punctuation and numeric characters
     * @property defaultMap default character map for Russian lowercase letters
     */
    companion object {
        val lettersEn = 'A'..'z'
        val lettersRu = ('А'..'я') + 'Ё' + 'ё'
        val letLowEn = 'a'..'z'
        val letLowRu = ('а'..'я') + 'ё'
        val punctNum = ' '..'@'
        val defaultMap: Map<Char, Int> = mapOf(
            'а' to 21, 'б' to 13, 'в' to 4, 'г' to 20, 'д' to 22, 'е' to 1, 'ё' to 25, 'ж' to 12, 'з' to 24, 'и' to 14,
            'й' to 2, 'к' to 28, 'л' to 9, 'м' to 23, 'н' to 3, 'о' to 29, 'п' to 6, 'р' to 16, 'с' to 15, 'т' to 11,
            'у' to 26, 'ф' to 5, 'х' to 30, 'ц' to 27, 'ч' to 8, 'ш' to 18, 'щ' to 10, 'ь' to 33, 'ы' to 31, 'ъ' to 32,
            'э' to 19, 'ю' to 7, 'я' to 17
        )
    }
    private var codeMap = defaultMap
    private var isCaseSensitive = false
    private val keyword: String

    init {
        this.isCaseSensitive = respectCase
        if (keyword.isEmpty()) throw Exception("Must not be empty")
        this.keyword = if (this.isCaseSensitive) keyword else keyword.lowercase()
    }

    /**
     * @param keyword combination of symbols used to encode and decode messages
     * @param codeMap map of characters and their respective numeric codes
     * @param respectCase defines if cyphering should be case-sensitive
     */
    constructor(keyword: String, codeMap: Map<Char, Int>, respectCase: Boolean = false) : this(keyword, respectCase) {
        if (!this.checkIfIn(this.keyword, codeMap)) throw Exception("KeyMap")
        this.codeMap = codeMap
    }
    /**
     * @param keyword combination of symbols used to encode and decode messages
     * @param charList list of characters to generate code map based upon
     * @param respectCase defines if cyphering should be case-sensitive
     */
    constructor(keyword: String, charList: T, respectCase: Boolean = false) : this(keyword, respectCase) {
        if (!this.checkIfIn(this.keyword, charList)) throw Exception("KeyRange")
        this.codeMap = getRandomizedCodeMap(charList)
    }

    /**
     * Check if a given string consists of symbols presenting as the given map keys
     * @param s input string to check the containing characters occurrence
     * @param map Map of characters to check against
     * @return `true` if the given map has all the symbols from the string, `false` if not
     */
    private fun checkIfIn(s: String, map: Map<Char, Int>): Boolean {
        var has = true
        for (char in s) {
            if (!map.containsKey(char)) has = false; break
        }
        return has
    }
    /**
     * Check if a given string consists of symbols presenting as the given map keys
     * @param s input string to check the containing characters occurrence
     * @param entity any iterable object of characters (e.g. List, Range) to check against
     * @return `true` if the given object contains all the symbols from the string, `false` if not
     */
    private fun checkIfIn(s: String, entity: T): Boolean {
        var isIn = true
        for (char in s) {
            if (char !in entity) isIn = false; break
        }
        return isIn
    }

    /**
     * Get a randomized char/code map
     * @param symbols iterable object representing a list of characters
     * @return randomized map of characters and their codes
     */
    private fun <T: Iterable<Char>> getRandomizedCodeMap(symbols: T): Map<Char, Int> {
        val codeMap = HashMap<Char, Int>()
        val numbers = 1..symbols.count()
        for (c in symbols) {
            var code: Int
            // Ensures unique code value
            do {
                code = numbers.random()
                codeMap[c] = code
            } while (codeMap.filterValues { it == code }.keys.size != 1)
        }
        return codeMap
    }

    /**
     * Get normalized key
     * @param text input string fragment to serve as a base for the encryption key
     * @return normalized key to be of the same length as the input text fragment
     */
    private fun normalizeKeyLength(text: String): String {
        var key = this.keyword
        while (key.length != text.length) {
            if (key.length < text.length) key += keyword
            if (key.length > text.length) key = key.substring(0 until text.length)
        }
        return key
    }

    /**
     * Encrypt text message using stored encryption key
     * @param text input original message string
     * return encrypted message string
     */
    fun encrypt(text: String): String {
        val txt = if (this.isCaseSensitive) text else text.lowercase()
        val key = this.normalizeKeyLength(txt)
        var res = ""
        for ((i, v) in txt.withIndex()) {
            if (codeMap[v] == null) throw Exception("EncryptMsg")
            var code = codeMap[v]!! + codeMap[key[i]]!!
            if (code > codeMap.size) code -= codeMap.size
            res += codeMap.filterValues { it == code }.keys.first()
        }
        return res
    }

    /**
     * Decrypt cyphered message using stored encryption key
     * @param text input encrypted message string
     * return decrypted original message string
     */
    fun decrypt(text: String): String {
        val txt = if (this.isCaseSensitive) text else text.lowercase()
        val key = this.normalizeKeyLength(txt)
        var res = ""
        for ((i, v) in txt.withIndex()) {
            if (codeMap[v] == null) throw Exception("DecryptMsg")
            var code = codeMap[v]!! - codeMap[key[i]]!!
            if (code <= 0) code += codeMap.size
            res += codeMap.filterValues { it == code }.keys.first()
        }
        return res
    }
}

fun main() {
    try {
        // User input
        print("Enter a keyword: ")
        val keyword = readln()
        print("Enter a message to encrypt: ")
        val message = readln()

        // Creating cypher instance
        val cypher: Cypher<Iterable<Char>> = Cypher(keyword)
        // Encrypting
        val encrypted = cypher.encrypt(message)
        println(colorizeOut("Encrypted: ", OutputType.Success) + encrypted)
        // Decrypting
        val decrypted = cypher.decrypt(encrypted)
        println(colorizeOut("Decrypted: ", OutputType.Success) + decrypted)
    }
    catch (exc: Exception) {
        println(colorizeOut("Invalid input: ", OutputType.Error) + handleException(exc.message))
    }
}
