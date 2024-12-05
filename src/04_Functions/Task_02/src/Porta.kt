import kotlin.math.sqrt

/**
 * Encrypt/decrypt words implementing the bigram substitution ciphering based on a polygraphic Porta Cipher
 * @property keys an [Iterable] entity of [Char] elements, such as [CharRange], [List] of chars, [String]
 * @property supplement [Char] added to complement the last symbol group if it contains a single character
 * @property valRange range of [Int] values used for constructing encoding map values
 * @property valRank number of digits used in the largest value number
 * @property valSize a number of values in the encoding map
 * @property isValRnd specifies whether the encoding map values should be randomized
 * @return a new instance containing an encoding map and the supplementary data ready to encrypt/decrypt messages
 *
 * Usage examples
 * ```kotlin
 * // Getting a new instance without any suggestion or hint provided: discouraged for taking the longest
 * val cipher: Porta<Iterable<Char>> = Porta()
 * // Original message
 * val original = "kotlin"
 * // Encryption
 * val encrypted = cipher.encrypt("kotlin")
 * // Decryption
 * val decrypted = cipher.decrypt(encrypted)
 * // Print results
 * cipher.printResult(original, encrypted)
 * cipher.printResult(decrypted)
 * // Output
 * Plaintext	ko		tl		in
 * Ciphertext	46655	07991	30853
 * Decrypted	kotlin
 *
 * // Providing a sample text (thus prompting the encoding map auto detection) and a supplement
 * val cipher: Porta<Iterable<Char>> = Porta("123", 'a')
 * val original = "4567890"
 * val encrypted = cipher.encrypt(original)
 * cipher.printResult(original, encrypted)
 * val decrypted = cipher.decrypt(encrypted)
 * cipher.printResult(decrypted)
 * cipher.printCipherTable()
 * // Output
 * Provided supplement seems not to be in the specified range of keys, so the range will be extended to include the supplement character
 * Plaintext	45	67	89	0+a
 * Ciphertext	068	114	149	011
 * Decrypted	4567890a
 *  	0	1	2	3	4	5	6	7	8	9	a
 * 0	042	120	049	093	016	074	018	045	128	061	011
 * 1	031	107	072	038	163	117	181	166	105	026	200
 * 2	023	043	165	019	032	051	062	138	143	130	112
 * 3	157	153	014	041	139	148	161	002	111	065	150
 * 4	171	091	186	175	058	068	064	164	192	167	027
 * 5	071	133	110	003	131	022	177	121	188	180	013
 * 6	190	126	078	199	010	195	012	114	006	060	198
 * 7	081	057	028	046	040	147	017	048	116	197	096
 * 8	087	024	136	141	142	044	125	191	119	149	115
 * 9	155	140	055	000	056	059	173	135	178	033	084
 * a	185	036	025	035	034	076	100	030	052	102	108
 *
 * // Providing a charset, supplement, values range and a plain coding map usage indication
 * val cipher = Porta(Crypto.letEnLow, '0', 0..999, false)
 * val original = "algorithm"
 * val encrypted = cipher.encrypt(original)
 * cipher.printResult(original, encrypted)
 * val decrypted = cipher.decrypt(encrypted)
 * cipher.printResult(decrypted)
 * // Output
 * cipher.printCipherTable()
 * Provided supplement seems not to be in the specified range of keys, so the range will be extended to include the supplement character
 * Plaintext	al	go	ri	th	m+0
 * Ciphertext	011	176	467	520	350
 * Decrypted	algorithm0
 *
 * // Providing a full plaintext string as a sample and a supplement
 * val msg = "Kotlin (/ˈkɒtlɪn/) (Ко́тлин) — кроссплатформенный, статически типизированный, ООЯП."
 * val encrypted = Porta<Iterable<Char>>(msg, '*').encrypt(msg)
 * ```
 */
class Porta<T: Iterable<Char>> : Crypto {
    private val isValRnd: Boolean
    private var keys: T
    private val map: MutableMap<String, String>
    private var supplement: Char
    private var valRange: IntRange
    private val valRank: Int
        get() = this.valRange.last.toString().length
    private val valSize: Int
        get() = this.keys.count() * this.keys.count()

    /**
     * Creates a new instance holding the encryption settings (such as an encryption map, a supplement, etc.)
     * @param keys an [Iterable] entity of [Char] elements, such as [CharRange], [List] of chars, [String]
     * @param supplement [Char] added to complement the last symbol group if it contains a single character
     * @param valRange range of [Int] values used for constructing encoding map values
     * @param isRandomized specifies whether the encoding map values should be randomized
     */
    constructor(keys: T, supplement: Char?, valRange: IntRange?, isRandomized: Boolean = true) {
        this.keys = keys
        if (valRange == null) {
            this.valRange = 0..(this.valSize * 2)
        }
        else {
            if (sqrt((valRange.count() * valRange.count()).toDouble()) < this.valSize) {
                println(Console.format("Provided value range is not sufficient to fit full set of unique values " +
                        "for the cipher table, so the range will be extended to fulfill the requirement",
                    Console.Mode.Warning, Console.Style.Emphasized))
                this.valRange = valRange.first..(valRange.first + this.valSize)
            }
            this.valRange = valRange
        }
        if (supplement == null) {
            println(Console.format("No supplement provided, " +
                    "thus a random character will be used as a supplement",
                Console.Mode.Warning, Console.Style.Emphasized))
            this.supplement = this.keys.toList().random()
        }
        else {
            if (!checkIfIn(supplement, this.keys)) {
                println(Console.format("Provided supplement seems not to be in the specified range of keys, " +
                        "so the range will be extended to include the supplement character",
                    Console.Mode.Warning, Console.Style.Emphasized))
                this.keys = this.keys.plusElement(supplement) as T
            }
            this.supplement = supplement
        }
        this.isValRnd = isRandomized
        this.map = getMap(this.keys, this.isValRnd)
    }

