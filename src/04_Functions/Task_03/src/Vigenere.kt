/**
 * Alphabetic encryption/decryption implementing the Vigenère cipher based on multiple Caesar ciphers
 * @param keyword a [String] determining the sequential Caesar cipher increments
 * @param isCaseSensitive specifies whether the encoding map values should be randomized
 * @param isRndShift specifies whether the shifts between the tabula recta rows should be randomized
 * @param isStrShuffle specifies if the cipher table base row should be a randomized string
 * @param sample a [String] providing additional information to get the best match while char set auto-detection
 * @property charSet a [Set] of characters used to generate encryption map
 * @property key a prepared [String] specifying the Caesar cipher increments
 * @property map a ciphering [Map] used for encryption and decryption
 * @return a new instance containing an encoding map and the supplementary data ready to encrypt/decrypt messages
 *
 * Usage examples
 * ```kotlin
 * // Getting a new instance with only keyword provided
 * val keyword = "kotlin"
 * val cipher = Vigenere(keyword)
 * // Original message
 * val original = "vigenere"
 * // Encryption
 * val encrypted = cipher.encrypt(original)
 * // Decryption
 * val decrypted = cipher.decrypt(encrypted)
 * // Print results
 * cipher.printResult(original, encrypted, decrypted)
 * // Output
 * Plaintext	vigenere
 * Keyword		kotlinko
 * Ciphertext	wyngpukj
 * Decrypted	vigenere
 *
 * // Getting a new instance with only keyword provided and string shuffling options toggled off
 * val keyword = "123"
 * val cipher = Vigenere(keyword, isStrShuffle = false)
 * // Original message
 * val original = "4567890"
 * // Encryption
 * val encrypted = cipher.encrypt(original)
 * // Decryption
 * val decrypted = cipher.decrypt(encrypted)
 * // Print results
 * cipher.printResult(original, encrypted, decrypted)
 * cipher.printCipherTable()
 * // Output
 * Plaintext	4567890
 * Keyword		1231231
 * Ciphertext	9656844
 * Decrypted	4567890
 *
 *      0	1	2	3	4	5	6	7	8	9
 * 0	3	4	5	6	7	8	9	0	1	2
 * 1	7	8	9	0	1	2	3	4	5	6
 * 2	9	0	1	2	3	4	5	6	7	8
 * 3	0	1	2	3	4	5	6	7	8	9
 * 4	8	9	0	1	2	3	4	5	6	7
 * 5	4	5	6	7	8	9	0	1	2	3
 * 6	2	3	4	5	6	7	8	9	0	1
 * 7	5	6	7	8	9	0	1	2	3	4
 * 8	6	7	8	9	0	1	2	3	4	5
 * 9	1	2	3	4	5	6	7	8	9	0
 *
 * // Getting a new instance with only keyword provided string shuffling and random shifting options toggled off
 * val keyword = "Виженер"
 * val cipher = Vigenere(keyword, isStrShuffle = false, isRndShift = false)
 * // Original message
 * val original = "Абрамов"
 * // Encryption
 * val encrypted = cipher.encrypt(original)
 * // Decryption
 * val decrypted = cipher.decrypt(encrypted)
 * // Print results
 * cipher.printResult(original, encrypted, decrypted)
 * // Output
 * Plaintext	Абрамов
 * Keyword		Виженер
 * Ciphertext	ВЙЧЕЪУТ
 * Decrypted	Абрамов
 *
 * // Getting a new instance with only keyword provided string shuffling and random shifting options toggled off
 * val keyword = "Виженер"
 * val cipher = Vigenere(keyword, isStrShuffle = false, isRndShift = false)
 * // Original message
 * val original = "Абрамов"
 * // Encryption
 * val encrypted = cipher.encrypt(original)
 * // Decryption
 * val decrypted = cipher.decrypt(encrypted)
 * // Print results
 * cipher.printResult(original, encrypted, decrypted)
 * // Output
 * Plaintext	Абрамов
 * Keyword		Виженер
 * Ciphertext	ВЙЧЕЪУТ
 * Decrypted	Абрамов
 *
 * // Getting a new instance with keyword and original message as a sample provided, thus matching any input
 * val keyword = "Vigenère"
 * val original = "Kotlin (/ˈkɒtlɪn/) (Ко́тлин) — кроссплатформенный, статически типизированный, ООЯП."
 * val cipher = Vigenere(keyword, sample = original)
 * // Encryption
 * val encrypted = cipher.encrypt(original)
 * // Decryption
 * val decrypted = cipher.decrypt(encrypted)
 * // Print results
 * cipher.printResult(original, encrypted, decrypted)
 * // Output
 * Plaintext	Kotlin (/ˈkɒtlɪn/) (Ко́тлин) — кроссплатформенный, статически типизированный, ООЯП.
 * Keyword		VigenèreVigenèreVigenèreVigenèreVigenèreVigenèreVigenèreVigenèreVigenèreVigenèreVig
 * Ciphertext	анlоoVраKeV.KыonḰoаПèОɪ—)ыКаОрПзО.ч фыɪеОм(ètЯkКыoчКl—iɪз.Пвn—iП)Пiˈè.фоlˈɒtnс/ввк
 * Decrypted	Kotlin (/ˈkɒtlɪn/) (Ко́тлин) — кроссплатформенный, статически типизированный, ООЯП.
 */

