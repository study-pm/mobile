import kotlin.math.pow

/**
 * Encrypt/decrypt words implementing a homophonic substitution cipher
 * @param fqMap a letter frequency map
 * @param isRnd `true` if codes should be generated randomly from the full possible value range or `false` when from the restricted to each char one
 * @property valRank an [Int] number of digits used for values representation based on the allowed value range
 * @map represents a ciphering map
 *
 * Usage examples
 * ```kotlin
 * // Getting a new instance with a sample text provided
 * val cipher = Homophonic("abc")
 * // Original message
 * val original = "kotlin"
 * // Encryption
 * val encrypted = cipher.encrypt(original)
 * // Decryption
 * val decrypted = cipher.decrypt(encrypted)
 * // Print results
 * cipher.printResult(original, encrypted, decrypted)
 * Plaintext	k	o	t	l	i	n
 * Ciphertext	0906	2374	6125	2120	3667	4452
 * Decrypted	k	o	t	l	i	n
 *
 * // Getting a new instance with a sample text provided
 * val cipher = Homophonic("и")
 * // Original message
 * val original = "ОмОфОниЯ"
 * // Encryption
 * val encrypted = cipher.encrypt(original)
 * // Decryption
 * val decrypted = cipher.decrypt(encrypted)
 * // Print results
 * cipher.printResult(original, encrypted, decrypted)
 * Plaintext	О	м	О	ф	О	н	и	Я
 * Ciphertext	0014	0592	0013	0777	0060	0357	0251	0668
 * Decrypted	о	м	о	ф	о	н	и	я
 *
 *
 * // Getting a new instance with a specific language map provided
 * val cipher = Homophonic("и")
 * // Original message
 * val original = "ОмОфОниЯ"
 * // Encryption
 * val encrypted = cipher.encrypt(original)
 * // Decryption
 * val decrypted = cipher.decrypt(encrypted)
 * // Print results
 * cipher.printResult(original, encrypted, decrypted)
 * Plaintext	K	r	y	p	t	o	g	r	a	p	h	i	e
 * Ciphertext	5148	7153	5880	4266	5326	6831	5971	0639	1666	7435	0328	0704	3838
 * Decrypted	k	r	y	p	t	o	g	r	a	p	h	i	e
 *
 * // Getting a new instance within a specific frequency map provided and randomizing within full range off
 * val cipher = Homophonic(Crypto.Chars.FqMap.RuSt, false)
 * // Original message
 * val original = "Омофоническая замена"
 * // Encryption
 * val encrypted = cipher.encrypt(original)
 * // Decryption
 * val decrypted = cipher.decrypt(encrypted)
 * // Print results
 * Plaintext	О	м	о	ф	о	н	и	ч	е	с	к	а	я	 	з	а	м	е	н	а
 * Ciphertext	514	406	544	764	548	440	256	792	216	671	344	038	840	988	249	059	401	192	472	067
 * Decrypted	о	м	о	ф	о	н	и	ч	е	с	к	а	я	 	з	а	м	е	н	а
 *
 * ```
 */
class Homophonic(private val fqMap: Chars.FqMap, private val isRnd: Boolean = true) : Crypto() {
    private val valRank = fqMap.value.firstNotNullOf { (_, v) -> v }.toString().split('.')[1].length
    private var map: Map<Char, List<String>> = getMap().toSortedMap()

    /**
     * @param sample a [String] to match the respective frequency map object
     */
    constructor(sample: String, isRnd: Boolean = true) : this(Text(sample).matchFqMap(sample), isRnd)

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
     * Generate a cipher map of values randomized either within each character range or full range
     * @return ciphering map
     */
    private fun getMap(): Map<Char, List<String>> {
        var start = 0
        val set = mutableSetOf<String>()
        var rnd: String
        val map = fqMap.value.map { (k, v) ->
            val end = start + (v * 1000-1).toInt()
            val list = mutableListOf<String>()
            while (set.size < end) {
                rnd = convertIntToValue((if (isRnd) 0..(10.0.pow(valRank).toInt()) else start..end).random())
                if (!set.contains(rnd)) list.add(rnd)
                set.add(rnd)
            }
            start = end + 1
            k to list
        }.toMap()
        return map
    }

    /**
     * Encrypt an original message using the inner cipher table
     * @param msg input message [String]
     * @return encoded message [String]
     * @throws Exception if the provided input does not match the key set of encryption table
     */
    override fun encrypt(msg: String): String {
        val txt = msg.lowercase()
        val letterMap = txt.map { c -> c to 0 }.toMap().toMutableMap()
        var res = ""
        txt.forEach { c ->
            if (!map.containsKey(c)) throw Exception("Encryption table does not contain specified key: $c")
            res += map[c]?.get(letterMap[c]!!) ?: map[c]!![0]
            letterMap[c] = letterMap[c]!! + 1
        }
        return res
    }

    /**
     * Decrypts the encoded message using the inner cipher table
     * @param msg encrypted message [String]
     * @return decrypted message [String]
     * @throws Exception if the provided input does not match the value set of encryption table
     */
    override fun decrypt(msg: String): String {
        val txt = msg.lowercase()
        val groups = txt.chunked(valRank)
        var res = ""
        groups.forEach { group ->
            val found = map.filterValues { it.contains(group) }
            if (found.keys.isEmpty()) throw Exception("Encryption table does not contain specified value $group")
            res += found.keys.first()
        }
        return res
    }

    /**
     * Prints the encryption result in pretty table format
     * @param plain original message [String]
     * @param cipher encrypted message [String]
     * @param decrypted decrypted message [String]
     */
    override fun printResult(plain: String, cipher: String, decrypted: String) {
        print(Console.format("Plaintext\t", styles = setOf(Console.Style.Strong)))
        plain.forEach { print("$it\t") }
        println()
        print(Console.format("Ciphertext\t", styles = setOf(Console.Style.Strong)))
        cipher.chunked(valRank).forEach { print("$it\t") }
        println()
        print(Console.format("Decrypted\t", styles = setOf(Console.Style.Strong)))
        decrypted.forEach { print("$it\t") }
        println()
    }

    /**
     * Prints an encoding map in pretty format of a cipher table
     */
    override fun printCipherTable() {
        map.forEach { k, v ->
            print(Console.format(k.toString() + "\t", styles = setOf(Console.Style.Strong)))
            v.forEach { print(it + "\t") }
            println()
        }
    }
}

/* Sample I/O
=== Input 1 ===

 */