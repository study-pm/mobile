/**
 * Serves as a basic class for implementing various ciphers in derived classes and provides encryption related utils
 */
abstract class Crypto : Cryptable {
    /**
     * Collection of character sets
     * @property num numeric symbols (digits)
     * @property letEnLow English alphabet letters lowercase
     * @property letEn English alphabet letters
     * @property letRuLow Russian alphabet letters lowercase
     * @property letRuLowClip Russian alphabet letters lowercase except for 'ё` and 'й' chars
     * @property letRu Russian alphabet letters
     * @property letCyr Cyrillic / Slavonic / Slavic
     * @property baseLat Basic Latin
     * @property baseLatLow Basic Latin lowercase
     * @property extLatA Latin Extended A
     * @property extLatB Latin Extended B
     */
    companion object CharSets {
        val num = '0'..'9'
        val letEnLow = 'a'..'z'
        val letEn = 'A'..'z'
        val letRuLow = ('а'..'е') + 'ё' + ('ж'..'я')
        val letRuLowClip = ('а'..'и') + ('к'..'я')
        val letRu = ('А'..'Е') + 'Ё' + ('Ж'..'е') + 'ё' + ('ж'..'я')
        val letCyr = 'Ѐ'..'ӿ'
        val baseLat = ' '..'~'
        val baseLatLow = (' '..'@') + ('['..'~')
        val extLatA = 'Ā'..'ſ'
        val extLatB = 'ƀ'..'ɏ'
    }

    /**
     * Stores a text fragment and provides its character analysis
     * @param value a text [String]
     */
    data class Text(var value: String) {
        val hasEn: Boolean
            get() = Regex("[A-z]").containsMatchIn(value)
        val hasRu: Boolean
            get() = Regex("[А-я]|Ёё").containsMatchIn(value)
        val hasMix: Boolean
            get() = hasEn && hasRu
        val isEmpty: Boolean
            get() = value.isEmpty()
        val isNum: Boolean
            get() = value.all { it.isDigit() }
        val isEnLow: Boolean
            get() = value.matches(Regex("^[a-z]+$"))
        val isEnLet: Boolean
            get() = value.matches(Regex("^[A-Za-z]+$"))
        val isEnNum: Boolean
            get() = value.matches(Regex("^[A-Za-z|\\d]+$"))
        val isEnBase: Boolean
            get() = value.matches(Regex("^[A-Za-z|\\d\\s\\p{Punct}]+$"))
        val isRuLow: Boolean
            get() = value.matches(Regex("^[а-я|ё]+$"))
        val isRuLet
            get() = value.matches(Regex("^[А-я|Ёё]+$"))
        val isRuNum: Boolean
            get() = value.matches(Regex("^[А-я|Ёё\\d]+$"))
        val isRuBase: Boolean
            get() = value.matches(Regex("^[А-я|Ёё\\d\\s\\p{Punct}]+$"))
        val isBaseLat: Boolean
            get() = value.matches(Regex("^[\\u0020-\\u007E]$"))
        val isBaseLatLow: Boolean
            get() = value.matches(Regex("^\\p{ASCII}\\p{Lower}+$"))
        val isMixLow: Boolean
            get() = value.matches(Regex("^[a-zа-я|ё]+$"))
        val isMixLet: Boolean
            get() = value.matches(Regex("^[A-Za-z\\p{IsCyrillic}]+$"))
        val isLatLet: Boolean
            get() = value.matches(Regex("^\\p{IsLatin}+$"))
        val isLatA: Boolean
            get() = value.matches(Regex("^[\\u0100-\\u017F]+$"))
        val isLatB: Boolean
            get() = value.matches(Regex("^[\\u0180-\\u024F]+$"))
        val isLatIPA: Boolean
            get() = value.matches(Regex("^[\\u0250-\\u02AD]+$"))
        val isCyr: Boolean
            get() = value.matches(Regex("^\\p{IsCyrillic}"))
        val isGreek: Boolean
            get() = value.matches(Regex("^\\p{IsGreek}"))

        /**
         * Match character set based on the stored text character analysis
         * @return character set as [Iterable] of [Char] values
         */
        fun matchCharSet() = when {
            isEmpty     -> CharSets.baseLat + CharSets.letRu
            isNum       -> CharSets.num
            isEnLow     -> CharSets.letEnLow
            isEnLet     -> CharSets.letEn
            isEnNum     -> CharSets.letEn + CharSets.num
            isEnBase    -> CharSets.baseLat
            isRuLow     -> CharSets.letRuLow
            isRuLet     -> CharSets.letRu
            isRuNum     -> CharSets.letRu + CharSets.num
            isRuBase    -> CharSets.letRu + CharSets.baseLat - CharSets.letEn
            isMixLow    -> CharSets.letRuLow + CharSets.letEnLow
            isMixLet    -> CharSets.letRu + CharSets.letEn
            else        -> value.toSet().toList() // Ensures no duplicating values
        }

        /**
         * Gets all data object properties (incl. getters)
         * @return [String] value of object props
         */
        override fun toString(): String {
            return "value=\"$value\", hasEn=$hasEn, hasRu=$hasRu, hasMix=$hasMix, isNum=$isNum,\n" +
                    "isEnLow=$isEnLow, isEnLet=$isEnLet, isEnNum=$isEnNum, isEnBase=$isEnBase,\n" +
                    "isRuLow=$isRuLow, isRuLet=$isRuLet, isRuNum=$isRuNum, isRuBase=$isRuBase,\n" +
                    "isBaseLat=$isBaseLat, isBaseLatLow=$isBaseLatLow, isMixLow=$isMixLow, isMixLet=$isMixLet,\n" +
                    "isLatLet=$isLatLet, isLatA=$isLatA, isLatB=$isLatB, isLatIPA=$isLatIPA\n" +
                    "isCyr=$isCyr, isGreek=$isGreek"
        }
    }

    abstract override fun encrypt(msg: String): String
    abstract override fun decrypt(msg: String): String
    abstract fun printCipherTable()
    abstract fun printResult(plain: String, cipher: String, decrypted: String)
}