    constructor(keys: T, isRandomized: Boolean = true) : this(keys, null, null, isRandomized)

    constructor(keys: T, supplement: Char?, isRandomized: Boolean = true) :
            this(keys, supplement, null, isRandomized)

    constructor(isRandomized: Boolean = true) : this(Text("").matchCharSet() as T, isRandomized)

    constructor(sample: String, supplement: Char?, valRange: IntRange?, isRandomized: Boolean = true) :
            this(Text(sample).matchCharSet() as T, supplement, valRange, isRandomized)

    constructor(sample: String, supplement: Char?, isRandomized: Boolean = true) :
            this(sample, supplement, null, isRandomized)

    constructor(sample: String, isRandomized: Boolean = true) : this(Text(sample).matchCharSet() as T, isRandomized)

    /**
     * Check if a given character belongs to the specified entity
     * @param c input [Char] to check
     * @param entity any [Iterable] object of characters (e.g. [List], [CharRange]) to check against
     * @return `true` if the given object contains all the symbols from the string, `false` if not
     */
    private fun checkIfIn(c: Char, entity: T) = c in entity

    /**
     * Convert [Int] to cipher map value format
     * @param n input [Int]
     * @return corresponding cipher map [String] value
     */
    private fun convertIntToValue(n: Int): String {
        var value = n.toString()
        while (value.length < valRank) value = "0$value"
        return value
    }

    /**
     * Ensures generating a randomized unique map value
     * @param map constructed cipher map
     * @return unique random cipher map value [String]
     */
    private fun getUniqueRandomValue(map: MutableMap<String, String>): String {
        var rnd: Int
        var value: String
        do {
            rnd = valRange.random()
            value = convertIntToValue(rnd)
        }
        while (map.containsValue(value))
        return value
    }

    /**
     * Generates a cipher map either of ordered or randomized values based on the provided key range
     * @param range [Iterable] range of keys
     * @param isRandomized specifies if cipher map values should be randomized
     * @return ciphering map
     */
    private fun getMap(range: Iterable<Char>, isRandomized: Boolean): MutableMap<String, String> {
        val map = mutableMapOf<String, String>()
        var value = valRange.first
        for (row in range) {
            for (col in range) {
                map[row.toString() + col.toString()] =
                    if (isRandomized) getUniqueRandomValue(map) else convertIntToValue(value)
                value++
            }
        }
        return map
    }

    /**
     * Encrypts an original word using the inner cipher table
     * @param msg input message [String]
     * @return encoded message [String]
     * @throws Exception if the provided input does not match the key set of encryption table
     */
    override fun encrypt(msg: String): String {
        val groups = msg.chunked(2).toMutableList()
        if (groups.last().length == 1) groups[groups.lastIndex] += this.supplement.toString()
        var res = ""
        groups.forEach {
            if (!map.containsKey(it)) throw Exception("Encryption table does not contain specified key: $it")
            res += map[it]
        }
        return res
    }

    /**
     * Decrypts the encoded word using the inner cipher table
     * @param msg encrypted message [String]
     * @return decrypted message [String]
     * @throws Exception if the provided input does not match the value set of encryption table
     */
    override fun decrypt(msg: String): String {
        val groups = msg.chunked(valRank)
        var res = ""
        groups.forEach { group ->
            if (!map.containsValue(group)) throw Exception("Encryption table does not contain specified value $group")
            res += map.filterValues { it == group }.keys.first()
        }
        return res
    }

    /**
     * Prints an encoding map in pretty format of a cipher table
     */
    fun printCipherTable() {
        var row = 0
        var col = 0
        print("\t")
        // Print top horizontal header
        for (char in keys) print("${char}\t" + if (valRank > 3) "\t" else "")
        // Print table body
        map.values.forEach { value ->
            // Print left vertical header
            if (col % keys.count().toDouble() == 0.0) {
                println()
                print("${keys.elementAt(row)}\t")
                row++
            }
            // Print values
            print("$value\t")
            col++
        }
        println()
    }

    /**
     * Prints the encryption result in pretty table format
     * @param plain original message [String]
     * @param cipher encrypted message [String]
     */
    fun printResult(plain: String, cipher: String) {
        val groups = plain.chunked(2).toMutableList()
        print(Console.format("Plaintext\t", style = Console.Style.Strong))
        groups.forEachIndexed { i, v ->
            print(v)
            if (i == groups.lastIndex) {
                if (v.length == 1) print("+$supplement")
                println()
            }
            else print(if (valRank > 3) "\t\t" else "\t")
        }
        print(Console.format("Ciphertext\t", style = Console.Style.Strong))
        val output = cipher.chunked(valRank)
        output.forEachIndexed { i, v -> print(v + if (i != output.lastIndex) "\t" else "\n") }
    }
    /**
     * Prints the decryption result in pretty table format
     * @param decrypted decoded message [String]
     */
    fun printResult(decrypted: String) {
        println(Console.format("Decrypted\t", style = Console.Style.Strong) + decrypted)
    }
}