class Vigenere(
    keyword: String,
    private val isCaseSensitive: Boolean = true,
    private val isRndShift: Boolean = true,
    private val isStrShuffle: Boolean = true,
    sample: String? = null
) : Crypto() {
    private val charSet: Set<Char>
    private val key: String = if (isCaseSensitive) keyword else keyword.lowercase()
    private val map: Map<Char, List<Char>>

    init {
        charSet = getCharSet(keyword, sample)
        map = setMap()
    }

    /**
     * Match character set based on the user input
     * @param key a [String] used as a keyword
     * @param sample an example reference [String] to better match the char set
     * @return a [Set] of characters used as encryption map keys
     */
    private fun getCharSet(key: String, sample: String?): Set<Char> {
        if (key.isEmpty()) throw Exception("Key must not be empty")
        val keysample = if (sample != null) key + sample else key
        return Text(if (isCaseSensitive) keysample else keysample.lowercase()).matchCharSet().toSet()
    }

    /**
     * Get index list specifying the starting characters
     * @param size a quantity of the encoding map symbols
     * @param isRnd specifies if the shifts between each row should be randomized
     * @return a [MutableList] of [Int] values determining the starting char index
     */
    private fun getShifts(size: Int, isRnd: Boolean): MutableList<Int> {
        val indices = mutableListOf<Int>()
        var index: Int
        for (i in 0..size-1) {
            if (isRnd) {
                index = getUniqueIndex(indices, size-1)
            }
            else {
                index = i
            }
            indices.add(index)
        }
        return indices
    }

    /**
     * Ensures generating a randomized unique indices
     * @param indices a [MutableList] reference to search for duplicates in
     * @param size a quantity of the encoding map symbols
     * @return unique random cipher map index [Int]
     */
    private fun getUniqueIndex(indices: MutableList<Int>, size: Int): Int {
        var index: Int
        do {
            index = (0..size).random()
        }
        while (indices.contains(index))
        return index
    }

    /**
     * Get normalized key
     * @param text input string fragment to serve as a base for the encryption key
     * @return normalized key to be of the same length as the input text fragment
     */
    private fun getKeyNormalized(text: String): String {
        var key = this.key
        while (key.length != text.length) {
            if (key.length < text.length) key += this.key
            if (key.length > text.length) key = key.substring(0 until text.length)
        }
        return key
    }

    /**
     * Build an encryption map
     * @return a [Map] matching characters and cipher strings
     */
    private fun setMap(): Map<Char, List<Char>> {
        val shifts = getShifts(charSet.size, isRndShift)
        val list = (if (isStrShuffle) charSet.shuffled() else charSet).toList()
        var str: List<Char>
        val map = buildMap {
            charSet.forEachIndexed { index, char ->
                str = list.subList(shifts[index], list.size)
                if (str.size < list.size) str += list.subList(0, list.size - str.size)
                else  str = list.subList(0, list.size)
                put(char, str)
            }
        }
        return map
    }

    /**
     * Encrypt an original message using the inner cipher table
     * @param msg input message [String]
     * @return encoded message [String]
     * @throws Exception if the provided input does not match the key set of encryption table
     */
    override fun encrypt(msg: String): String {
        val txt = if (isCaseSensitive) msg else msg.lowercase()
        val key = getKeyNormalized(txt)
        return txt.foldIndexed("") { i, acc, c -> acc + map[c]?.get(charSet.indexOf(key[i])) }
    }

    /**
     * Decrypts the encoded message using the inner cipher table
     * @param msg encrypted message [String]
     * @return decrypted message [String]
     * @throws Exception if the provided input does not match the value set of encryption table
     */
    override fun decrypt(msg: String): String {
        val key = getKeyNormalized(msg)
        return msg.foldIndexed("") { i, acc, c ->
            acc + map.filterValues { s -> s.indexOf(c) == charSet.indexOf(key[i]) }.keys.first() }
    }

    /**
     * Prints an encoding map (aka tabula recta) in pretty format of a cipher table
     */
    override fun printCipherTable() {
        print("\t")
        // Print top horizontal header
        for (char in charSet) print(Console.format("$char\t", Console.Mode.Service, Console.Style.Strong))
        // Print table body
        for ((char, row) in map.entries) {
            println()
            // Print left vertical header
            print(Console.format("$char\t", Console.Mode.Service, Console.Style.Strong))
            for (col in row) {
                print("$col\t")
            }
        }
    }

    /**
     * Prints the encryption result in pretty table format
     * @param plain original message [String]
     * @param cipher encrypted message [String]
     * @param decrypted decrypted message [String]
     */
    override fun printResult(plain: String, cipher: String, decrypted: String) {
        print(Console.format("Plaintext\t", styles = setOf(Console.Style.Strong)))
        println(plain)
        print(Console.format("Keyword\t\t", styles = setOf(Console.Style.Strong)))
        println(getKeyNormalized(plain))
        print(Console.format("Ciphertext\t", styles = setOf(Console.Style.Strong)))
        println(cipher)
        print(Console.format("Decrypted\t", styles = setOf(Console.Style.Strong)))
        println(decrypted)
    }
}
